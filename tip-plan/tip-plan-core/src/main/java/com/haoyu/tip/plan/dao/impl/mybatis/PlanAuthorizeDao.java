package com.haoyu.tip.plan.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.plan.dao.IPlanAuthorizeDao;

@Repository
public class PlanAuthorizeDao extends MybatisDao implements IPlanAuthorizeDao{

	@Override
	public int deleteByIds(List<String> ids) {
		return this.delete("deleteByIds", ids);
	}

}
