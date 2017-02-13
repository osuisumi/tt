package com.haoyu.tip.schedule.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.schedule.entity.Schedule;

public interface IScheduleDao {
	
	Schedule selectScheduleById(String id);
	
	int insertSchedule(Schedule schedule);
	
	int insertSchedules(List<Schedule> schedules);
	
	int updateSchedule(Schedule schedule);
	
	int updateScheduleByParameter(Map<String,Object> parameter);
	
	int deleteScheduleByLogic(Schedule schedule);
	
	int deleteScheduleByPhysics(String id);

	List<Schedule> findAll(Map<String, Object> parameter);
	
	List<Schedule> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
