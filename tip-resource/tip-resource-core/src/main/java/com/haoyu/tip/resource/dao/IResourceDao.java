package com.haoyu.tip.resource.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.resource.entity.Resources;

public interface IResourceDao {

	int updateByIds(Map<String, Object> param);
	
	int deleteByIds(List<String> ids);

	List<Resources> select(Map<String, Object> param, PageBounds pageBounds);

	int getCount(Map<String, Object> param);

}
