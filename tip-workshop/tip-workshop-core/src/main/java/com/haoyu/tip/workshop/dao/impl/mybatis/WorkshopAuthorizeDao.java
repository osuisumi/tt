package com.haoyu.tip.workshop.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.workshop.dao.IWorkshopAuthorizeDao;
import com.haoyu.tip.workshop.entity.WorkshopAuthorize;

@Repository
public class WorkshopAuthorizeDao extends MybatisDao implements IWorkshopAuthorizeDao{

	@Override
	public int insertByIds(Map<String, Object> param) {
		return insert("insertByIds", param);
	}

	@Override
	public Map<String, WorkshopAuthorize> selectWorkshopAuthorizeForMap(String workshopId) {
		return selectMap("selectWorkshopAuthorizeForMap",workshopId , "user.id");
	}

	@Override
	public int updateByIds(Map<String, Object> param) {
		return update("updateByIds",param);
	}

	@Override
	public int deleteByPhysics(WorkshopAuthorize workshopAuthorize) {
		return delete("deleteByPhysics",workshopAuthorize);
	}

	@Override
	public int deleteByIds(List<String> ids) {
		return delete("deleteByIds",ids);
	}

}
