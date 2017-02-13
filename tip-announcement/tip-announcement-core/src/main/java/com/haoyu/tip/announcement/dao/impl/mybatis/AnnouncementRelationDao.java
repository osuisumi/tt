package com.haoyu.tip.announcement.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.announcement.dao.IAnnouncementRelationDao;

@Repository
public class AnnouncementRelationDao extends MybatisDao implements IAnnouncementRelationDao{

	@Override
	public int deleteByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

}
