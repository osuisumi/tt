package com.haoyu.ncts.course.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseRegisterStat;

public interface ICourseRegisterStatDao {
	
	List<CourseRegisterStat> findCourseRegisterStats(Map<String,Object> parameter,PageBounds pageBounds);

}
