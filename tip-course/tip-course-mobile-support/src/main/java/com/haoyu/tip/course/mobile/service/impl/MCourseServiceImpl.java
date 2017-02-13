package com.haoyu.tip.course.mobile.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.aip.activity.service.IActivityResultService;
import com.haoyu.aip.activity.service.IActivityService;
import com.haoyu.aip.activity.utils.ActivityResultState;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.ncts.course.entity.CourseRegisterStat;
import com.haoyu.ncts.course.entity.CourseStat;
import com.haoyu.ncts.course.entity.Section;
import com.haoyu.ncts.course.service.ICourseRegisterStatService;
import com.haoyu.ncts.course.service.ICourseService;
import com.haoyu.ncts.course.service.ICourseStatService;
import com.haoyu.ncts.course.service.ISectionService;
import com.haoyu.ncts.course.utils.CourseResultState;
import com.haoyu.ncts.course.utils.CourseType;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.course.mobile.entity.MCourse;
import com.haoyu.tip.course.mobile.entity.MCourseStudyProgress;
import com.haoyu.tip.course.mobile.entity.MSection;
import com.haoyu.tip.course.mobile.service.IMCourseService;

@Service
public class MCourseServiceImpl implements IMCourseService{
	
	@Resource
	private ICourseService courseService;
	@Resource
	private ISectionService sectionService;
	@Resource
	private IActivityService activityService;
	@Resource
	private IActivityResultService activityResultService;
	@Resource
	private ICourseStatService courseStatService;
	@Resource
	private ICourseRegisterStatService courseRegisterStatService;
	@Resource
	private RedisTemplate redisTemplate;

