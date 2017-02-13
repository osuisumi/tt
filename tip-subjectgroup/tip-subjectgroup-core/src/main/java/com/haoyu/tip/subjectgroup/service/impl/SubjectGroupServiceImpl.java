package com.haoyu.tip.subjectgroup.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.subjectgroup.dao.ISubjectGroupDao;
import com.haoyu.tip.subjectgroup.entity.SubjectGroup;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupAuthorize;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupRelation;
import com.haoyu.tip.subjectgroup.event.CreateSubjectGroupEvent;
import com.haoyu.tip.subjectgroup.event.DeleteSubjectGroupEvent;
import com.haoyu.tip.subjectgroup.service.ISubjectGroupAuthorizeService;
import com.haoyu.tip.subjectgroup.service.ISubjectGroupRelationService;
import com.haoyu.tip.subjectgroup.service.ISubjectGroupService;
import com.haoyu.tip.subjectgroup.utils.SubjectGroupAuthorizeRole;
import com.haoyu.tip.subjectgroup.utils.SubjectGroupAuthorizeState;

@Service
public class SubjectGroupServiceImpl implements ISubjectGroupService {

	@Resource
	private ISubjectGroupDao subjectGroupDao;
	@Resource
	private ISubjectGroupRelationService subjectGroupRelationService;
	@Resource
	private ISubjectGroupAuthorizeService subjectGroupAuthorizeService;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(SubjectGroup obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)subjectGroupDao);
	}

	@Override
	public Response update(SubjectGroup obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)subjectGroupDao);
	}

	@Override
	public Response delete(String id) {
		Response response = BaseServiceUtils.delete(id, (MybatisDao)subjectGroupDao);
		if (response.isSuccess()) {
			applicationContext.publishEvent(new DeleteSubjectGroupEvent(id));
		}
		return response;
	}

	@Override
	public SubjectGroup get(String id) {
		return (SubjectGroup) BaseServiceUtils.get(id, (MybatisDao)subjectGroupDao);
	}

	@Override
	public List<SubjectGroup> list(SearchParam searchParam, PageBounds pageBounds) {
		List<SubjectGroup> subjectGroups = subjectGroupDao.selectSubjectGroup(searchParam.getParamMap(), pageBounds);
		if (Collections3.isNotEmpty(subjectGroups)) {
			Map<String, SubjectGroup> subjectGroupMap = Collections3.extractToMap(subjectGroups, "id", null);
			List<String> ids = Collections3.extractToList(subjectGroups, "id");
			SearchParam sp = new SearchParam();
			sp.getParamMap().put("subjectGroupIds", ids);
			sp.getParamMap().put("role", SubjectGroupAuthorizeRole.MASTER.toString());
			sp.getParamMap().put("state", SubjectGroupAuthorizeState.PASS.toString());
			List<SubjectGroupAuthorize> subjectGroupAuthorizes = subjectGroupAuthorizeService.list(sp, null);
			if (Collections3.isNotEmpty(subjectGroupAuthorizes)) {
				for (SubjectGroupAuthorize sa : subjectGroupAuthorizes) {
					subjectGroupMap.get(sa.getSubjectGroupId()).getMasters().add(sa.getUser());
				}
			}
		}
		return subjectGroups;
	}

	@Override
	public Response createSubjectGroup(SubjectGroup subjectGroup) {
		Response response = this.create(subjectGroup);
		if (response.isSuccess() && Collections3.isNotEmpty(subjectGroup.getSubjectGroupRelations())) {
			for (SubjectGroupRelation subjectGroupRelation : subjectGroup.getSubjectGroupRelations()) {
				if (subjectGroupRelation.getSubjectGroup() == null || StringUtils.isEmpty(subjectGroupRelation.getSubjectGroup().getId())) {
					subjectGroupRelation.setSubjectGroup(subjectGroup);
				}
				String id = SubjectGroupRelation.getId(subjectGroupRelation.getSubjectGroup().getId(), subjectGroupRelation.getRelation().getId());
				subjectGroupRelation.setId(id);
				try {
					subjectGroupRelationService.create(subjectGroupRelation);
				} catch (DuplicateKeyException e) {
					
				}
			}
			applicationContext.publishEvent(new CreateSubjectGroupEvent(subjectGroup));
		}
		return response;
	}

	@Override
	public List<SubjectGroup> listByRole(SearchParam searchParam, PageBounds pageBounds) {
		return subjectGroupDao.selectByRole(searchParam.getParamMap(), pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> paramMap) {
		return subjectGroupDao.getCount(paramMap);
	}
}
