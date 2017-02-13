package com.haoyu.tip.announcement.dao.impl.mybatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.announcement.dao.IAnnouncementDao;

@Repository
public class AnnouncementDao extends MybatisDao implements IAnnouncementDao{

	@Override
	public int deleteByIds(Map<String, Object> param) {
		return update("deleteByIds", param);
	}

}
