package com.haoyu.tip.schedule.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.schedule.dao.IScheduleRelationDao;
import com.haoyu.tip.schedule.entity.ScheduleRelation;

@Repository
public class ScheduleRelationDao extends MybatisDao implements IScheduleRelationDao{

	@Override
	public ScheduleRelation selectScheduleRelationById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.scheduleRelation.dao.IScheduleRelationDao#insertScheduleRelation(com.haoyu.sip.cms.scheduleRelation.entity.ScheduleRelation)
	 */
	@Override
	public int insertScheduleRelation(ScheduleRelation scheduleRelation) {
		scheduleRelation.setDefaultValue();
		return super.insert(scheduleRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.scheduleRelation.dao.IScheduleRelationDao#updateScheduleRelation(com.haoyu.sip.cms.scheduleRelation.entity.ScheduleRelation)
	 */
	@Override
	public int updateScheduleRelation(ScheduleRelation scheduleRelation) {
		scheduleRelation.setUpdateValue();
		return super.update(scheduleRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.scheduleRelation.dao.IScheduleRelationDao#deleteScheduleRelationByLogic(com.haoyu.sip.cms.scheduleRelation.entity.ScheduleRelation)
	 */
	@Override
	public int deleteScheduleRelationByLogic(ScheduleRelation scheduleRelation) {
		scheduleRelation.setUpdateValue();
		return super.deleteByLogic(scheduleRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.scheduleRelation.dao.IScheduleRelationDao#deleteScheduleRelationByPhysics(java.lang.String)
	 */
	@Override
	public int deleteScheduleRelationByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.scheduleRelation.dao.IScheduleRelationDao#findAll(java.util.Map)
	 */
	@Override
	public List<ScheduleRelation> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.scheduleRelation.dao.IScheduleRelationDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<ScheduleRelation> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
