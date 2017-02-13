package com.haoyu.ncts.course.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseAuthorize;
import com.haoyu.sip.core.service.Response;

public interface ICourseAuthorizeService {
	
	List<CourseAuthorize> listCourseAuthorize(Map<String, Object> param, PageBounds pageBounds);
	
	Response updateCourseAuthorizeByCourseId(String courseId, String role, List<CourseAuthorize> courseAuthorizes);

	Response updateCourseAuthorizeByUserId(String userId, String role, List<CourseAuthorize> courseAuthorizes);
	
	Response createCourseAuthorize(CourseAuthorize courseAuthorize, String userName);

	Response deleteCourseAuthorizeByLogic(CourseAuthorize courseAuthorize);

	Response createCourseAuthorize(CourseAuthorize courseAuthorize);
	
}
