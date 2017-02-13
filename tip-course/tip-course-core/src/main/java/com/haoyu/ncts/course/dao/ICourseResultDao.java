package com.haoyu.ncts.course.dao;

import java.util.Map;

import com.haoyu.ncts.course.entity.CourseResult;

public interface ICourseResultDao {

	int update(CourseResult courseResult);

	int insert(CourseResult courseResult);

	Map<String, CourseResult> selectForMap(Map<String, Object> param);

	int getCount(Map<String, Object> param);

}
