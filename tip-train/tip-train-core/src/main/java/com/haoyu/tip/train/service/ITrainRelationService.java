package com.haoyu.tip.train.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.train.entity.TrainRelation;

public interface ITrainRelationService {
	Response createTrainRelation(TrainRelation trainRelation);
	
	Response createTrainRelations(List<TrainRelation> trainRelations);

	Response updateTrainRelation(TrainRelation trainRelation);
	
	Response updateTrainRelations(String trainId,List<String> relationIds,String relationType);

	Response deleteTrainRelation(TrainRelation trainRelation);
	
	Response deleteTrainRelations(List<TrainRelation> trainRelations);

	TrainRelation findTrainRelationById(String id);

	List<TrainRelation> findTrainRelations(TrainRelation trainRelation);

	List<TrainRelation> findTrainRelations(TrainRelation trainRelation, PageBounds pageBounds);

	List<TrainRelation> findTrainRelations(Map<String, Object> parameter);

	List<TrainRelation> findTrainRelations(Map<String, Object> parameter, PageBounds pageBounds);

}
