package com.haoyu.ncts.course.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseRegister;
import com.haoyu.sip.core.service.Response;
@Service
public interface ICourseRegisterService {
	
	List<CourseRegister> listCourseRegister(CourseRegister courseRegister,PageBounds pageBounds);
	
	List<CourseRegister> listCourseRegister(Map<String, Object> params, PageBounds pageBounds);
	
	Response createCourseRegister(CourseRegister courseRegister);
	
	Response updateCourseRegister(CourseRegister courseRegister);
	
	Response updateCourseRegisterState(CourseRegister courseRegister,List<String> userIds);
	
	Response deleteCourseRegisterByLogic(CourseRegister courseRegister);
	
	Response deleteCourseRegisterByPhysics(String id);
	
	Response updateCourseRegister(CourseRegister courseRegister,List<String> userIds);

	CourseRegister getCourseRegister(String id);

	Map<String, Integer> mapCourseRegisterNum(Map<String, Object> parameter);

	Response deleteCourseRegisterByParam(Map<String, Object> parameter);

	int getCount(Map<String, Object> param);
	
}
