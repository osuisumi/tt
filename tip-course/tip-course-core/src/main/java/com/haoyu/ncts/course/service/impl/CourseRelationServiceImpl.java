package com.haoyu.ncts.course.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.dao.ICourseRelationDao;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.ncts.course.entity.CourseRelation;
import com.haoyu.ncts.course.service.ICourseRelationService;
import com.haoyu.ncts.course.service.ICourseService;
import com.haoyu.sip.core.service.Response;

@Service
public class CourseRelationServiceImpl implements ICourseRelationService{

	@Resource
	private ICourseRelationDao courseRelationDao;
	@Resource
	private ICourseService courseService;
	
	@Override
	public CourseRelation getCourseRelation(String id) {
		return courseRelationDao.selectByPrimaryKey(id);
	}

	@Override
	public Response createCourseRelation(CourseRelation courseRelation) {
		if(courseRelation == null){
			return Response.failInstance().responseMsg("create courseRelation fail! courseRelation is null");
		}
		if(StringUtils.isEmpty(courseRelation.getId())){
			courseRelation.setId(CourseRelation.getId(courseRelation.getCourse().getId(), courseRelation.getRelation().getId()));
		}
		courseRelation.setDefaultValue();
		return courseRelationDao.insertCourseRelation(courseRelation)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateCourseRelation(CourseRelation courseRelation) {
		return null;
	}

	@Override
	public Response deleteCourseRelation(CourseRelation courseRelation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseRelation> listCourseRelations(Map<String, Object> parameter, PageBounds pageBounds) {
		return courseRelationDao.findAll(parameter, pageBounds);
	}
	
	@Override
	public List<CourseRelation> listCourseRelations(CourseRelation courseRelation, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(courseRelation.getCourse()!=null){
			Course course = courseRelation.getCourse();
			if(StringUtils.isNotEmpty(course.getId())){
				parameter.put("courseId", course.getId());
			}
			if(StringUtils.isNotEmpty(course.getSubject())){
				parameter.put("subject",course.getSubject());
			}
			if(StringUtils.isNotEmpty(course.getStage())){
				parameter.put("stage",course.getState());
			}
			if(StringUtils.isNotEmpty(course.getState())){
				parameter.put("state",course.getState());
			}
			if(StringUtils.isNotEmpty(course.getType())){
				parameter.put("type",course.getType());
			}
		}
		if(courseRelation.getRelation()!=null && StringUtils.isNotEmpty(courseRelation.getRelation().getId())){
			parameter.put("relationId",courseRelation.getRelation().getId());
		}
		return this.listCourseRelations(parameter, pageBounds);
	}

}
