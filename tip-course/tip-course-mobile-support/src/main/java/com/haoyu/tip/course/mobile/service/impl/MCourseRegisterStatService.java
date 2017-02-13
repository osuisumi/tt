package com.haoyu.tip.course.mobile.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.user.mobile.entity.MUser;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.ncts.course.entity.CourseRegisterStat;
import com.haoyu.ncts.course.service.ICourseRegisterStatService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.course.mobile.entity.MCourseRegister;
import com.haoyu.tip.course.mobile.entity.MCourseRegisterStat;
import com.haoyu.tip.course.mobile.entity.MCourseResult;
import com.haoyu.tip.course.mobile.service.IMCourseRegisterStatService;

@Service
public class MCourseRegisterStatService implements IMCourseRegisterStatService{
	
	@Resource
	private ICourseRegisterStatService courseRegisterStatService;
	
	@Override
	public Response listCourseRegisterStat(String courseId, String courseResultState, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("courseId", courseId);
		if(StringUtils.isNotEmpty(courseResultState)){
			parameter.put("courseResultState", courseResultState);
		}
		List<CourseRegisterStat> courseRegisterStats = courseRegisterStatService.findCourseRegisterStats(parameter, pageBounds);
		
		List<MCourseRegisterStat> mCourseRegisterStats = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(courseRegisterStats)){
			for(CourseRegisterStat crs:courseRegisterStats){
				MCourseRegisterStat mCourseRegisterStat = new MCourseRegisterStat();
				MCourseRegister mCourseRegister = new MCourseRegister();
				MCourseResult mCourseResult = new MCourseResult();
				
				if(crs.getCourseRegister()!=null){
					crs.getCourseRegister().setCourse(new Course(courseId));
					BeanUtils.copyProperties(crs.getCourseRegister(), mCourseRegister);
					mCourseRegister.setmUser((MUser)BeanUtils.getCopyEntity(crs.getCourseRegister().getUser(), MUser.class));
				}
				if(crs.getCourseResult()!=null){
					BeanUtils.copyProperties(crs.getCourseResult(), mCourseResult);
					mCourseResult.setScore(crs.getCourseResult().getScore() == null?new BigDecimal(0):crs.getCourseResult().getScore());
				}
				
				BeanUtils.copyProperties(crs, mCourseRegisterStat);
				mCourseRegisterStat.setmCourseRegister(mCourseRegister);
				mCourseRegisterStat.setmCourseResult(mCourseResult);
				
				mCourseRegisterStats.add(mCourseRegisterStat);
			}
		}
		
		Map<String,Object> result = Maps.newHashMap();
		result.put("mCourseRegisterStats", mCourseRegisterStats);
		
		if(courseRegisterStats instanceof PageList){
			PageList pageList = (PageList) courseRegisterStats;
			result.put("paginator", pageList.getPaginator());
		}else{
			result.put("paginator", null);
		}
		
		return Response.successInstance().responseData(result);
	}

}
