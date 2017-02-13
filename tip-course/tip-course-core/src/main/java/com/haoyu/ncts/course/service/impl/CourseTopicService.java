package com.haoyu.ncts.course.service.impl;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseTopicDao;
import com.haoyu.ncts.course.entity.CourseTopic;
import com.haoyu.ncts.course.service.ICourseTopicService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

@Service
public class CourseTopicService implements ICourseTopicService{
	@Resource
	private ICourseTopicDao courseTopicDao;

	@Override
	public Response createCourseTopic(CourseTopic courseTopic) {
		if(StringUtils.isEmpty(courseTopic.getId())){
			courseTopic.setId(Identities.uuid2());
		}
		courseTopic.setDefaultValue();
		return courseTopicDao.insertCourseTopic(courseTopic)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateCourseTopic(CourseTopic courseTopic) {
		courseTopic.setUpdateValue();
		return courseTopicDao.updateCourseTopic(courseTopic)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteCourseTopic(CourseTopic courseTopic) {
		courseTopic.setUpdateValue();
		return courseTopicDao.deleteCourseTopicByLogic(courseTopic)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public CourseTopic findCourseTopicById(String id) {
		return courseTopicDao.selectCourseTopicById(id);
	}

	@Override
	public List<CourseTopic> findCourseTopics(Map<String, Object> parameter, PageBounds pageBounds) {
		return courseTopicDao.findAll(parameter, pageBounds);
	}

}
