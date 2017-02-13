package com.haoyu.tip.train.dao.impl.mybatis;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.train.dao.ITrainRegisterDao;
import com.haoyu.tip.train.entity.TrainRegister;
@Repository
public class TrainRegisterDao extends MybatisDao implements ITrainRegisterDao{

	@Override
	public TrainRegister selectTrainRegisterById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertTrainRegister(TrainRegister trainRegister) {
		trainRegister.setDefaultValue();
		return super.insert(trainRegister);
	}

	@Override
	public int updateTrainRegister(Map<String,Object> param) {
		return super.update("update",param);
	}

	@Override
	public int deleteTrainRegisterByLogic(Map<String,Object> param) {
		if(!param.containsKey("entity")){
			TrainRegister entity = new TrainRegister();
			entity.setUpdateValue();
			param.put("entity", entity);
		}
		return super.deleteByLogic(param);
	}
	

	@Override
	public int deleteTrainRegisterByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<TrainRegister> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter",parameter);
	}

	@Override
	public List<TrainRegister> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter",parameter,pageBounds);
	}

	@Override
	public int insertTrainRegisters(List<TrainRegister> trainRegisters) {
		return super.insert("batchInsert", trainRegisters);
	}

}
