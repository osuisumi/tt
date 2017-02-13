package com.haoyu.tip.train.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.train.dao.ITrainDao;
import com.haoyu.tip.train.entity.Train;

@Repository
public class TrainDao extends MybatisDao implements ITrainDao {

	@Resource
	private ApplicationContext applicationContext;

	@Override
	public Train selectTrainById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertTrain(Train train) {
		train.setDefaultValue();
		return super.insert(train);
	}

	@Override
	public int updateTrain(Train train) {
		train.setUpdateValue();
		return super.update(train);
	}

	@Override
	public int deleteTrainByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}

	@Override
	public int deleteTrainByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<Train> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<Train> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	@Override
	public Map<String, Train> selectForMap(Map<String, Object> parameter) {
		return super.selectMap("selectByParameter", parameter, "id");
	}


}
