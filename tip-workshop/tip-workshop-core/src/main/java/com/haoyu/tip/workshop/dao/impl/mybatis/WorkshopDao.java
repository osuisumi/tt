package com.haoyu.tip.workshop.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.tip.workshop.dao.IWorkshopDao;
import com.haoyu.tip.workshop.entity.Workshop;

@Repository
public class WorkshopDao extends MybatisDao implements IWorkshopDao {
	

	@Override
	public List<Workshop> selectByRole(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("selectByRole", paramMap, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> paramMap) {
		return selectOne("getCount", paramMap);
	}

	@Override
	public Workshop selectOne(Map<String, Object> param) {
		return selectOne("selectOne", param);
	}

	@Override
	public int deleteByIds(List<String> ids) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		Workshop entity = new Workshop();
		entity.setUpdateTime(System.currentTimeMillis());
		entity.setUpdatedby(ThreadContext.getUser());
		param.put("entity", entity);
		return update("deleteByIds", param);
	}

	@Override
	public List<Workshop> selectWorkshop(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

}
