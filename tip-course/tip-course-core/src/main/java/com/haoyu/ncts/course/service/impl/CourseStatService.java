package com.haoyu.ncts.course.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseStatDao;
import com.haoyu.ncts.course.entity.CourseStat;
import com.haoyu.ncts.course.service.ICourseStatService;

@Service
public class CourseStatService implements ICourseStatService{
	
	@Resource
	private ICourseStatDao courseStatDao;

	@Override
	public List<CourseStat> findCourseStats(Map<String, Object> parameter, PageBounds pageBounds) {
		return courseStatDao.findCourseStats(parameter, pageBounds);
	}

}
