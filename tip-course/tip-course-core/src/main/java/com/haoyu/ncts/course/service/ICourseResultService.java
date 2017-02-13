package com.haoyu.ncts.course.service;

import java.util.Map;

import com.haoyu.ncts.course.entity.CourseResult;

public interface ICourseResultService {

	Map<String, CourseResult> mapCourseResult(Map<String, Object> param);

	int getCount(Map<String, Object> param);
	
}
