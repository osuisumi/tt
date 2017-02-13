package com.haoyu.tip.resource.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.resource.dao.IResourceRelationDao;

@Repository
public class ResourceRelationDao extends MybatisDao implements IResourceRelationDao{

	@Override
	public int deleteByRelationId(String relationId) {
		return super.delete("deleteByRelationId", relationId);
	}

}
