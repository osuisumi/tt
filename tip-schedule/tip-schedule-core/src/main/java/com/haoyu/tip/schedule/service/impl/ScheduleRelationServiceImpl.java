package com.haoyu.tip.schedule.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.schedule.dao.IScheduleRelationDao;
import com.haoyu.tip.schedule.entity.Schedule;
import com.haoyu.tip.schedule.entity.ScheduleRelation;
import com.haoyu.tip.schedule.service.IScheduleRelationService;

@Service
public class ScheduleRelationServiceImpl implements IScheduleRelationService {
	@Resource
	private IScheduleRelationDao scheduleRelationDao;

	@Override
	public Response createScheduleRelation(ScheduleRelation scheduleRelation) {
		if (scheduleRelation == null) {
			return Response.failInstance().responseMsg("createScheduleRelation fail!scheduleRelation is null!");
		}
		if (StringUtils.isEmpty(scheduleRelation.getId())) {
			scheduleRelation.setId(Identities.uuid2());
		}
		int count = scheduleRelationDao.insertScheduleRelation(scheduleRelation);
		return count > 0 ? Response.successInstance().responseData(scheduleRelation) : Response.failInstance().responseMsg("createScheduleRelation fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.tip.scheduleRelation.service.IScheduleRelationService#updateScheduleRelation(com.haoyu.tip.scheduleRelation.entity.ScheduleRelation)
	 */
	@Override
	public Response updateScheduleRelation(ScheduleRelation scheduleRelation) {
		if (scheduleRelation == null || StringUtils.isEmpty(scheduleRelation.getId())) {
			return Response.failInstance().responseMsg("updateScheduleRelation is fail!scheduleRelation is null or scheduleRelation's id is null");
		}
		int count = scheduleRelationDao.updateScheduleRelation(scheduleRelation);
		return count > 0 ? Response.successInstance().responseData(scheduleRelation) : Response.failInstance().responseMsg("updateScheduleRelation fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.tip.scheduleRelation.service.IScheduleRelationService#deleteScheduleRelation(com.haoyu.tip.scheduleRelation.entity.ScheduleRelation)
	 */
	@Override
	public Response deleteScheduleRelation(ScheduleRelation scheduleRelation) {
		if (scheduleRelation == null || StringUtils.isEmpty(scheduleRelation.getId())) {
			return Response.failInstance().responseMsg("deleteScheduleRelation is fail!scheduleRelation is null or scheduleRelation's id is null");
		}
		int count = scheduleRelationDao.deleteScheduleRelationByLogic(scheduleRelation);
		return count > 0 ? Response.successInstance() : Response.failInstance().responseMsg("deleteScheduleRelation fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.tip.scheduleRelation.service.IScheduleRelationService#findScheduleRelationById(java.lang.String)
	 */
	@Override
	public ScheduleRelation findScheduleRelationById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return scheduleRelationDao.selectScheduleRelationById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.tip.scheduleRelation.service.IScheduleRelationService#findScheduleRelations(com.haoyu.tip.scheduleRelation.entity.ScheduleRelation)
	 */
	@Override
	public List<ScheduleRelation> findScheduleRelations(ScheduleRelation scheduleRelation) {
		return findScheduleRelations(scheduleRelation, null);
	}

	@Override
	public List<ScheduleRelation> findScheduleRelations(ScheduleRelation scheduleRelation, PageBounds pageBounds) {
		Map<String, Object> parameter = Maps.newHashMap();
		return scheduleRelationDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<ScheduleRelation> findScheduleRelations(Map<String, Object> parameter) {
		return scheduleRelationDao.findAll(parameter);
	}

	@Override
	public List<ScheduleRelation> findScheduleRelations(Map<String, Object> parameter, PageBounds pageBounds) {
		return scheduleRelationDao.findAll(parameter, pageBounds);
	}

	@Override
	public Response deleteScheduleRelation(Schedule schedule) {
		ScheduleRelation scheduleRelation = new ScheduleRelation();
		scheduleRelation.setSchedule(schedule);
		return scheduleRelationDao.deleteScheduleRelationByLogic(scheduleRelation)>0?Response.successInstance():Response.failInstance();
	}
}
