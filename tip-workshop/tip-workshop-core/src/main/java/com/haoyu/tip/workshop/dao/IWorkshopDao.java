package com.haoyu.tip.workshop.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.workshop.entity.Workshop;

public interface IWorkshopDao {

	List<Workshop> selectByRole(Map<String, Object> paramMap, PageBounds pageBounds);

	int getCount(Map<String, Object> paramMap);

	Workshop selectOne(Map<String, Object> param);

	int deleteByIds(List<String> ids);

	List<Workshop> selectWorkshop(Map<String, Object> paramMap, PageBounds pageBounds);

}
