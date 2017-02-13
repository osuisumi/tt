package com.haoyu.tip.train.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.train.entity.Train;
import com.haoyu.tip.train.web.param.TrainParam;

public interface ITrainService {

	Response createTrain(Train train);

	Response updateTrain(Train train);

	Response deleteTrain(Train train);

	Train findTrainById(String id);

	List<Train> findTrains(TrainParam train);

	List<Train> findTrains(TrainParam train, PageBounds pageBounds);

	List<Train> findTrains(Map<String, Object> parameter);

	List<Train> findTrains(Map<String, Object> parameter, PageBounds pageBounds);

	Map<String, Train> mapTrain(Map<String, Object> parameter);
	
}
