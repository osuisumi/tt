package com.haoyu.tip.resource.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.resource.dao.IResourceExtendDao;
import com.haoyu.tip.resource.entity.ResourceExtend;

@Repository
public class ResourceExtendDao extends MybatisDao implements IResourceExtendDao{

	@Override
	public int insert(ResourceExtend resourceExtend) {
		return super.insert(resourceExtend);
	}

	@Override
	public int update(ResourceExtend resourceExtend) {
		return super.update("update", resourceExtend);
	}

}
