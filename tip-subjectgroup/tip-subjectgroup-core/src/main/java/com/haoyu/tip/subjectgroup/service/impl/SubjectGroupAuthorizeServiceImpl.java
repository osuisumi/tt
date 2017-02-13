package com.haoyu.tip.subjectgroup.service.impl;

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
import com.haoyu.tip.subjectgroup.dao.ISubjectGroupAuthorizeDao;
import com.haoyu.tip.subjectgroup.entity.SubjectGroup;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupAuthorize;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupRelation;
import com.haoyu.tip.subjectgroup.event.UpdateSubjectGroupAuthorizeEvent;
import com.haoyu.tip.subjectgroup.service.ISubjectGroupAuthorizeService;
import com.haoyu.tip.subjectgroup.service.ISubjectGroupRelationService;
import com.haoyu.tip.subjectgroup.utils.SubjectGroupAuthorizeRole;
import com.haoyu.tip.subjectgroup.utils.SubjectGroupAuthorizeState;

@Service
public class SubjectGroupAuthorizeServiceImpl implements ISubjectGroupAuthorizeService {
	
	@Resource
	private ISubjectGroupAuthorizeDao subjectGroupAuthorizeDao;
	@Resource
	private PropertiesLoader propertiesLoader;
	@Resource
	private ISubjectGroupRelationService subjectGroupRelationService;
	@Resource  
	private ApplicationContext applicationContext;  

