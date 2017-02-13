package com.haoyu.ncts.course.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.dao.ICourseRegisterDao;
import com.haoyu.ncts.course.entity.CourseRegister;
import com.haoyu.ncts.course.event.ChangeUserCourseRegisterEvent;
import com.haoyu.ncts.course.service.ICourseRegisterService;
import com.haoyu.ncts.course.utils.CourseRegisterState;
import com.haoyu.sip.auth.realm.CacheCleaner;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Collections3;

@Service
public class CourseRegisterServiceImpl implements ICourseRegisterService{
	@Resource
	private ICourseRegisterDao courseRegisterDao;
	@Resource
	private CacheCleaner authRealm;
	@Resource
	private ApplicationContext applicationContext;

	@Override
	public List<CourseRegister> listCourseRegister(Map<String, Object> params, PageBounds pageBounds) {
		return courseRegisterDao.select(params, pageBounds);
	}
	
	@Override
	public List<CourseRegister> listCourseRegister(CourseRegister courseRegister,PageBounds pageBounds) {
		Map<String,Object> params = Maps.newHashMap();
		if(courseRegister.getCourse()!=null &&StringUtils.isNotEmpty(courseRegister.getCourse().getId())){
			params.put("courseId", courseRegister.getCourse().getId());
		}
		if(courseRegister.getUser()!=null && StringUtils.isNotEmpty(courseRegister.getUser().getId())){
			params.put("userId",courseRegister.getUser().getId());
		}
		if(courseRegister.getState()!=null && StringUtils.isNotEmpty(courseRegister.getState())){
			params.put("state",courseRegister.getState());
		}
		return this.listCourseRegister(params, pageBounds);
	}

	@Override
	public Response createCourseRegister(CourseRegister courseRegister) {
		if(courseRegister == null){
			return Response.failInstance().responseMsg("create courseRegister fail! courseRegister is null");
		}
		if(StringUtils.isEmpty(courseRegister.getId())){
			courseRegister.setId(CourseRegister.getId(courseRegister.getCourse().getId(), courseRegister.getUser().getId()));
		}
		courseRegister.setDefaultValue();
		Response response;
		try {
			response = courseRegisterDao.insert(courseRegister)>0?Response.successInstance():Response.failInstance();
		} catch (DuplicateKeyException e) {
			response = Response.failInstance().responseMsg("不能重复选课");
		}
		if (response.isSuccess()) {
			if (CourseRegisterState.PASS.equals(courseRegister.getState())) {
				authRealm.clearUserCacheByIds(Lists.newArrayList(courseRegister.getUser().getId()));
				Map<String, Object> map = Maps.newHashMap();
				map.put("userIds", Lists.newArrayList(courseRegister.getUser().getId()));
				applicationContext.publishEvent(new ChangeUserCourseRegisterEvent(map));
			}
		}
		return response;
	}
	
