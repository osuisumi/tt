package com.haoyu.ncts.course.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.dao.ICourseAuthorizeDao;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.ncts.course.entity.CourseAuthorize;
import com.haoyu.ncts.course.service.ICourseAuthorizeService;
import com.haoyu.ncts.utils.RoleCodeConstant;
import com.haoyu.sip.auth.realm.CacheCleaner;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.service.IUserInfoService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class CourseAuthorizeServiceImpl implements ICourseAuthorizeService{
	
	@Resource
	private ICourseAuthorizeDao courseAuthorizeDao;
	@Resource
	private IUserInfoService userService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private CacheCleaner authRealm;
	
	@Override
	public List<CourseAuthorize> listCourseAuthorize(Map<String, Object> param, PageBounds pageBounds) {
		return courseAuthorizeDao.select(param, pageBounds);
	}
	
	@Override
	public Response updateCourseAuthorizeByCourseId(String courseId, String role, List<CourseAuthorize> newList) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("courseId", courseId);
		param.put("role", role);
		List<CourseAuthorize> oldList = this.listCourseAuthorize(param, null);
		List<CourseAuthorize> addList = Collections3.subtract(newList, oldList);
		List<CourseAuthorize> deleteList = Collections3.subtract(oldList, newList);
		List<String> allUids = Lists.newArrayList();
		
		if (Collections3.isNotEmpty(deleteList)) {
			Map<String, Object> map = Maps.newHashMap();
			List<String> ids = Collections3.extractToList(deleteList, "id");
			map.put("ids", ids);
			CourseAuthorize courseAuthorize = new CourseAuthorize();
			courseAuthorize.setUpdatedby(ThreadContext.getUser());
			courseAuthorize.setUpdateTime(System.currentTimeMillis());
			map.put("entity", courseAuthorize);
			courseAuthorizeDao.deleteBatch(map);
			
			List<User> users = Collections3.extractToList(deleteList, "user");
			List<String> uids = Collections3.extractToList(users, "id");
			allUids = Collections3.union(allUids, uids);
		}
		if (Collections3.isNotEmpty(addList)) {
			Map<String, Object> map = Maps.newHashMap();
			CourseAuthorize courseAuthorize = new CourseAuthorize();
			Course course = new Course();
			course.setId(courseId);
			courseAuthorize.setCourse(course);
			courseAuthorize.setRole(role);
			courseAuthorize.setDefaultValue();
			courseAuthorize.setId(PropertiesLoader.get("db.uuid"));
			map.put("entity", courseAuthorize);
			List<User> users = Collections3.extractToList(addList, "user");
			List<String> uids = Collections3.extractToList(users, "id");
			map.put("uids", uids);
			courseAuthorizeDao.insertBatch(map);
			
			allUids = Collections3.union(allUids, uids);
		}
		if (Collections3.isNotEmpty(allUids) &&  authRealm != null) {
			authRealm.clearUserCacheByIds(allUids);
		}
		return Response.successInstance();
	}

	@Override
	public Response createCourseAuthorize(CourseAuthorize courseAuthorize, String userName) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("userName", userName);
		param.put("roleCode", RoleCodeConstant.COURSE_TEACHER);
		List<UserInfo> userInfos = userService.listUser(param, null);
		if (Collections3.isNotEmpty(userInfos)) {
			User user = userInfos.get(0).getUser();
			param.clear();
			param.put("userId", user.getId());
			param.put("role", courseAuthorize.getRole());
			param.put("courseId", courseAuthorize.getCourse().getId());
			List<CourseAuthorize> courseAuthorizes = this.listCourseAuthorize(param, null);
			if (Collections3.isEmpty(courseAuthorizes)) {
				if (StringUtils.isEmpty(courseAuthorize.getId())) {
					courseAuthorize.setId(Identities.uuid2());
				}
				courseAuthorize.setUser(user); 
				courseAuthorize.setDefaultValue();
				int count = courseAuthorizeDao.insert(courseAuthorize);
				if (count > 0) {
					if (authRealm != null) {
						authRealm.clearUserCacheById(user.getId());
					}
				}
				return count>0?Response.successInstance().responseData(courseAuthorize):Response.failInstance();
			}else{
				return Response.failInstance().responseMsg("user exists");
			}
		}else{
			return Response.failInstance().responseMsg("user not exists");
		}
	}

	@Override
	public Response deleteCourseAuthorizeByLogic(CourseAuthorize courseAuthorize) {
		String[] array = courseAuthorize.getId().split(",");
		List<String> ids = Arrays.asList(array);
		courseAuthorize.setUpdatedby(ThreadContext.getUser());
		courseAuthorize.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", courseAuthorize);
		int count = courseAuthorizeDao.deleteByLogic(param);
		if (count > 0) {
			if (authRealm != null) {
				String[] uidArray = courseAuthorize.getId().split(",");
				List<String> uids = Arrays.asList(uidArray);
				authRealm.clearUserCacheByIds(uids);
			}
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateCourseAuthorizeByUserId(String userId, String role, List<CourseAuthorize> newList) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("userId", userId);
		param.put("role", role);
		List<CourseAuthorize> oldList = this.listCourseAuthorize(param, null);
		List<CourseAuthorize> addList = Collections3.subtract(newList, oldList);
		List<CourseAuthorize> deleteList = Collections3.subtract(oldList, newList);
		
		if (Collections3.isNotEmpty(deleteList)) {
			Map<String, Object> map = Maps.newHashMap();
			List<String> ids = Collections3.extractToList(deleteList, "id");
			map.put("ids", ids);
			CourseAuthorize courseAuthorize = new CourseAuthorize();
			courseAuthorize.setUpdatedby(ThreadContext.getUser());
			courseAuthorize.setUpdateTime(System.currentTimeMillis());
			map.put("entity", courseAuthorize);
			courseAuthorizeDao.deleteBatch(map);
		}
		if (Collections3.isNotEmpty(addList)) {
			Map<String, Object> map = Maps.newHashMap();
			CourseAuthorize courseAuthorize = new CourseAuthorize();
			courseAuthorize.setUser(new com.haoyu.sip.core.entity.User(userId));
			courseAuthorize.setRole(role);
			courseAuthorize.setDefaultValue();
			courseAuthorize.setId(PropertiesLoader.get("db.uuid"));
			courseAuthorize.setDefaultValue();
			map.put("entity", courseAuthorize);
			List<Course> courses = Collections3.extractToList(addList, "course");
			List<String> cids = Collections3.extractToList(courses, "id");
			map.put("cids", cids);
			courseAuthorizeDao.insertBatch(map);
		}
		if (authRealm != null) {
			authRealm.clearUserCacheByIds(Lists.newArrayList(userId));
		}
		return Response.successInstance();
	}

	@Override
	public Response createCourseAuthorize(CourseAuthorize courseAuthorize) {
		if (StringUtils.isEmpty(courseAuthorize.getId())) {
			courseAuthorize.setId(Identities.uuid2());
		}
		courseAuthorize.setDefaultValue();
		int count = courseAuthorizeDao.insert(courseAuthorize);
		if (count > 0) {
			if (authRealm != null) {
				authRealm.clearUserCacheById(courseAuthorize.getUser().getId());
			}
		}
		return count>0?Response.successInstance().responseData(courseAuthorize):Response.failInstance();
	}
}
