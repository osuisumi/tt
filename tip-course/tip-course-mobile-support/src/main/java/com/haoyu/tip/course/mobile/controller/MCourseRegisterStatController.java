package com.haoyu.tip.course.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.tip.course.mobile.service.IMCourseRegisterStatService;

@RestController
@RequestMapping("**/m/course_register_stat")
public class MCourseRegisterStatController extends AbstractBaseMobileController{
	
	@Resource
	private IMCourseRegisterStatService mCourseRegisterService;
	
	@RequestMapping(value="{courseId}",method=RequestMethod.GET)
	public Response listCourseRegisterStat(@PathVariable String courseId,String courseResultState){
		PageBounds pageBounds = getPageBounds(10, true);
		return mCourseRegisterService.listCourseRegisterStat(courseId, courseResultState, pageBounds);
	}

}
