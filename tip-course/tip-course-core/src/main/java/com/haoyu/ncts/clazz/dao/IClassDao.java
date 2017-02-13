package com.haoyu.ncts.clazz.dao;

import java.util.List;
import java.util.Map;
import com.haoyu.ncts.clazz.entity.Class;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface IClassDao {

	List<Class> select(Map<String, Object> param, PageBounds pageBounds);

	Class selectByPrimaryKey(String id);

	int deleteByLogic(Map<String, Object> param);

	int insert(Class clazz);

	int update(Class clazz);

}
