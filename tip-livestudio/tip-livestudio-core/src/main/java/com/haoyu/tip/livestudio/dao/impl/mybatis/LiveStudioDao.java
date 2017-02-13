package com.haoyu.tip.livestudio.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.livestudio.dao.ILiveStudioDao;

@Repository
public class LiveStudioDao extends MybatisDao implements ILiveStudioDao{

	@Override
	public int updateNowNum(String id) {
		return this.getSqlSession().update(namespace+".updateNowNum", id);
	}

	

}