	@Override
	public Response studyCourse(Course course) {
		MCourse mCourse = new MCourse();
		List<MSection> mSections = Lists.newArrayList();
		course = courseService.getCourse(course.getId());
		if (CourseType.LEAD.equals(course.getType()) || CourseType.SELF.equals(course.getType())) {
			PageBounds pageBounds = new PageBounds();
			pageBounds.setLimit(Integer.MAX_VALUE);
			pageBounds.setOrders(Order.formString("SORT_NO,CREATE_TIME"));
			Map<String, Object> param = Maps.newHashMap();
			param.put("courseId", course.getId());
			List<Section> sections = sectionService.listSection(param, true, pageBounds);
			
			//查询每个节的活动数, 以及当前用户的活动完成情况
			if (Collections3.isNotEmpty(sections)) {
				param = Maps.newHashMap();
				List<Section> childSections = Lists.newArrayList();
				for (Section section : sections) {
					childSections.addAll(section.getChildSections());
				}
				if (Collections3.isNotEmpty(childSections)) {
					List<String> relationIds = Collections3.extractToList(childSections, "id");
					param.put("relationIds", relationIds);
					param.put("courseId", course.getId());
					List<Activity> activities = activityService.listActivity(param, false, null);
					Map<String, Integer> activityNumMap = Maps.newHashMap();
					for (Activity activity : activities) {
						String sectionId = activity.getRelation().getId();
						if (!activityNumMap.containsKey(sectionId)) {
							activityNumMap.put(sectionId, 0);
						}
						int num = activityNumMap.get(sectionId);
						activityNumMap.put(sectionId, num+1);
					}
					param = Maps.newHashMap();
					param.put("relationId", course.getId());
					param.put("creator", ThreadContext.getUser().getId());
					List<ActivityResult> activityResults = activityResultService.listActivityResult(param, null);
					Map<String, List<ActivityResult>> activityResultMap = Maps.newHashMap();
					for (ActivityResult activityResult : activityResults) {
						if (activityResult.getActivity().getRelation() != null) {
							String sectionId = activityResult.getActivity().getRelation().getId();
							if (!activityResultMap.containsKey(sectionId)) {
								activityResultMap.put(sectionId, Lists.newArrayList());
							}
							activityResultMap.get(sectionId).add(activityResult);
						}
					}
					for (Section section : sections) {
						MSection mSection = new MSection();
						try {
							BeanUtils.copyProperties(mSection, section);
						} catch (IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
						}
						for (Section sec : section.getChildSections()) {
							MSection childMSection = new MSection();
							try {
								BeanUtils.copyProperties(childMSection, sec);
							} catch (IllegalAccessException | InvocationTargetException e) {
								e.printStackTrace();
							}
							if (activityNumMap.containsKey(sec.getId())) {
								List<ActivityResult> arList = activityResultMap.get(sec.getId());
								int completeActNum = 0;
								if (Collections3.isNotEmpty(arList)) {
									for (ActivityResult activityResult : arList) {
										if (ActivityResultState.COMPLETE.equals(activityResult.getState())) {
											completeActNum++;
										}
									}
								}
								int actNum = activityNumMap.get(sec.getId());
								if (completeActNum == actNum) {
									childMSection.setCompleteState(PropertiesLoader.get("property.section.completeState.complete"));
								}else if(completeActNum == 0){
									childMSection.setCompleteState(PropertiesLoader.get("property.section.completeState.notAttempt"));
								}else{
									childMSection.setCompleteState(PropertiesLoader.get("property.section.completeState.inProgess"));
								}
							}
							mSection.getChildMSections().add(childMSection);
						}
						mSections.add(mSection);
					}
				}
			}else{
				
			}
		}
		try {
			BeanUtils.copyProperties(mCourse, course);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		mCourse.setmSections(mSections);
		return Response.successInstance().responseData(mCourse);
	}

	@Override
	public Response getStudyProgress(Course course) {
		String key = PropertiesLoader.get("redis.app.key") + ":courseStat:"+course.getId();
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("courseId", course.getId());
		ValueOperations<String,List<CourseStat>> valueOper = redisTemplate.opsForValue();
		CourseStat courseStat = new CourseStat();
		if(redisTemplate.hasKey(key)){
			List<CourseStat> courseStats = (List<CourseStat>) valueOper.get(key);
			if(CollectionUtils.isNotEmpty(courseStats)){
				courseStat = courseStats.get(0);
			}
		}else{
			List<CourseStat> courseStats = courseStatService.findCourseStats(parameter, null);
			valueOper.set(key, courseStats);
			if(CollectionUtils.isNotEmpty(courseStats)){
				courseStat = courseStats.get(0);
			}
			redisTemplate.expire(key, 1, TimeUnit.DAYS);
		}
		
		parameter.put("userId", ThreadContext.getUser().getId());
		CourseRegisterStat courseRegisterStat = new CourseRegisterStat();
		List<CourseRegisterStat> courseRegisterStats = courseRegisterStatService.findCourseRegisterStats(parameter, null);
		if(CollectionUtils.isNotEmpty(courseRegisterStats)){
			courseRegisterStat = courseRegisterStats.get(0);
		}
		
		MCourseStudyProgress result = new MCourseStudyProgress();
		
		if(courseStat.getCourse()!=null && courseStat.getCourse().getTimePeriod()!=null){
			result.setTimePeriod(courseStat.getCourse().getTimePeriod());
		}
		
		
		com.haoyu.sip.core.utils.BeanUtils.copyProperties(courseStat, result);
		com.haoyu.sip.core.utils.BeanUtils.copyProperties(courseRegisterStat, result);
		
		if(courseRegisterStat!=null && courseRegisterStat.getCourseResult()!=null){
			result.setScore(courseRegisterStat.getCourseResult().getScore()==null?new BigDecimal(0):courseRegisterStat.getCourseResult().getScore());
			result.setState(StringUtils.isEmpty(courseRegisterStat.getCourseResult().getState())?"null":courseRegisterStat.getCourseResult().getState());
		}
		
		return Response.successInstance().responseData(result);
	}
	
}