	@Override
	public Response updateCourseRegister(CourseRegister courseRegister) {
		if(courseRegister == null || StringUtils.isEmpty(courseRegister.getId())){
			return Response.failInstance().responseMsg("update courseRegister fail! courseRegister is null or id is null");
		}
		Map<String,Object> param = Maps.newHashMap();
		List<String> ids = Arrays.asList(courseRegister.getId().split(","));
		courseRegister.setUpdateValue();
		param.put("ids", ids);
		param.put("entity",courseRegister);
		return courseRegisterDao.update(param)>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response deleteCourseRegisterByLogic(CourseRegister courseRegister) {
		if(courseRegister == null || StringUtils.isEmpty(courseRegister.getId())){
			return Response.failInstance().responseMsg("delete courseRegister fail! courseRegister is null or courseRegister.id is null");
		}
		Map<String,Object> param = Maps.newHashMap();
		List<String> ids = Arrays.asList(courseRegister.getId().split(","));
		courseRegister.setUpdateValue();
		param.put("ids", ids);
		param.put("entity",courseRegister);
		Response response = courseRegisterDao.deleteByLogic(param)>0?Response.successInstance():Response.failInstance();
		if (response.isSuccess()) {
			if (courseRegister.getUser() != null && StringUtils.isNotEmpty(courseRegister.getUser().getId())) {
				List<String> userIds = Arrays.asList(courseRegister.getUser().getId().split(","));
				authRealm.clearUserCacheByIds(userIds);
				Map<String, Object> map = Maps.newHashMap();
				map.put("userIds", userIds);
				applicationContext.publishEvent(new ChangeUserCourseRegisterEvent(map));
			}
		}
		return response;
	}

	@Override
	public CourseRegister getCourseRegister(String id) {
		return courseRegisterDao.selectByPrimaryKey(id);
	}
	
	@Override
	public Response updateCourseRegister(CourseRegister courseRegister,List<String> userIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseId",courseRegister.getCourse().getId());
		param.put("relationId",courseRegister.getRelation().getId());
		List<CourseRegister> oldCourseRegisters = this.listCourseRegister(param, null);
		List<CourseRegister> prepareAdd = generatePrepareAdd(courseRegister,userIds);
		List<CourseRegister> prepareRemove = Lists.newArrayList();
		for (CourseRegister tr : oldCourseRegisters) {
			if (!prepareAdd.contains(tr)) {
				prepareRemove.add(tr);
			}else{
				prepareAdd.remove(tr);
			}
		}
		Response response = Response.successInstance();
		if(!CollectionUtils.isEmpty(prepareAdd)){
			for(CourseRegister cr:prepareAdd){
				if(response.isSuccess()){
					response = this.createCourseRegister(cr);
				}else{
					throw new DuplicateKeyException("有用户已选该课");
				}
			}
		}
		if(response.isSuccess()){
			if(!CollectionUtils.isEmpty(prepareRemove)){
				Map<String,Object> deleteParam = Maps.newHashMap();
				deleteParam.put("ids",Collections3.extractToList(prepareRemove, "id"));
				response = this.courseRegisterDao.delete(deleteParam)>0?Response.successInstance():Response.failInstance();
			}
		}
		List<User> users = Collections3.extractToList(prepareAdd, "user");
		userIds = Collections3.extractToList(users, "id");
		users = Collections3.extractToList(prepareRemove, "user");
		userIds = Collections3.union(userIds, Collections3.extractToList(users, "id"));
		
		authRealm.clearUserCacheByIds(userIds);
		Map<String, Object> map = Maps.newHashMap();
		map.put("userIds", userIds);
		applicationContext.publishEvent(new ChangeUserCourseRegisterEvent(map));
		return response;
	}
	
	private List<CourseRegister> generatePrepareAdd(CourseRegister courseRegister, List<String> userIds){
		List<CourseRegister> result = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(userIds)){
			for(String userId:userIds){
				if(StringUtils.isNotEmpty(userId)){
					User user = new User(userId);
					courseRegister.setUser(user);
					result.add(courseRegister);
				}
			}
		}
		return result;
	}

	@Override
	public Response deleteCourseRegisterByPhysics(String id) {
		CourseRegister courseRegister = this.getCourseRegister(id);
		int count = this.courseRegisterDao.deleteByPhysics(id);
		if (count>0) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("userIds", Lists.newArrayList(courseRegister.getUser().getId()));
			applicationContext.publishEvent(new ChangeUserCourseRegisterEvent(map));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateCourseRegisterState(CourseRegister courseRegister, List<String> userIds) {
		Response response = this.updateCourseRegister(courseRegister);
		if(response.isSuccess()){
			authRealm.clearUserCacheByIds(userIds);
			Map<String, Object> map = Maps.newHashMap();
			map.put("userIds", userIds);
			applicationContext.publishEvent(new ChangeUserCourseRegisterEvent(map));
		}
		return response;
	}

	@Override
	public Map<String, Integer> mapCourseRegisterNum(Map<String, Object> parameter) {
		return courseRegisterDao.mapCount(parameter);
	}

	@Override
	public Response deleteCourseRegisterByParam(Map<String, Object> parameter) {
		int count = courseRegisterDao.delete(parameter);
		if (count > 0 && parameter.containsKey("userId")) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("userIds", Lists.newArrayList((String)parameter.get("userId")));
			applicationContext.publishEvent(new ChangeUserCourseRegisterEvent(map));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return courseRegisterDao.getCount(param);
	}

}
