package com.haoyu.tip.course.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.tip.course.mobile.service.IMCourseStatService;

@RestController
@RequestMapping("**/m/course_stat")
public class MCourseStatController extends AbstractBaseMobileController{
	
	@Resource
	private IMCourseStatService mCourseStatService;
	
	@RequestMapping(value="{courseId}",method=RequestMethod.GET)
	public Response getCourseStat(@PathVariable String courseId){
		return mCourseStatService.getCourseStat(courseId);
	}
	

}
