package com.haoyu.tip.plan.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.plan.entity.Plan;

public interface IPlanDao {

	int selectCount(Map<String, Object> param);

	int updateByIdNotSelective(Plan obj);

	List<Plan> selectPlan(Map<String, Object> paramMap, PageBounds pageBounds);
   
}