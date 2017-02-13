package com.haoyu.ncts.course.dao;


import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseTopic;

public interface ICourseTopicDao {

	CourseTopic selectCourseTopicById(String id);

	int insertCourseTopic(CourseTopic courseTopic);

	int updateCourseTopic(CourseTopic courseTopic);

	int deleteCourseTopicByLogic(CourseTopic courseTopic);

	int deleteCourseTopicByPhysics(String id);

	List<CourseTopic> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}