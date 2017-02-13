package com.haoyu.tip.schedule.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.schedule.entity.Schedule;
import com.haoyu.tip.schedule.web.param.ScheduleParam;

public interface IScheduleService {
	
	Response createSchedule(Schedule schedule);
	
	Response createSchedule(Schedule schedule,boolean publishEvent);
	
	Response createSchedules(ScheduleParam scheduleParam);
	
	Response updateSchedule(Schedule schedule);
	
	Response updateScheduleState(String userId,String relationId,String state);
	
	Response deleteSchedule(Schedule schedule);
	
//	Response deleteSchedule(String relationId,Date startTime,Date endTime,String userId );
	
	Schedule findScheduleById(String id);
	
	List<Schedule> findSchedules(ScheduleParam schedule);
	
	List<Schedule> findSchedules(ScheduleParam schedule,PageBounds pageBounds);
	
	List<Schedule> findSchedules(Map<String,Object> parameter);
	
	List<Schedule> findSchedules(Map<String,Object> parameter,PageBounds pageBounds);
	
//	List<Schedule> findSchedules(Schedule schedule,String dateParam,String progressParam,PageBounds pageBounds);
	
}
