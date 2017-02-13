package com.haoyu.tip.course.mobile.service;

import com.haoyu.ncts.course.entity.CourseRegister;
import com.haoyu.sip.core.service.Response;

public interface IMCourseRegisterService {
	
	public Response create(CourseRegister courseRegister);

}