	@Override
	public Response create(SubjectGroupAuthorize obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)subjectGroupAuthorizeDao);
	}

	@Override
	public Response update(SubjectGroupAuthorize obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)subjectGroupAuthorizeDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)subjectGroupAuthorizeDao);
	}

	@Override
	public SubjectGroupAuthorize get(String id) {
		return (SubjectGroupAuthorize) BaseServiceUtils.get(id, (MybatisDao)subjectGroupAuthorizeDao);
	}

	@Override
	public List<SubjectGroupAuthorize> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)subjectGroupAuthorizeDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response deleteByPhysics(SubjectGroupAuthorize subjectGroupAuthorize) {
		int count = subjectGroupAuthorizeDao.deleteByPhysics(subjectGroupAuthorize);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateToMember(SubjectGroupAuthorize subjectGroupAuthorize) {
		subjectGroupAuthorize.setUpdateTime(System.currentTimeMillis());
		int count = subjectGroupAuthorizeDao.updateToMember(subjectGroupAuthorize);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response saveMaster(SubjectGroup subjectGroup, String userId) {
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("subjectGroupId", subjectGroup.getId());
		searchParam.getParamMap().put("role", SubjectGroupAuthorizeRole.MASTER.toString());
		List<SubjectGroupAuthorize> subjectGroupAuthorizes = this.list(searchParam, null);
		List<String> ids = Collections3.extractToList(subjectGroupAuthorizes, "user.id");
		
		SubjectGroupAuthorize subjectGroupAuthorize = new SubjectGroupAuthorize();
		subjectGroupAuthorize.setSubjectGroupId(subjectGroup.getId());
		this.updateToMember(subjectGroupAuthorize);
		if (Collections3.isNotEmpty(subjectGroup.getMasters())) {
			for (User user : subjectGroup.getMasters()) {
				String id = SubjectGroupAuthorize.getId(subjectGroup.getId(), user.getId());
				subjectGroupAuthorize.setId(id);
				subjectGroupAuthorize.setUser(user);
				subjectGroupAuthorize.setRole(SubjectGroupAuthorizeRole.MASTER.toString());
				subjectGroupAuthorize.setState(SubjectGroupAuthorizeState.PASS.toString());
				Response response = this.update(subjectGroupAuthorize);
				if (!response.isSuccess()) {
					try {
						this.create(subjectGroupAuthorize);
					} catch (DuplicateKeyException e) {

					}
					if (!ids.contains(subjectGroupAuthorize.getUser().getId())) {
						ids.add(subjectGroupAuthorize.getUser().getId());
					}
				}
			}
			subjectGroupRelationService.updateMemberNum(subjectGroup.getSubjectGroupRelations().get(0));
		}
		applicationContext.publishEvent(new UpdateSubjectGroupAuthorizeEvent(ids));
		return Response.successInstance();
	}

	@Override
	public Response updateSubjectGroupAuthorize(SubjectGroupAuthorize subjectGroupAuthorize, String subjectGroupRelationId) {
		String[] idArray = subjectGroupAuthorize.getId().split(",");
		if (idArray != null && idArray.length > 0) {
			List<String> ids = Lists.newArrayList();
			for (String id : idArray) {
				if (!ids.contains(id)) {
					ids.add(id);
				}
			}
			Response response = this.updateByIds(ids, subjectGroupAuthorize);
			if (response.isSuccess()) {
				SubjectGroupRelation subjectGroupRelation = new SubjectGroupRelation();
				subjectGroupRelation.setId(subjectGroupRelationId);
				response = subjectGroupRelationService.updateMemberNum(subjectGroupRelation);
				if (response.isSuccess()) {
					applicationContext.publishEvent(new UpdateSubjectGroupAuthorizeEvent(ids));
				}
			}
			return response;
		}
		return Response.failInstance();
	}

	@Override
	public Response updateByIds(List<String> ids, SubjectGroupAuthorize subjectGroupAuthorize) {
		Map<String, Object> param = Maps.newHashMap();
		subjectGroupAuthorize.setUpdateTime(System.currentTimeMillis());
		subjectGroupAuthorize.setUpdatedby(ThreadContext.getUser());
		param.put("ids", ids);
		param.put("entity", subjectGroupAuthorize);
		int count = subjectGroupAuthorizeDao.updateByIds(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response saveMember(SubjectGroup subjectGroup) {
		for (User user : subjectGroup.getMembers()) {
			String id = SubjectGroupAuthorize.getId(subjectGroup.getId(), user.getId());
			SubjectGroupAuthorize subjectGroupAuthorize = new SubjectGroupAuthorize();
			subjectGroupAuthorize.setSubjectGroupId(subjectGroup.getId());
			subjectGroupAuthorize.setId(id);
			subjectGroupAuthorize.setUser(user);
			subjectGroupAuthorize.setRole(SubjectGroupAuthorizeRole.MEMBER.toString());
			subjectGroupAuthorize.setState(SubjectGroupAuthorizeState.PASS.toString());
			try {
				this.create(subjectGroupAuthorize);
			} catch (DuplicateKeyException e) {
				
			}
		}
		List<String> ids = Collections3.extractToList(subjectGroup.getMembers(), "id");
		applicationContext.publishEvent(new UpdateSubjectGroupAuthorizeEvent(ids));
		return subjectGroupRelationService.updateMemberNum(subjectGroup.getSubjectGroupRelations().get(0));
	}

	@Override
	public Response deleteMember(SubjectGroupAuthorize subjectGroupAuthorize, String subjectGroupRelationId) {
		Response response = this.deleteByPhysics(subjectGroupAuthorize);
		if (response.isSuccess()) {
			SubjectGroupRelation subjectGroupRelation = new SubjectGroupRelation();
			subjectGroupRelation.setId(subjectGroupRelationId);
			response = subjectGroupRelationService.updateMemberNum(subjectGroupRelation);
			if (response.isSuccess()) {
				applicationContext.publishEvent(new UpdateSubjectGroupAuthorizeEvent(Lists.newArrayList(subjectGroupAuthorize.getUser().getId())));
			}
		}
		return response;
	}

	@Override
	public List<User> listMemberUser(SearchParam searchParam, PageBounds pageBounds) {
		return subjectGroupAuthorizeDao.selectMemberUser(searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response deleteByUserId(SubjectGroupAuthorize subjectGroupAuthorize) {
		int count = subjectGroupAuthorizeDao.deleteByUserId(subjectGroupAuthorize);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
