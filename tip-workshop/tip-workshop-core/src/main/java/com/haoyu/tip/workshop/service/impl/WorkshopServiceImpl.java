package com.haoyu.tip.workshop.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.workshop.dao.IWorkshopDao;
import com.haoyu.tip.workshop.entity.Workshop;
import com.haoyu.tip.workshop.entity.WorkshopAuthorize;
import com.haoyu.tip.workshop.entity.WorkshopRelation;
import com.haoyu.tip.workshop.service.IWorkshopAuthorizeService;
import com.haoyu.tip.workshop.service.IWorkshopRelationService;
import com.haoyu.tip.workshop.service.IWorkshopService;
import com.haoyu.tip.workshop.utils.WorkshopAuthorizeRole;
import com.haoyu.tip.workshop.utils.WorkshopAuthorizeState;

@Service("workshopService")
public class WorkshopServiceImpl implements IWorkshopService {
	
	@Resource
	private IWorkshopDao workshopDao;
	@Resource
	private IWorkshopRelationService workshopRelationService;
	@Resource
	private IWorkshopAuthorizeService workshopAuthorizeService;
	@Resource
	private IFileService fileService;
	
	
	public void setWorkshopDao(IWorkshopDao workshopDao) {
		this.workshopDao = workshopDao;
	}

	public void setWorkshopRelationService(IWorkshopRelationService workshopRelationService) {
		this.workshopRelationService = workshopRelationService;
	}

	public void setWorkshopAuthorizeService(IWorkshopAuthorizeService workshopAuthorizeService) {
		this.workshopAuthorizeService = workshopAuthorizeService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	@Override
	public Response create(Workshop obj) {
		return BaseServiceUtils.create(obj, (MybatisDao) workshopDao);
	}

	@Override
	public Response update(Workshop obj) {
		return BaseServiceUtils.update(obj, (MybatisDao) workshopDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao) workshopDao);
	}

	@Override
	public Workshop get(String id) {
		return (Workshop) BaseServiceUtils.get(id, (MybatisDao) workshopDao);
	}

	@Override
	public List<Workshop> list(SearchParam searchParam, PageBounds pageBounds) {
		List<Workshop> workshops = workshopDao.selectWorkshop(searchParam.getParamMap(), pageBounds);
		if (Collections3.isNotEmpty(workshops)) {
			Map<String, Workshop> workshopMap = Collections3.extractToMap(workshops, "id", null);
			List<String> ids = Collections3.extractToList(workshops, "id");
			SearchParam sp = new SearchParam();
			sp.getParamMap().put("workshopIds", ids);
			sp.getParamMap().put("role", WorkshopAuthorizeRole.MASTER.toString());
			sp.getParamMap().put("state", WorkshopAuthorizeState.PASS.toString());
			List<WorkshopAuthorize> workshopAuthorizes = workshopAuthorizeService.list(sp, null);
			if (Collections3.isNotEmpty(workshopAuthorizes)) {
				for (WorkshopAuthorize wa : workshopAuthorizes) {
					workshopMap.get(wa.getWorkshop().getId()).getMasters().add(wa.getUser());
				}
			}
		}
		return workshops;
	}

	@Override
	public Response createWorkshop(Workshop workshop) {
		Response response = this.create(workshop);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(workshop.getWorkshopRelations())) {
				for (WorkshopRelation workshopRelation : workshop.getWorkshopRelations()) {
					workshopRelation.setWorkshop(workshop);
					String id = WorkshopRelation.getId(workshop.getId(), workshopRelation.getRelation().getId());
					workshopRelation.setId(id);
					workshopRelationService.create(workshopRelation);
				}
			}
			workshopAuthorizeService.createWorkshopAuthorize(workshop);
//			if (workshop.getImage() != null) {
//				response = fileService.createFile(workshop.getImage(), workshop.getId(),"workshop");
//				
//			}
		}
		return response;
	}

	@Override
	public List<Workshop> listByRole(SearchParam searchParam, PageBounds pageBounds) {
		return workshopDao.selectByRole(searchParam.getParamMap(), pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> paramMap) {
		return workshopDao.getCount(paramMap);
	}

	@Override
	public Workshop viewWorkshop(Workshop workshop) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("id", workshop.getId());
		if(ThreadContext.getUser() != null){
			param.put("userId", ThreadContext.getUser().getId());
		}
		return workshopDao.selectOne(param);
	}

	@Override
	public Response updateWorkshop(Workshop workshop) {
//		Workshop ws = this.get(workshop.getId());
		Response response = this.update(workshop);
//		if (response.isSuccess()) {
//			response = fileService.updateFile(workshop.getImage(), ws.getImage(), workshop.getId(), "workshop");
//		}
		return response;
	}

	@Override
	public Response deleteWorkshop(Workshop workshop) {
		String[] idArray = workshop.getId().split(",");
		List<String> ids = Arrays.asList(idArray);
		int count = workshopDao.deleteByIds(ids);
		return count>0?Response.successInstance():Response.failInstance();
	}
}
