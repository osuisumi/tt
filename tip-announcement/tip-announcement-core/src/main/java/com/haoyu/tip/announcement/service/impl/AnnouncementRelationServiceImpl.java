package com.haoyu.tip.announcement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.announcement.dao.IAnnouncementRelationDao;
import com.haoyu.tip.announcement.entity.AnnouncementRelation;
import com.haoyu.tip.announcement.service.IAnnouncementRelationService;

@Service("announcementRelationService")
public class AnnouncementRelationServiceImpl implements IAnnouncementRelationService{
	
	@Resource
	private IAnnouncementRelationDao announcementRelationDao;
	
	public void setAnnouncementRelationDao(IAnnouncementRelationDao announcementRelationDao) {
		this.announcementRelationDao = announcementRelationDao;
	}

	@Override
	public Response create(AnnouncementRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)announcementRelationDao);
	}

	@Override
	public Response update(AnnouncementRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)announcementRelationDao);
	}

	@Override
	public Response delete(String id) {
		int count = announcementRelationDao.deleteByPhysics(id);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public AnnouncementRelation get(String id) {
		return (AnnouncementRelation) BaseServiceUtils.get(id, (MybatisDao)announcementRelationDao);
	}

	@Override
	public List<AnnouncementRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)announcementRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateBrowseNum(AnnouncementRelation announcementRelation) {
		announcementRelation.setBrowseNum(1);
		return this.update(announcementRelation);
	}

}
