package com.haoyu.tip.schedule.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.schedule.entity.Schedule;
import com.haoyu.tip.schedule.entity.ScheduleRelation;

public interface IScheduleRelationService {
	
	Response createScheduleRelation(ScheduleRelation scheduleRelation);

	Response updateScheduleRelation(ScheduleRelation scheduleRelation);

	Response deleteScheduleRelation(ScheduleRelation scheduleRelation);
	
	Response deleteScheduleRelation(Schedule schedule);
	
	ScheduleRelation findScheduleRelationById(String id);

	List<ScheduleRelation> findScheduleRelations(ScheduleRelation scheduleRelation);

	List<ScheduleRelation> findScheduleRelations(ScheduleRelation scheduleRelation, PageBounds pageBounds);

	List<ScheduleRelation> findScheduleRelations(Map<String, Object> parameter);

	List<ScheduleRelation> findScheduleRelations(Map<String, Object> parameter, PageBounds pageBounds);

}
