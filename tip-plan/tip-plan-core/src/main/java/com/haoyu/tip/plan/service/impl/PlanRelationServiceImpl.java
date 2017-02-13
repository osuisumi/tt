package com.haoyu.tip.plan.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoyu.tip.plan.dao.IPlanRelationDao;
import com.haoyu.tip.plan.entity.PlanRelation;
import com.haoyu.tip.plan.service.IPlanRelationService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;

@Service
public class PlanRelationServiceImpl implements IPlanRelationService{

	@Resource
	private IPlanRelationDao planRelationDao;
	
	@Override
	public Response create(PlanRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)planRelationDao);
	}

	@Override
	public Response update(PlanRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)planRelationDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id,(MybatisDao)planRelationDao);
	}

	@Override
	public PlanRelation get(String id) {
		return (PlanRelation) BaseServiceUtils.get(id, (MybatisDao)planRelationDao);
	}

}
