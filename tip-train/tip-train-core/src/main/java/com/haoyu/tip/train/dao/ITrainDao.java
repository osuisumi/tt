package com.haoyu.tip.train.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.train.entity.Train;

public interface ITrainDao {
	
	Train selectTrainById(String id);
	
	int insertTrain(Train train);
	
	int updateTrain(Train train);
	
	int deleteTrainByLogic(Map<String,Object> param);
	
	int deleteTrainByPhysics(String id);

	List<Train> findAll(Map<String, Object> parameter);
	
	List<Train> findAll(Map<String, Object> parameter, PageBounds pageBounds);

	Map<String, Train> selectForMap(Map<String, Object> parameter);

}
