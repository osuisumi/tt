package com.haoyu.tip.workshop.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.workshop.dao.IWorkshopAuthorizeDao;
import com.haoyu.tip.workshop.entity.Workshop;
import com.haoyu.tip.workshop.entity.WorkshopAuthorize;
import com.haoyu.tip.workshop.entity.WorkshopRelation;
import com.haoyu.tip.workshop.event.UpdateWorkshopAuthorizeEvent;
import com.haoyu.tip.workshop.service.IWorkshopAuthorizeService;
import com.haoyu.tip.workshop.service.IWorkshopRelationService;
import com.haoyu.tip.workshop.utils.WorkshopAuthorizeRole;
import com.haoyu.tip.workshop.utils.WorkshopAuthorizeState;

@Service
public class WorkshopAuthorizeServiceImpl implements IWorkshopAuthorizeService{

	@Resource
	private IWorkshopAuthorizeDao workshopAuthorizeDao;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IWorkshopRelationService workshopRelationService;
	@Resource
	private PropertiesLoader propertiesLoader;
	
	@Override
	public Response create(WorkshopAuthorize obj) {
		return BaseServiceUtils.create(obj, (MybatisDao) workshopAuthorizeDao);
	}

	@Override
	public Response update(WorkshopAuthorize obj) {
		return BaseServiceUtils.update(obj, (MybatisDao) workshopAuthorizeDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao) workshopAuthorizeDao);
	}

	@Override
	public WorkshopAuthorize get(String id) {
		return (WorkshopAuthorize) BaseServiceUtils.get(id, (MybatisDao) workshopAuthorizeDao);
	}

	@Override
	public List<WorkshopAuthorize> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao) workshopAuthorizeDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateWorkshopAuthorize(WorkshopAuthorize workshopAuthorize) {
		String[] idArray = workshopAuthorize.getId().split(",");
		List<String> ids = Arrays.asList(idArray);
		if (Collections3.isNotEmpty(ids)) {
			Response response = this.updateByIds(ids, workshopAuthorize);
			if (response.isSuccess()) {
				WorkshopRelation workshopRelation = workshopAuthorize.getWorkshopRelation();
				response = workshopRelationService.updateMemberNum(workshopRelation);
				if (response.isSuccess()) {
					applicationContext.publishEvent(new UpdateWorkshopAuthorizeEvent(ids));
				}
			}
			return response;
		}
		return Response.failInstance();
	}

	@Override
	public Response updateByIds(List<String> ids, WorkshopAuthorize workshopAuthorize) {
		Map<String, Object> param = Maps.newHashMap();
		workshopAuthorize.setUpdateTime(System.currentTimeMillis());
		workshopAuthorize.setUpdatedby(ThreadContext.getUser());
		param.put("ids", ids);
		param.put("entity", workshopAuthorize);
		int count = workshopAuthorizeDao.updateByIds(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response createWorkshopAuthorize(Workshop workshop) {
		WorkshopAuthorize workshopAuthorize = new WorkshopAuthorize();
		workshopAuthorize.setWorkshop(workshop);
		workshopAuthorize.setState(WorkshopAuthorizeState.PASS.toString());
		if (Collections3.isNotEmpty(workshop.getMasters())) {
			for (User user : workshop.getMasters()) {
				String id = WorkshopAuthorize.getId(workshop.getId(), user.getId());
				workshopAuthorize.setId(id);
				workshopAuthorize.setUser(user);
				workshopAuthorize.setRole(WorkshopAuthorizeRole.MASTER.toString());
				try {
					this.create(workshopAuthorize);
				} catch (DuplicateKeyException e) {
					this.update(workshopAuthorize);
				}
			}
		}
		if (Collections3.isNotEmpty(workshop.getMembers())) {
			for (User user : workshop.getMembers()) {
				String id = WorkshopAuthorize.getId(workshop.getId(), user.getId());
				workshopAuthorize.setId(id);
				workshopAuthorize.setUser(user);
				workshopAuthorize.setRole(WorkshopAuthorizeRole.MEMBER.toString());
				try {
					this.create(workshopAuthorize);
				} catch (DuplicateKeyException e) {
					this.update(workshopAuthorize);
				}
			}
		}
		if (Collections3.isNotEmpty(workshop.getAssists())) {
			for (User user : workshop.getAssists()) {
				String id = WorkshopAuthorize.getId(workshop.getId(), user.getId());
				workshopAuthorize.setId(id);
				workshopAuthorize.setUser(user);
				workshopAuthorize.setRole(WorkshopAuthorizeRole.ASSIST.toString());
				try {
					this.create(workshopAuthorize);
				} catch (DuplicateKeyException e) {
					this.update(workshopAuthorize);
				}
			}
		}
		Response response = workshopRelationService.updateMemberNum(workshop.getWorkshopRelations().get(0));
		if (response.isSuccess()) {
			List<String> ids = Collections3.extractToList(workshop.getMembers(), "id");
			if (Collections3.isNotEmpty(ids)) {
				applicationContext.publishEvent(new UpdateWorkshopAuthorizeEvent(ids));
			}
		}
		return Response.successInstance();
	}

	@Override
	public Response deleteByPhysics(WorkshopAuthorize workshopAuthorize) {
		int count = workshopAuthorizeDao.deleteByPhysics(workshopAuthorize);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteWorkshopAuthorize(WorkshopAuthorize workshopAuthorize) {
		Response response = this.deleteByPhysics(workshopAuthorize);
		if (response.isSuccess()) {
			WorkshopRelation workshopRelation = workshopAuthorize.getWorkshopRelation();
			response = workshopRelationService.updateMemberNum(workshopRelation);
			if (response.isSuccess()) {
				applicationContext.publishEvent(new UpdateWorkshopAuthorizeEvent(Lists.newArrayList(workshopAuthorize.getUser().getId())));
			}
			return response;
		}
		return Response.failInstance();
	}
	
	@Override
	public Response deleteWorkshopAuthorizeBatch(WorkshopAuthorize workshopAuthorize) {
		String[] idArray = workshopAuthorize.getId().split(",");
		List<String> ids = Arrays.asList(idArray);
		int count = workshopAuthorizeDao.deleteByIds(ids);
		if (count > 0) {
			WorkshopRelation workshopRelation = workshopAuthorize.getWorkshopRelation();
			Response response = workshopRelationService.updateMemberNum(workshopRelation);
			if (response.isSuccess()) {
				String[] uidArray = workshopAuthorize.getUser().getId().split(",");
				List<String> uids = Arrays.asList(uidArray);
				applicationContext.publishEvent(new UpdateWorkshopAuthorizeEvent(uids));
			}
		}
		return Response.failInstance();
	}

	@Override
	public Response createWorkshopAuthorize(WorkshopAuthorize workshopAuthorize) {
		Response response = this.create(workshopAuthorize);
		if (response.isSuccess()) {
			response = workshopRelationService.updateMemberNum(workshopAuthorize.getWorkshopRelation());
			if (response.isSuccess()) {
				applicationContext.publishEvent(new UpdateWorkshopAuthorizeEvent(Lists.newArrayList(workshopAuthorize.getUser().getId())));
			}
		}
		return response;
	}

}
