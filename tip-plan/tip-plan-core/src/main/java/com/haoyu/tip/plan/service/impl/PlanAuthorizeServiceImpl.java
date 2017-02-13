package com.haoyu.tip.plan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.plan.dao.IPlanAuthorizeDao;
import com.haoyu.tip.plan.entity.Plan;
import com.haoyu.tip.plan.entity.PlanAuthorize;
import com.haoyu.tip.plan.event.UpdatePlanAuthorizeEvent;
import com.haoyu.tip.plan.service.IPlanAuthorizeService;
import com.haoyu.tip.plan.service.IPlanRelationService;
import com.haoyu.tip.plan.utils.PlanAuthorizeRole;
import com.haoyu.tip.plan.utils.PlanAuthorizeState;

@Service
public class PlanAuthorizeServiceImpl implements IPlanAuthorizeService{

	@Resource
	private IPlanAuthorizeDao planAuthorizeDao;
	@Resource
	private IPlanRelationService planRelationService;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(PlanAuthorize obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)planAuthorizeDao);
	}

	@Override
	public Response update(PlanAuthorize obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)planAuthorizeDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id,(MybatisDao)planAuthorizeDao);
	}

	@Override
	public PlanAuthorize get(String id) {
		return (PlanAuthorize) BaseServiceUtils.get(id, (MybatisDao)planAuthorizeDao);
	}
	
	@Override
	public List<PlanAuthorize> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)planAuthorizeDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response saveMember(Plan plan) {
		PlanAuthorize planAuthorize = plan.getPlanAuthorize();
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("planId", plan.getId());
		searchParam.getParamMap().put("role", planAuthorize.getRole());
		List<PlanAuthorize> planAuthorizes = this.list(searchParam, null);
		List<String> oldUserIds = Lists.newArrayList();
		for (PlanAuthorize pa : planAuthorizes) {
			User user = pa.getUser();
			oldUserIds.add(user.getId());
		}
		planAuthorize.setPlanId(plan.getId());
		List<User> users = null;
		if (PlanAuthorizeRole.MASTER.toString().equals(planAuthorize.getRole())) {
			users = plan.getMasters();
		}else {
			users = plan.getMembers();
		}
		List<String> newUserIds = Collections3.extractToList(users, "id");
		if (Collections3.isNotEmpty(newUserIds) || Collections3.isNotEmpty(oldUserIds)) {
			List<String> addUserIds = Collections3.subtract(newUserIds, oldUserIds);
			List<String> deleteUserIds = Collections3.subtract(oldUserIds, newUserIds);
			if (Collections3.isNotEmpty(deleteUserIds)) {
				List<String> ids = Lists.newArrayList();
				for (String userId : deleteUserIds) {
					String id = PlanAuthorize.getId(plan.getId(), userId);
					ids.add(id);
				}
				planAuthorizeDao.deleteByIds(ids);
			}
			if (Collections3.isNotEmpty(addUserIds)) {
				for (String userId : addUserIds) {
					String id = PlanAuthorize.getId(plan.getId(), userId);
					planAuthorize.setId(id);
					planAuthorize.setUser(new User(userId));
					planAuthorize.setState(PlanAuthorizeState.PASS.toString());
					Response response = this.update(planAuthorize);
					if (!response.isSuccess()) {
						this.create(planAuthorize);
					}
				}
				
			}
			List<String> ids = Collections3.union(addUserIds, deleteUserIds);
			applicationContext.publishEvent(new UpdatePlanAuthorizeEvent(ids));
		}
		return Response.successInstance();
	}

	@Override
	public Response createPlan(PlanAuthorize planAuthorize) {
		Response response = this.create(planAuthorize);
		if (response.isSuccess()) {
			applicationContext.publishEvent(new UpdatePlanAuthorizeEvent(Lists.newArrayList(planAuthorize.getUser().getId())));
		}
		return response;
	}

}
