package com.haoyu.tip.train.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.train.entity.TrainRegister;

public interface ITrainRegisterDao {

	TrainRegister selectTrainRegisterById(String id);

	int insertTrainRegister(TrainRegister trainRegister);
	
	int insertTrainRegisters(List<TrainRegister> trainRegisters);

	int updateTrainRegister(Map<String,Object> param);

	int deleteTrainRegisterByLogic(Map<String,Object> param);

	int deleteTrainRegisterByPhysics(String id);

	List<TrainRegister> findAll(Map<String, Object> parameter);

	List<TrainRegister> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
