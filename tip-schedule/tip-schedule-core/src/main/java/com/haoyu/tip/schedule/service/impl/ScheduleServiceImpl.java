package com.haoyu.tip.schedule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.schedule.entity.Schedule;
import com.haoyu.tip.schedule.event.CreateScheduleEvent;
import com.haoyu.tip.schedule.event.CreateSchedulesEvent;
import com.haoyu.tip.schedule.dao.IScheduleDao;
import com.haoyu.tip.schedule.service.IScheduleRelationService;
import com.haoyu.tip.schedule.service.IScheduleService;
import com.haoyu.tip.schedule.web.param.ScheduleParam;

@Service
public class ScheduleServiceImpl implements IScheduleService {
	@Resource
	private IScheduleDao scheduleDao;
	@Resource
	private IScheduleRelationService scheduleRelationServiceImpl;
	@Resource
	private ApplicationContext applicationContent;

	@Override
	public Response createSchedule(Schedule schedule) {
		return createSchedule(schedule,true);
	}
	
	@Override
	public Response createSchedule(Schedule schedule, boolean publishEvent) {
		if (schedule == null || schedule.getScheduleRelation() == null) {
			return Response.failInstance().responseMsg("createSchedule fail!schedule or scheduleRelation is null!");
		}
		if (StringUtils.isEmpty(schedule.getId())) {
			schedule.setId(Identities.uuid2());
		}
		int count = scheduleDao.insertSchedule(schedule);
		if(count>0){
			Schedule s = new Schedule(schedule.getId());
			schedule.getScheduleRelation().setSchedule(s);
			Response response = scheduleRelationServiceImpl.createScheduleRelation(schedule.getScheduleRelation());
			if(response.isSuccess()){
				response.setResponseData(schedule);
				if(publishEvent){
					applicationContent.publishEvent(new CreateScheduleEvent(schedule));
				}
			}
			return response;
		}else{
			return Response.failInstance();
		}
	}
	
	@Override
	public Response updateSchedule(Schedule schedule) {
		if (schedule == null || StringUtils.isEmpty(schedule.getId())) {
			return Response.failInstance().responseMsg("updateSchedule is fail!schedule is null or schedule's id is null");
		}
		int count = scheduleDao.updateSchedule(schedule);
		if(schedule.getScheduleRelation()!=null){
			scheduleRelationServiceImpl.updateScheduleRelation(schedule.getScheduleRelation());
		}
		return count > 0 ? Response.successInstance().responseData(schedule) : Response.failInstance().responseMsg("updateSchedule fail!");
	}

	@Override
	public Response deleteSchedule(Schedule schedule) {
		if (schedule == null || StringUtils.isEmpty(schedule.getId())) {
			return Response.failInstance().responseMsg("deleteSchedule is fail!schedule is null or schedule's id is null");
		}
		int count = scheduleDao.deleteScheduleByPhysics(schedule.getId());
		scheduleRelationServiceImpl.deleteScheduleRelation(schedule);
		return count > 0 ? Response.successInstance() : Response.failInstance().responseMsg("deleteSchedule fail!");
	}

	@Override
	public Schedule findScheduleById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return scheduleDao.selectScheduleById(id);
	}

	@Override
	public List<Schedule> findSchedules(ScheduleParam schedule) {
		return findSchedules(schedule, null);
	}

	@Override
	public List<Schedule> findSchedules(ScheduleParam schedule, PageBounds pageBounds) {
		Map<String, Object> parameter = Maps.newHashMap();
		schedule.setParam(parameter);
		return scheduleDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<Schedule> findSchedules(Map<String, Object> parameter) {
		return scheduleDao.findAll(parameter);
	}

	@Override
	public List<Schedule> findSchedules(Map<String, Object> parameter, PageBounds pageBounds) {
		return scheduleDao.findAll(parameter, pageBounds);
	}

	@Override
	public Response updateScheduleState(String userId, String relationId, String state) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		return scheduleDao.updateScheduleByParameter(parameter)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response createSchedules(ScheduleParam scheduleParam) {
		Response response = Response.failInstance();
		List<Schedule> added = Lists.newArrayList();
		for(Schedule schedule:scheduleParam.getSchedules()){
			response = this.createSchedule(schedule,false);
			if(response.isSuccess()){
				added.add(schedule);
			}
		}
		if(response.isSuccess()){
			applicationContent.publishEvent(new CreateSchedulesEvent(added));
		}
		return response;
	}

//	@Override
//	public Response deleteSchedule(String relationId, Date startTime, Date endTime, String userId) {
//		return this.deleteSchedule(new Schedule(Schedule.getId(relationId, startTime, endTime,userId)));
//	}


}
