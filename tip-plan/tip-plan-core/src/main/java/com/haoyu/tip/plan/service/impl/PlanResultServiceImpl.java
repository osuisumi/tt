package com.haoyu.tip.plan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.tip.plan.dao.IPlanResultDao;
import com.haoyu.tip.plan.entity.PlanResult;
import com.haoyu.tip.plan.service.IPlanResultService;

@Service
public class PlanResultServiceImpl implements IPlanResultService{
	
	@Resource
	private IPlanResultDao planResultDao;
	@Resource
	private IFileService fileService;
	
	@Override
	public Response create(PlanResult obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)planResultDao);
	}

	@Override
	public Response update(PlanResult obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)planResultDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)planResultDao);
	}

	@Override
	public PlanResult get(String id) {
		return (PlanResult) BaseServiceUtils.get(id, (MybatisDao)planResultDao);
	}

	@Override
	public Response createPlanResult(PlanResult planResult) {
		planResult.setFileNum(planResult.getFileInfos().size());
		Response response = this.create(planResult);
		if (response.isSuccess()) {
			fileService.createFileList(planResult.getFileInfos(), planResult.getId(), "plan_result");
		}
		return response;
	}

	@Override
	public PlanResult viewPlanResult(PlanResult planResult) {
		planResult = this.get(planResult.getId());
		if (planResult != null) {
			PlanResult pr = new PlanResult();
			pr.setId(planResult.getId());
			pr.setBrowseNum(1);
			Response response = this.update(pr);
			if (response.isSuccess()) {
				if (planResult.getFileNum() > 0) {
					planResult.setFileInfos(fileService.listFileInfoByRelationId(planResult.getId()));
				}
			}
		}
		return planResult;
	}

	@Override
	public List<PlanResult> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)planResultDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

}
