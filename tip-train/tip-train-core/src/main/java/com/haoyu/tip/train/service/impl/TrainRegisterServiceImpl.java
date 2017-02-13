package com.haoyu.tip.train.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.train.dao.ITrainRegisterDao;
import com.haoyu.tip.train.entity.TrainRegister;
import com.haoyu.tip.train.service.ITrainRegisterService;

@Service
public class TrainRegisterServiceImpl implements ITrainRegisterService{
	
	@Resource
	private ITrainRegisterDao trainRegisterDao;

	@Override
	public Response createTrainRegister(TrainRegister trainRegister) {
		if(trainRegister==null){
			return Response.failInstance().responseMsg("createTrainRegister fail!trainRegister is null!");
		}
		if(StringUtils.isEmpty(trainRegister.getId())){
			trainRegister.setId(Identities.uuid2());
		}
		int count = trainRegisterDao.insertTrainRegister(trainRegister);
		return count>0?Response.successInstance().responseData(trainRegister):Response.failInstance().responseMsg("createTrainRegister fail!");
	}
	
	@Override
	public Response createTrainRegisters(List<TrainRegister> trainRegisters) {
		for(TrainRegister tr:trainRegisters){
			if(tr.getTrain() == null || tr.getUser() == null ){
				return Response.failInstance().responseMsg("create trainRegisters fail! train,user must not be null");
			}
			if(StringUtils.isAnyEmpty(tr.getTrain().getId(),tr.getUser().getId(),tr.getState())){
				return Response.failInstance().responseMsg("create trainRegisters fail! train.id,user.id,state must not be null");
			}
			if(StringUtils.isEmpty(tr.getId())){
				tr.setId(Identities.uuid2());
			}
		}
		return this.trainRegisterDao.insertTrainRegisters(trainRegisters)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateTrainRegister(TrainRegister trainRegister) {
		if(trainRegister==null||StringUtils.isEmpty(trainRegister.getId())){
			return Response.failInstance().responseMsg("updateTrainRegister is fail!trainRegister is null or trainRegister's id is null");
		}
		trainRegister.setUpdateValue();
		Map<String,Object> param = Maps.newHashMap();
		param.put("ids", Arrays.asList(trainRegister.getId().split(",")));
		param.put("entity",trainRegister);
		int count = trainRegisterDao.updateTrainRegister(param);
		return count>0?Response.successInstance().responseData(trainRegister):Response.failInstance().responseMsg("updateTrainRegister fail!");
	}

	@Override
	public Response deleteTrainRegister(TrainRegister trainRegister) {
		if(trainRegister==null||StringUtils.isEmpty(trainRegister.getId())){
			return Response.failInstance().responseMsg("deleteTrainRegister is fail!trainRegister is null or trainRegister's id is null");
		}
		Map<String,Object> param = Maps.newHashMap();
		List<String> ids = Arrays.asList(trainRegister.getId().split(","));
		param.put("ids",ids);
		trainRegister.setUpdateValue();
		param.put("entity",trainRegister);
		int count = trainRegisterDao.deleteTrainRegisterByLogic(param);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteTrainRegister fail!");
	}
	
	@Override
	public TrainRegister findTrainRegisterById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return trainRegisterDao.selectTrainRegisterById(id);
	}

	@Override
	public List<TrainRegister> findTrainRegisters(TrainRegister trainRegister) {
		return findTrainRegisters(trainRegister,null);
	}

	@Override
	public List<TrainRegister> findTrainRegisters(TrainRegister trainRegister, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(trainRegister!=null){
			if(StringUtils.isNotEmpty(trainRegister.getState())){
				parameter.put("state", trainRegister.getState());
			}
		}
		return trainRegisterDao.findAll(parameter,pageBounds);
	}

	@Override
	public List<TrainRegister> findTrainRegisters(Map<String, Object> parameter) {
		return trainRegisterDao.findAll(parameter);
	}

	@Override
	public List<TrainRegister> findTrainRegisters(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return trainRegisterDao.findAll(parameter,pageBounds);
	}


}
