package com.haoyu.ncts.course.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.Course;

public interface ICourseDao {

	List<Course> select(Map<String, Object> params, PageBounds pageBounds);

	Course selectByPrimaryKey(String id);
	
	Course selectByPrimaryKey(String id,String getStat);

	int insert(Course course);

	int update(Course course);

	int deleteByLogic(Map<String, Object> param);

	int deleteByPhysics(List<String> ids);

	int retrieve(Map<String, Object> param);

	int getCount(Map<String, Object> params);

	int updateByIds(Map<String, Object> param);
	
	String getCourseMaxCode();

	List<Course> selectBySource(Map<String, Object> param, PageBounds pageBounds);
	

}
