package com.haoyu.tip.course.mobile.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.ncts.course.entity.CourseStat;
import com.haoyu.ncts.course.service.ICourseStatService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.tip.course.mobile.entity.MCourse;
import com.haoyu.tip.course.mobile.entity.MCourseStat;
import com.haoyu.tip.course.mobile.service.IMCourseStatService;

@Service
public class MCourseStatService implements IMCourseStatService{
	
	@Resource
	private ICourseStatService courseStatService;
	@Resource
	private RedisTemplate redisTemplate;

	@Override
	public Response getCourseStat(String courseId) {
		Map<String, Object> parameter = Maps.newHashMap();
		parameter.put("courseId", courseId);
		String key = PropertiesLoader.get("redis.app.key") + ":courseStat:"+courseId;
		ValueOperations<String,List<CourseStat>> valueOper = redisTemplate.opsForValue();
		List<CourseStat> courseStats = Lists.newArrayList();
		if(redisTemplate.hasKey(key)){
			courseStats = (List<CourseStat>) valueOper.get(key);
		}else{
			courseStats = courseStatService.findCourseStats(parameter, null);
			valueOper.set(key, courseStats);
			redisTemplate.expire(key, 12, TimeUnit.HOURS);
		}
		MCourseStat result = new MCourseStat();
		if(CollectionUtils.isNotEmpty(courseStats)){
			CourseStat courseStat = courseStats.get(0);
			Course course = courseStat.getCourse();
			MCourse mCourse = new MCourse();
			BeanUtils.copyProperties(course, mCourse);
			mCourse.setStudyHours(course.getStudyHours() == null?new BigDecimal(0):course.getStudyHours());
			mCourse.setRegisterNum(courseStat.getRegisterNum());
			BeanUtils.copyProperties(courseStat, result);
			result.setmCourse(mCourse);
		}
		return Response.successInstance().responseData(result);
	}

}
