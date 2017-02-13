package com.haoyu.ncts.course.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseRegister;

public interface ICourseRegisterDao {
	
	List<CourseRegister> select(Map<String, Object> params, PageBounds pageBounds);

	int insert(CourseRegister courseRegister);
	
	int update(Map<String,Object> param);

	int deleteByLogic(Map<String, Object> param);
	
	int delete(Map<String,Object> param);
	
	int deleteByPhysics(String id);

	CourseRegister selectByPrimaryKey(String id);

	Map<String, Integer> mapCount(Map<String, Object> parameter);

	int getCount(Map<String, Object> param);

}
