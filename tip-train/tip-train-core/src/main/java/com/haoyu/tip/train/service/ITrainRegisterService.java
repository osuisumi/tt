package com.haoyu.tip.train.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.train.entity.TrainRegister;

public interface ITrainRegisterService {

	Response createTrainRegister(TrainRegister trainRegister);
	
	Response createTrainRegisters(List<TrainRegister> trainRegisters);

	Response updateTrainRegister(TrainRegister trainRegister);

	Response deleteTrainRegister(TrainRegister trainRegister);

	TrainRegister findTrainRegisterById(String id);

	List<TrainRegister> findTrainRegisters(TrainRegister trainRegister);

	List<TrainRegister> findTrainRegisters(TrainRegister trainRegister, PageBounds pageBounds);

	List<TrainRegister> findTrainRegisters(Map<String, Object> parameter);

	List<TrainRegister> findTrainRegisters(Map<String, Object> parameter, PageBounds pageBounds);

}
