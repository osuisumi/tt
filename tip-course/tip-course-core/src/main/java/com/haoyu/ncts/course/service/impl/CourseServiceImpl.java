package com.haoyu.ncts.course.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.service.IActivityService;
import com.haoyu.ncts.course.dao.ICourseDao;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.ncts.course.entity.CourseAuthorize;
import com.haoyu.ncts.course.entity.Section;
import com.haoyu.ncts.course.service.ICourseAuthorizeService;
import com.haoyu.ncts.course.service.ICourseService;
import com.haoyu.ncts.course.service.ISectionService;
import com.haoyu.ncts.course.utils.CourseState;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.faq.service.IFaqQuestionService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Resource
	private ICourseDao courseDao;
	@Resource
	private ICourseAuthorizeService courseAuthorizeService;
	@Resource
	private IFaqQuestionService faqQuestionService;
	@Resource
	private ISectionService sectionService;
	@Resource
	private IActivityService activityService;

	@Override
	public List<Course> listCourse(Map<String, Object> params, PageBounds pageBounds) {
		return courseDao.select(params, pageBounds);
	}

	@Override
	public Course getCourse(String id) {
		return courseDao.selectByPrimaryKey(id);
	}

	@Override
	public Response createCourse(Course course) {
		if (StringUtils.isEmpty(course.getId())) {
			course.setId(Course.generateId());
		}
		course.setCode(this.generateCourseCode());
		course.setDefaultValue();
		int count = courseDao.insert(course);
		if (count > 0) {
			if (Collections3.isNotEmpty(course.getCourseAuthorizes())) {
				for (CourseAuthorize courseAuthorize : course.getCourseAuthorizes()) {
					courseAuthorize.setCourse(course);
					courseAuthorizeService.createCourseAuthorize(courseAuthorize);
					courseAuthorize.setCourse(null);
				}
			}
		}
		return count > 0 ? Response.successInstance().responseData(course) : Response.failInstance();
	}

	private String generateCourseCode() {
		String prefix = PropertiesLoader.get("course.code.prefix");
		String maxCode = courseDao.getCourseMaxCode();
		if (StringUtils.isNotEmpty(maxCode)) {
			String no = StringUtils.substringAfterLast(maxCode, prefix);
			int num = Integer.parseInt(no);
			String code = String.valueOf(num + 1);
			return prefix + code;
		} else {
			return prefix + "1";
		}
	}

	@Override
	public Response updateCourse(Course course) {
		if (CourseState.PUBLISHED.equals(course.getState())) {
			// 获取章节内容保存为JSON
			Map<String, Object> param = Maps.newHashMap();
			param.put("courseId", course.getId());
			PageBounds pageBounds = new PageBounds();
			pageBounds.setLimit(Integer.MAX_VALUE);
			pageBounds.setOrders(Order.formString("SORT_NO,CREATE_TIME"));
			// 获取章节
			List<Section> sections = sectionService.listSection(param, true, pageBounds);
			if (Collections3.isNotEmpty(sections)) {
				List<String> sectionIds = Collections3.extractToList(sections, "id");
				param.clear();
				param.put("relationIds", sectionIds);
				// 获取活动
				List<Activity> activities = activityService.listActivity(param, false, pageBounds);
				Map<String, List<Activity>> actMap = Maps.newHashMap();
				if (Collections3.isNotEmpty(activities)) {
					for (Activity activity : activities) {
						if (!actMap.containsKey(activity.getRelation().getId())) {
							actMap.put(activity.getRelation().getId(), Lists.newArrayList());
						}
						actMap.get(activity.getRelation().getId()).add(activity);
					}
				}
				// 将章节封装成map
				List<Map<String, Object>> sectionList = Lists.newArrayList();
				for (Section section : sections) {
					Map<String, Object> sectionMap = Maps.newHashMap();
					sectionMap.put("title", section.getTitle());
					if (Collections3.isNotEmpty(section.getChildSections())) {
						List<Map<String, Object>> childList = Lists.newArrayList();
						for (Section childSection : section.getChildSections()) {
							Map<String, Object> childSectionMap = Maps.newHashMap();
							childSectionMap.put("title", childSection.getTitle());
							childList.add(childSectionMap);

							List<Activity> acts = actMap.get(childSection.getId());
							if (Collections3.isNotEmpty(acts)) {
								List<Map<String, Object>> actList = Lists.newArrayList();
								for (Activity activity : acts) {
									Map<String, Object> map = Maps.newHashMap();
									map.put("title", activity.getTitle());
									map.put("type", activity.getType());
									actList.add(map);
								}
								childSectionMap.put("activities", actList);
							}
						}
						sectionMap.put("childSections", childList);
					}
					sectionList.add(sectionMap);
				}
				course.setContent(new JsonMapper().toJson(sectionList));
			}
		}

		course.setUpdatedby(ThreadContext.getUser());
		course.setUpdateTime(System.currentTimeMillis());
		int count = courseDao.update(course);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response deleteCourseByLogic(Course course) {
		String[] array = course.getId().split(",");
		List<String> ids = Arrays.asList(array);
		course.setUpdatedby(ThreadContext.getUser());
		course.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", course);
		int count = courseDao.deleteByLogic(param);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response deleteCourseByPhysics(String id) {
		String[] array = id.split(",");
		List<String> ids = Arrays.asList(array);
		int count = courseDao.deleteByPhysics(ids);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response retrieveCourse(Course course) {
		String[] array = course.getId().split(",");
		List<String> ids = Arrays.asList(array);
		course.setUpdatedby(ThreadContext.getUser());
		course.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", course);
		int count = courseDao.retrieve(param);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public int getCourseCount(Map<String, Object> params) {
		return courseDao.getCount(params);
	}

	@Override
	public Response updateCourseByIds(Course course) {
		String[] idArray = course.getId().split(",");
		List<String> ids = Arrays.asList(idArray);
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		course.setUpdatedby(ThreadContext.getUser());
		course.setUpdateTime(System.currentTimeMillis());
		param.put("entity", course);
		int count = courseDao.updateByIds(param);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public List<Course> listCourse(Course course, PageBounds pageBounds) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("state", course.getState());
		param.put("isDeleted", course.getIsDeleted());
		param.put("title", course.getTitle());
		param.put("creator", course.getCreator().getId());
		param.put("subject", course.getSubject());
		param.put("stage", course.getStage());
		param.put("type", course.getType());
		return this.listCourse(param, pageBounds);
	}

	@Override
	public List<Activity> listActivity(String courseId) {
		List<Activity> activities = Lists.newArrayList();
		List<Section> sections = sectionService.listSectionByCourseId(courseId);
		List<String> relationIds = Collections3.extractToList(sections, "id");
		if (Collections3.isNotEmpty(relationIds)) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("relationIds", relationIds);
			activities = activityService.listActivity(param, true, null);
		}
		return activities;
	}

	@Override
	public Course getCourse(String id, String getStat) {
		return courseDao.selectByPrimaryKey(id, getStat);
	}
	

	@Override
	public List<Course> listCourseBySource(Map<String, Object> param, PageBounds pageBounds) {
		return courseDao.selectBySource(param, pageBounds);
	}

}
