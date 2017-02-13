package com.haoyu.tip.plan.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.tip.plan.dao.IPlanDao;
import com.haoyu.tip.plan.entity.Plan;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class PlanDao extends MybatisDao implements IPlanDao{

	@Override
	public int selectCount(Map<String, Object> param) {
		return selectOne("selectCount", param);
	}

	@Override
	public int updateByIdNotSelective(Plan obj) {
		return this.update("updateByIdNotSelective", obj);
	}

	@Override
	public List<Plan> selectPlan(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

}
