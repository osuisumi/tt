package com.haoyu.tip.schedule.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.schedule.entity.ScheduleRelation;

public interface IScheduleRelationDao {
	
	ScheduleRelation selectScheduleRelationById(String id);
	
	int insertScheduleRelation(ScheduleRelation scheduleRelation);
	
	int updateScheduleRelation(ScheduleRelation scheduleRelation);
	
	int deleteScheduleRelationByLogic(ScheduleRelation scheduleRelation);
	
	int deleteScheduleRelationByPhysics(String id);

	List<ScheduleRelation> findAll(Map<String, Object> parameter);
	
	List<ScheduleRelation> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
