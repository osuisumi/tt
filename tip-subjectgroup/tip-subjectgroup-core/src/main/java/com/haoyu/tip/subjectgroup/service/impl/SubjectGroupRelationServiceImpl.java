package com.haoyu.tip.subjectgroup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.subjectgroup.dao.ISubjectGroupRelationDao;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupRelation;
import com.haoyu.tip.subjectgroup.service.ISubjectGroupRelationService;

@Service
public class SubjectGroupRelationServiceImpl implements ISubjectGroupRelationService {

	@Resource
	private ISubjectGroupRelationDao subjectGroupRelationDao;
	
	@Override
	public Response create(SubjectGroupRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)subjectGroupRelationDao);
	}

	@Override
	public Response update(SubjectGroupRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)subjectGroupRelationDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)subjectGroupRelationDao);
	}

	@Override
	public SubjectGroupRelation get(String id) {
		return (SubjectGroupRelation) BaseServiceUtils.get(id, (MybatisDao)subjectGroupRelationDao);
	}

	@Override
	public List<SubjectGroupRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)subjectGroupRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateMemberNum(SubjectGroupRelation subjectGroupRelation) {
		subjectGroupRelation.setMemberNum(1);
		return this.update(subjectGroupRelation);
	}

	@Override
	public Response updatePlanNum(SubjectGroupRelation subjectGroupRelation) {
		subjectGroupRelation.setPlanNum(1);
		return this.update(subjectGroupRelation);
	}
}
