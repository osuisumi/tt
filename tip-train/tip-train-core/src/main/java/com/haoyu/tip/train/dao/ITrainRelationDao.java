package com.haoyu.tip.train.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.train.entity.TrainRelation;

public interface ITrainRelationDao {
	TrainRelation selectTrainRelationById(String id);

	int insertTrainRelation(TrainRelation trainRegister);

	int updateTrainRelation(TrainRelation trainRegister);

	int deleteTrainRelationByLogic(TrainRelation trainRegister);

	int deleteTrainRelationByPhysics(String id);
	
	int batchDeleteByPhysics(List<String> ids);

	List<TrainRelation> findAll(Map<String, Object> parameter);

	List<TrainRelation> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
