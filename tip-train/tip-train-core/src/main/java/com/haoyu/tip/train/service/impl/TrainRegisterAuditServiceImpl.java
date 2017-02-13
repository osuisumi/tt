package com.haoyu.tip.train.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.train.dao.ITrainRegisterAuditDao;
import com.haoyu.tip.train.entity.TrainRegisterAudit;
import com.haoyu.tip.train.service.ITrainRegisterAuditService;

@Service
public class TrainRegisterAuditServiceImpl implements ITrainRegisterAuditService{

	@Resource
	private ITrainRegisterAuditDao trainRegisterAuditDao;
	
	@Override
	public Response createTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit) {
		if(trainRegisterAudit==null){
			return Response.failInstance().responseMsg("createTrainRegisterAudit fail!trainRegisterAudit is null!");
		}
		if(StringUtils.isEmpty(trainRegisterAudit.getId())){
			trainRegisterAudit.setId(Identities.uuid2());
		}
		int count = trainRegisterAuditDao.insertTrainRegisterAudit(trainRegisterAudit);
		return count>0?Response.successInstance().responseData(trainRegisterAudit):Response.failInstance().responseMsg("createTrainRegisterAudit fail!");
	}

	@Override
	public Response updateTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit) {
		if(trainRegisterAudit==null||StringUtils.isEmpty(trainRegisterAudit.getId())){
			return Response.failInstance().responseMsg("updateTrainRegisterAudit is fail!trainRegisterAudit is null or trainRegisterAudit's id is null");
		}
		int count = trainRegisterAuditDao.updateTrainRegisterAudit(trainRegisterAudit);
		return count>0?Response.successInstance().responseData(trainRegisterAudit):Response.failInstance().responseMsg("updateTrainRegisterAudit fail!");
	}

	@Override
	public Response deleteTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit) {
		if(trainRegisterAudit==null||StringUtils.isEmpty(trainRegisterAudit.getId())){
			return Response.failInstance().responseMsg("deleteTrainRegisterAudit is fail!trainRegisterAudit is null or trainRegisterAudit's id is null");
		}
		int count = trainRegisterAuditDao.deleteTrainRegisterAuditByLogic(trainRegisterAudit);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteTrainRegisterAudit fail!");
	}

	@Override
	public TrainRegisterAudit findTrainRegisterAuditById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return trainRegisterAuditDao.selectTrainRegisterAuditById(id);
	}

	@Override
	public List<TrainRegisterAudit> findTrainRegisterAudits(TrainRegisterAudit trainRegisterAudit) {
		return findTrainRegisterAudits(trainRegisterAudit,null);
	}

	@Override
	public List<TrainRegisterAudit> findTrainRegisterAudits(TrainRegisterAudit trainRegisterAudit, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(trainRegisterAudit!=null){
		}
		return trainRegisterAuditDao.findAll(parameter,pageBounds);
	}

	@Override
	public List<TrainRegisterAudit> findTrainRegisterAudits(Map<String, Object> parameter) {
		return trainRegisterAuditDao.findAll(parameter);
	}

	@Override
	public List<TrainRegisterAudit> findTrainRegisterAudits(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return trainRegisterAuditDao.findAll(parameter,pageBounds);
	}

}
