package com.haoyu.ncts.course.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseRelation;

public interface ICourseRelationDao {

	CourseRelation selectByPrimaryKey(String id);
	
	int insertCourseRelation(CourseRelation courseRelation);

	int updateCourseRelation(CourseRelation courseRelation);
	
	int deleteCourseRelation(Map<String,Object> parameter);

	List<CourseRelation> findAll(Map<String, Object> parameter, PageBounds pageBounds);


}
