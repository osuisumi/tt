package com.haoyu.tip.train.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.train.entity.TrainAuthorize;

public interface ITrainAuthorizeDao {

	TrainAuthorize selectTrainAuthorizeById(String id);

	int insertTrainAuthorize(TrainAuthorize TrainAuthorize);

	int updateTrainAuthorize(TrainAuthorize TrainAuthorize);

	int deleteTrainAuthorizeByLogic(Map<String,Object> parameter);

	int deleteTrainAuthorizeByPhysics(String id);

	List<TrainAuthorize> findAll(Map<String, Object> parameter);

	List<TrainAuthorize> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}