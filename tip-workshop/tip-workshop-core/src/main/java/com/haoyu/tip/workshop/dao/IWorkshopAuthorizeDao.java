package com.haoyu.tip.workshop.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.tip.workshop.entity.WorkshopAuthorize;

public interface IWorkshopAuthorizeDao {

	int insertByIds(Map<String, Object> param);

	Map<String, WorkshopAuthorize> selectWorkshopAuthorizeForMap(String workshopId);

	int updateByIds(Map<String, Object> param);

	int deleteByPhysics(WorkshopAuthorize workshopAuthorize);

	int deleteByIds(List<String> ids);

}
