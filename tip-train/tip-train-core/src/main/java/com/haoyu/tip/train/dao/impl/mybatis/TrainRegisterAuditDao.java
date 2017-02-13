package com.haoyu.tip.train.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.train.dao.ITrainRegisterAuditDao;
import com.haoyu.tip.train.entity.TrainRegisterAudit;
@Repository
public class TrainRegisterAuditDao extends MybatisDao implements ITrainRegisterAuditDao{

	@Override
	public TrainRegisterAudit selectTrainRegisterAuditById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit) {
		trainRegisterAudit.setDefaultValue();
		return super.insert(trainRegisterAudit);
	}

	@Override
	public int updateTrainRegisterAudit(TrainRegisterAudit trainRegisterAudit) {
		trainRegisterAudit.setUpdateValue();
		return super.update(trainRegisterAudit);
	}

	@Override
	public int deleteTrainRegisterAuditByLogic(TrainRegisterAudit trainRegisterAudit) {
		trainRegisterAudit.setUpdateValue();
		return super.deleteByLogic(trainRegisterAudit);
	}

	@Override
	public int deleteTrainRegisterAuditByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<TrainRegisterAudit> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<TrainRegisterAudit> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter",parameter,pageBounds);
	}

}
