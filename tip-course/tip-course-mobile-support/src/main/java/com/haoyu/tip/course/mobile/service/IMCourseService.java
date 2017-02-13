package com.haoyu.tip.course.mobile.service;

import com.haoyu.ncts.course.entity.Course;
import com.haoyu.sip.core.service.Response;

public interface IMCourseService {

	Response studyCourse(Course course);
	/*
	 * 获取当前登录用户的课程学习进度
	 */
	Response getStudyProgress(Course course);
	
}
