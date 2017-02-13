package com.haoyu.tip.train.dao.impl.mybatis;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.train.dao.ITrainAuthorizeDao;
import com.haoyu.tip.train.entity.TrainAuthorize;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class TrainAuthorizeDao extends MybatisDao implements ITrainAuthorizeDao {

	@Override
	public TrainAuthorize selectTrainAuthorizeById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertTrainAuthorize(TrainAuthorize TrainAuthorize) {
		TrainAuthorize.setDefaultValue();
		return super.insert(TrainAuthorize);
	}

	@Override
	public int updateTrainAuthorize(TrainAuthorize TrainAuthorize) {
		TrainAuthorize.setUpdateValue();
		return super.update(TrainAuthorize);
	}

	@Override
	public int deleteTrainAuthorizeByLogic(Map<String,Object> parameter) {
		return super.deleteByLogic(parameter);
	}

	@Override
	public int deleteTrainAuthorizeByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<TrainAuthorize> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<TrainAuthorize> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	
}
