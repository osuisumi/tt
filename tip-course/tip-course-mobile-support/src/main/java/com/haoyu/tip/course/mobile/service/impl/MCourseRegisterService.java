package com.haoyu.tip.course.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.ncts.course.entity.CourseRegister;
import com.haoyu.ncts.course.entity.CourseRegisterStat;
import com.haoyu.ncts.course.service.ICourseRegisterService;
import com.haoyu.ncts.course.service.ICourseService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.tip.course.mobile.entity.MCourseRegister;
import com.haoyu.tip.course.mobile.service.IMCourseRegisterService;

@Service
public class MCourseRegisterService implements IMCourseRegisterService{
	@Resource
	private ICourseService courseService;
	@Resource
	private ICourseRegisterService courseRegisterService;

	@Override
	public Response create(CourseRegister courseRegister) {
		//设置courseRegister的relationid
		if(courseRegister.getRelation() == null || StringUtils.isEmpty(courseRegister.getRelation().getId())){
			if(courseRegister.getCourse()!=null&&StringUtils.isNotEmpty(courseRegister.getCourse().getId())){
				Course course = courseService.getCourse(courseRegister.getCourse().getId());
				if(course != null && course.getCourseRelation()!=null && course.getCourseRelation().getRelation()!= null && StringUtils.isNotEmpty(course.getCourseRelation().getRelation().getId())){
					courseRegister.setRelation(new Relation(course.getCourseRelation().getRelation().getId()));
				}
			}
		}
		if(StringUtils.isEmpty(courseRegister.getId())){
			courseRegister.setId(CourseRegister.getId(courseRegister.getCourse().getId(), ThreadContext.getUser().getId()));
		}
		MCourseRegister mCourseRegister = new MCourseRegister();
		BeanUtils.copyProperties(courseRegister, mCourseRegister);
		Response response = courseRegisterService.createCourseRegister(courseRegister);
		if(response.isSuccess()){
			response.setResponseData(mCourseRegister);
		}
		return response;
	}


}
