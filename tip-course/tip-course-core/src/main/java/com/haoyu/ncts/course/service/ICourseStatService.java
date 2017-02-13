package com.haoyu.ncts.course.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseStat;

public interface ICourseStatService {
	
	List<CourseStat> findCourseStats(Map<String,Object> parameter,PageBounds pageBounds);

}
