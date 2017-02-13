package com.haoyu.tip.train.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.train.entity.TrainRegisterAudit;

public interface ITrainRegisterAuditDao {
	
	TrainRegisterAudit selectTrainRegisterAuditById(String id);
	
	int insertTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit);
	
	int updateTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit);
	
	int deleteTrainRegisterAuditByLogic(TrainRegisterAudit trainRegisterAudit);
	
	int deleteTrainRegisterAuditByPhysics(String id);

	List<TrainRegisterAudit> findAll(Map<String, Object> parameter);
	
	List<TrainRegisterAudit> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
