package com.haoyu.ncts.course.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseRegisterStat;

public interface ICourseRegisterStatService {
	
//	Map<String,CourseRegisterStat> selectCourseRegisterStatMap(Map<String,Object> parameter,PageBounds pageBounds);
	List<CourseRegisterStat> findCourseRegisterStats(Map<String,Object> parameter,PageBounds pageBoudns);

}
