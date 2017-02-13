package com.haoyu.ncts.course.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.sip.core.service.Response;

public interface ICourseService {
	

	/**
	 * 获取课程列表
	 * @param params
	 * @return
	 */
	List<Course> listCourse(Map<String, Object> params, PageBounds pageBounds);
	
	/**
	 * 获取单个课程
	 * @param id
	 * @return
	 */
	Course getCourse(String id);
	
	Course getCourse(String id,String getStat);
	
	/**
	 * 创建课程
	 * @param course
	 * @return
	 */
	Response createCourse(Course course);
	
	/**
	 * 编辑课程
	 * @param course
	 * @return
	 */
	Response updateCourse(Course course);
	
	/**
	 * 逻辑删除单个或多个课程, 可恢复
	 * 单个则course.id为课程ID
	 * 多个则course.id为多个课程ID组成的字符串, 用逗号(,)分隔
	 * @param course
	 * @return
	 */
	Response deleteCourseByLogic(Course course);
	
	/**
	 * 物理删除单个或多个课程, 不可恢复
	 * 单个则id为课程ID
	 * 多个则id为多个课程ID组成的字符串, 用逗号(,)分隔
	 * @param course
	 * @return
	 */
	Response deleteCourseByPhysics(String id);
	
	/**
	 * 恢复被逻辑删除单个或多个课程
	 * 单个则course.id为课程ID
	 * 多个则course.id为多个课程ID组成的字符串, 用逗号(,)分隔
	 * @param course
	 * @return
	 */
	Response retrieveCourse(Course course);
	
	/**
	 * 获取课程数量
	 * @param param 
	 * @return
	 */
	int getCourseCount(Map<String, Object> params);

	Response updateCourseByIds(Course course);

	List<Course> listCourse(Course course, PageBounds pageBounds);

	List<Activity> listActivity(String id);

	List<Course> listCourseBySource(Map<String, Object> param, PageBounds pageBounds);

}
