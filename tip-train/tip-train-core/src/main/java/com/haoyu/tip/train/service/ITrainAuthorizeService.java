package com.haoyu.tip.train.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.train.entity.TrainAuthorize;
import com.haoyu.sip.core.service.Response;

public interface ITrainAuthorizeService {
	
	Response createTrainAuthorize(TrainAuthorize TrainAuthorize);
	
	Response updateTrainAuthorize(TrainAuthorize TrainAuthorize);
	
	Response deleteTrainAuthorize(TrainAuthorize TrainAuthorize);
	
	TrainAuthorize findTrainAuthorizeById(String id);
	
	List<TrainAuthorize> findTrainAuthorizes(TrainAuthorize TrainAuthorize,PageBounds pageBounds);
	
	List<TrainAuthorize> findTrainAuthorizes(Map<String,Object> parameter,PageBounds pageBounds);
}
