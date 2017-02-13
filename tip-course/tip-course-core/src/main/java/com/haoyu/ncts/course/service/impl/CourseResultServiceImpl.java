package com.haoyu.ncts.course.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoyu.ncts.course.dao.ICourseResultDao;
import com.haoyu.ncts.course.entity.CourseResult;
import com.haoyu.ncts.course.service.ICourseResultService;

@Service
public class CourseResultServiceImpl implements ICourseResultService{

	@Resource
	private ICourseResultDao courseResultDao;
	
	@Override
	public Map<String, CourseResult> mapCourseResult(Map<String, Object> param) {
		return courseResultDao.selectForMap(param);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return courseResultDao.getCount(param);
	}

}
