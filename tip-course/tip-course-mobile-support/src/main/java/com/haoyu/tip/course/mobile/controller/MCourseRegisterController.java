package com.haoyu.tip.course.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseRegister;
import com.haoyu.ncts.course.service.ICourseRegisterService;
import com.haoyu.ncts.course.utils.CourseRegisterState;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.tip.course.mobile.service.IMCourseRegisterService;

@RestController
@RequestMapping("**/m/course_register")
public class MCourseRegisterController extends AbstractBaseMobileController{
	@Resource
	private ICourseRegisterService courseRegisterService;
	@Resource
	private IMCourseRegisterService mCourseRegisterService;
	
	@RequestMapping(method=RequestMethod.POST)
	public Response createCourseRegister(CourseRegister courseRegister){
		courseRegister.setState(CourseRegisterState.SUBMIT);
		courseRegister.setUser(new User(ThreadContext.getUser().getId()));
		return mCourseRegisterService.create(courseRegister);
	}
	
	@RequestMapping(value="delete/{courseRegisterId}",method=RequestMethod.DELETE)
	public Response deleteCourseRegister(@PathVariable String courseRegisterId){
		return courseRegisterService.deleteCourseRegisterByPhysics(courseRegisterId);
	}
	

}
