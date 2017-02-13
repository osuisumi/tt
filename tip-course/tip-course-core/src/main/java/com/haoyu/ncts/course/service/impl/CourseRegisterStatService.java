package com.haoyu.ncts.course.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseRegisterStatDao;
import com.haoyu.ncts.course.entity.CourseRegisterStat;
import com.haoyu.ncts.course.service.ICourseRegisterStatService;

@Service
public class CourseRegisterStatService implements ICourseRegisterStatService{
	
	@Resource
	private ICourseRegisterStatDao courseRegisterStatDao;

	@Override
	public List<CourseRegisterStat> findCourseRegisterStats(Map<String, Object> parameter, PageBounds pageBoudns) {
		return courseRegisterStatDao.findCourseRegisterStats(parameter, pageBoudns);
	}
	
	

}
