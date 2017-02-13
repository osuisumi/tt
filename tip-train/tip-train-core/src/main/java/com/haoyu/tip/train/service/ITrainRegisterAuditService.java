package com.haoyu.tip.train.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.train.entity.TrainRegisterAudit;

public interface ITrainRegisterAuditService {

	Response createTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit);

	Response updateTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit);

	Response deleteTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit);

	TrainRegisterAudit findTrainRegisterAuditById(String id);

	List<TrainRegisterAudit> findTrainRegisterAudits(TrainRegisterAudit trainRegisterAudit);

	List<TrainRegisterAudit> findTrainRegisterAudits(TrainRegisterAudit trainRegisterAudit, PageBounds pageBounds);

	List<TrainRegisterAudit> findTrainRegisterAudits(Map<String, Object> parameter);

	List<TrainRegisterAudit> findTrainRegisterAudits(Map<String, Object> parameter, PageBounds pageBounds);

}
