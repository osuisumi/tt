package com.haoyu.ncts.course.service;


import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseTopic;
import com.haoyu.sip.core.service.Response;

public interface ICourseTopicService {
	
	Response createCourseTopic(CourseTopic courseTopic);
	
	Response updateCourseTopic(CourseTopic courseTopic);
	
	Response deleteCourseTopic(CourseTopic courseTopic);
	
	CourseTopic findCourseTopicById(String id);
	
	List<CourseTopic> findCourseTopics(Map<String,Object> parameter,PageBounds pageBounds);
}
