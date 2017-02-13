package com.haoyu.tip.livestudio.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.livestudio.dao.ILiveStudioRelationDao;
import com.haoyu.tip.livestudio.entity.LiveStudio;
import com.haoyu.tip.livestudio.entity.LiveStudioRelation;
import com.haoyu.tip.livestudio.service.ILiveStudioRelationService;

@Service
public class LiveStudioRelationService implements ILiveStudioRelationService{
	@Resource
	private ILiveStudioRelationDao liveStudioRelationDao;

	@Override
	public Response create(LiveStudioRelation liveStudioRelation) {
		return BaseServiceUtils.create(liveStudioRelation, (MybatisDao)liveStudioRelationDao);
	}

	@Override
	public Response update(LiveStudioRelation liveStudioRelation) {
		return BaseServiceUtils.update(liveStudioRelation, (MybatisDao)liveStudioRelationDao);
	}

	@Override
	public Response deleteByPhysics(String id) {
		return null;
	}

	@Override
	public Response deleteByLogic(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)liveStudioRelationDao);
	}

	@Override
	public LiveStudio get(String id) {
		return (LiveStudio) BaseServiceUtils.get(id, (MybatisDao)liveStudioRelationDao);
	}

	@Override
	public List<LiveStudioRelation> list(LiveStudioRelation liveStudioRelation, PageBounds pageBounds) {
		return ((MybatisDao)liveStudioRelationDao).selectList("select", liveStudioRelation, pageBounds);
	}

}
