package com.haoyu.tip.course.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.ncts.course.entity.Course;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.tip.course.mobile.service.IMCourseService;

@Controller
@RequestMapping("**/m/course")
public class MCourseController extends AbstractBaseController{
	
	@Resource
	private IMCourseService courseMobileService;
	
	@RequestMapping("{id}/study")
	@ResponseBody
	public Response studyCourse(Course course){
		return courseMobileService.studyCourse(course);
	}
	
	@RequestMapping("{id}/teach")
	@ResponseBody
	public Response teachCourse(Course course){
		return courseMobileService.studyCourse(course);
	}
	
	@RequestMapping("{id}/study_progress")
	@ResponseBody
	public Response studyProgress(Course course){
		return courseMobileService.getStudyProgress(course);
	}

}
