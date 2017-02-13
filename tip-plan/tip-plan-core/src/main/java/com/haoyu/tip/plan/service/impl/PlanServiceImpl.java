package com.haoyu.tip.plan.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.plan.dao.IPlanDao;
import com.haoyu.tip.plan.entity.Plan;
import com.haoyu.tip.plan.entity.PlanRelation;
import com.haoyu.tip.plan.event.CreatePlanEvent;
import com.haoyu.tip.plan.event.DeletePlanEvent;
import com.haoyu.tip.plan.service.IPlanRelationService;
import com.haoyu.tip.plan.service.IPlanService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Collections3;

@Service
public class PlanServiceImpl implements IPlanService{

	@Resource
	private IPlanDao planDao;
	@Resource
	private IPlanRelationService planRelationService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IFileService fileService;

	@Override
	public Response create(Plan obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)planDao);
	}

	@Override
	public Response update(Plan obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)planDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id,(MybatisDao)planDao);
	}

	@Override
	public Plan get(String id) {
		return (Plan) BaseServiceUtils.get(id, (MybatisDao)planDao);
	}
	
	@Override
	public List<Plan> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)planDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response createPlan(Plan plan) {
		Response response = this.create(plan);
		if (response.isSuccess() && Collections3.isNotEmpty(plan.getPlanRelations())) {
			for (PlanRelation planRelation : plan.getPlanRelations()) {
				if (planRelation.getRelation() != null && StringUtils.isNotEmpty(planRelation.getRelation().getId())) {
					if (planRelation.getPlan() == null || StringUtils.isEmpty(planRelation.getPlan().getId())) {
						planRelation.setPlan(plan);
					}
					String id = PlanRelation.getId(planRelation.getPlan().getId(), planRelation.getRelation().getId());
					planRelation.setId(id);
					planRelationService.create(planRelation);
					planRelation.setPlan(null);
				}
			}
		}
		if (response.isSuccess()) {
			response.setResponseData(plan);
			applicationContext.publishEvent(new CreatePlanEvent(plan));
		}
		fileService.createFileList(plan.getFileInfos(), plan.getId(), "plan");
		return response;
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return planDao.selectCount(param);
	}

	@Override
	public Response updateByIdNotSelective(Plan obj) {
		obj.setUpdatedby(ThreadContext.getUser());
		obj.setUpdateTime(System.currentTimeMillis());
		int count = planDao.updateByIdNotSelective(obj);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deletePlan(Plan plan) {
		plan = this.get(plan.getId());
		if(plan!=null){
			Response response = this.delete(plan.getId());
			if (response.isSuccess()) {
				applicationContext.publishEvent(new DeletePlanEvent(plan));
			}
			return response;
		}else{
			return Response.failInstance();
		}

	}

	@Override
	public Response updatePlan(Plan plan) {
		Response response = BaseServiceUtils.update(plan, (MybatisDao)planDao);
		if (response.isSuccess()) {
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(plan.getId());
			response = fileService.updateFileList(plan.getFileInfos(), oldFileInfos, plan.getId(), "plan");
		}
		return response;
	}

	@Override
	public Plan viewPlan(Plan plan) {
		if(StringUtils.isNotEmpty(plan.getId())){
			plan = get(plan.getId());
			if(plan!=null){
				plan.setFileInfos(fileService.listFileInfoByRelationId(plan.getId()));
			}
			return plan;
		}else{
			return null;
		}
		
	}

}
