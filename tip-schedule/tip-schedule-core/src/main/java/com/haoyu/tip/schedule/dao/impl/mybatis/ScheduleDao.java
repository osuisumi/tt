package com.haoyu.tip.schedule.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.schedule.dao.IScheduleDao;
import com.haoyu.tip.schedule.entity.Schedule;

@Repository
public class ScheduleDao extends MybatisDao implements IScheduleDao {

	@Override
	public Schedule selectScheduleById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.cms.schedule.dao.IScheduleDao#insertSchedule(com.haoyu.sip.cms.schedule.entity.Schedule)
	 */
	@Override
	public int insertSchedule(Schedule schedule) {
		schedule.setDefaultValue();
		return super.insert(schedule);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.cms.schedule.dao.IScheduleDao#updateSchedule(com.haoyu.sip.cms.schedule.entity.Schedule)
	 */
	@Override
	public int updateSchedule(Schedule schedule) {
		schedule.setUpdateValue();
		return super.update(schedule);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.cms.schedule.dao.IScheduleDao#deleteScheduleByLogic(com.haoyu.sip.cms.schedule.entity.Schedule)
	 */
	@Override
	public int deleteScheduleByLogic(Schedule schedule) {
		schedule.setUpdateValue();
		return super.deleteByLogic(schedule);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.cms.schedule.dao.IScheduleDao#deleteScheduleByPhysics(java.lang.String)
	 */
	@Override
	public int deleteScheduleByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.cms.schedule.dao.IScheduleDao#findAll(java.util.Map)
	 */
	@Override
	public List<Schedule> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.cms.schedule.dao.IScheduleDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Schedule> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	@Override
	public int updateScheduleByParameter(Map<String, Object> parameter) {
		return super.update("updateByParameter", parameter);
	}

	@Override
	public int insertSchedules(List<Schedule> schedules) {
		return super.insert("insertSchedules", schedules);

	}

	
	
}
