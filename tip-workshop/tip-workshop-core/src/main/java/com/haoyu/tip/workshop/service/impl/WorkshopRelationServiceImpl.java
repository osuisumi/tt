package com.haoyu.tip.workshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.workshop.dao.IWorkshopRelationDao;
import com.haoyu.tip.workshop.entity.WorkshopRelation;
import com.haoyu.tip.workshop.service.IWorkshopRelationService;

@Service("workshopRelationService")
public class WorkshopRelationServiceImpl implements IWorkshopRelationService{

	@Resource
	private IWorkshopRelationDao workshopRelationDao;
	
	
	public void setWorkshopRelationDao(IWorkshopRelationDao workshopRelationDao) {
		this.workshopRelationDao = workshopRelationDao;
	}

	@Override
	public Response create(WorkshopRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao) workshopRelationDao);
	}

	@Override
	public Response update(WorkshopRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao) workshopRelationDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao) workshopRelationDao);
	}

	@Override
	public WorkshopRelation get(String id) {
		return (WorkshopRelation) BaseServiceUtils.get(id, (MybatisDao) workshopRelationDao);
	}

	@Override
	public List<WorkshopRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao) workshopRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateMemberNum(WorkshopRelation workshopRelation) {
		workshopRelation.setMemberNum(1);
		return this.update(workshopRelation);
	}

	@Override
	public Response updateFollowNum(WorkshopRelation workshopRelation) {
		workshopRelation.setFollowNum(1);
		return this.update(workshopRelation);
	}

	@Override
	public Response updateResourceNum(WorkshopRelation workshopRelation) {
		workshopRelation.setResourceNum(1);
		return this.update(workshopRelation);
	}

	@Override
	public Response updatePlanNum(WorkshopRelation workshopRelation) {
		workshopRelation.setPlanNum(1);
		return this.update(workshopRelation);
	}
}
