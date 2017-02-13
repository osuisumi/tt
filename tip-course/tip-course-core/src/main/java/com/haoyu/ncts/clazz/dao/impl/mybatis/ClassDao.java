package com.haoyu.ncts.clazz.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.clazz.dao.IClassDao;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.ncts.clazz.entity.Class;

@Repository
public class ClassDao extends MybatisDao implements IClassDao{

	@Override
	public List<Class> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

	@Override
	public Class selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}

	@Override
	public int insert(Class clazz) {
		return super.insert(clazz);
	}

	@Override
	public int update(Class clazz) {
		return super.update(clazz);
	}

}
