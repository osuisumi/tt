package com.haoyu.tip.train.dao.impl.mybatis;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.train.dao.ITrainRelationDao;
import com.haoyu.tip.train.entity.TrainRelation;
@Repository
public class TrainRelationDao extends MybatisDao implements ITrainRelationDao{

	@Override
	public TrainRelation selectTrainRelationById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertTrainRelation(TrainRelation trainRelation) {
		trainRelation.setDefaultValue();
		return super.insert(trainRelation);
	}

	@Override
	public int updateTrainRelation(TrainRelation trainRelation) {
		trainRelation.setUpdateValue();
		return super.update(trainRelation);
	}

	@Override
	public int deleteTrainRelationByLogic(TrainRelation trainRelation) {
		trainRelation.setUpdateValue();
		return super.deleteByLogic(trainRelation);
	}

	@Override
	public int deleteTrainRelationByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<TrainRelation> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter",parameter);
	}

	@Override
	public List<TrainRelation> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter",parameter,pageBounds);
	}

	@Override
	public int batchDeleteByPhysics(List<String> ids) {
		if(CollectionUtils.isEmpty(ids)){
			return 0;
		}
		return super.delete("batchDeleteByPhysics", ids);
	}

}
