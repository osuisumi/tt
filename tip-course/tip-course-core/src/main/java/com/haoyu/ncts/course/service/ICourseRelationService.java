package com.haoyu.ncts.course.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseRelation;
import com.haoyu.sip.core.service.Response;

public interface ICourseRelationService {

	CourseRelation getCourseRelation(String id);
	
	Response createCourseRelation(CourseRelation courseRelation);
	
	Response updateCourseRelation(CourseRelation courseRelation);
	
	Response deleteCourseRelation(CourseRelation courseRelation);
	
	List<CourseRelation> listCourseRelations(Map<String,Object> parameter,PageBounds pageBounds);
	
	List<CourseRelation> listCourseRelations(CourseRelation courseRelation,PageBounds pageBounds);
	
}
