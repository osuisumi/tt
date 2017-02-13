package com.haoyu.tip.creative.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.creative.dao.ICreativeRelationDao;
import com.haoyu.tip.creative.entity.CreativeRelation;
import com.haoyu.tip.creative.service.ICreativeRelationService;

@Service
public class CreativeRelationService implements ICreativeRelationService{
	
	@Resource
	private ICreativeRelationDao creativeRelationDao;

	@Override
	public Response createCreativeRelation(CreativeRelation creativeRelation) {
		if (StringUtils.isEmpty(creativeRelation.getId())){
			creativeRelation.setId(Identities.uuid2());
		}
		if (creativeRelation.getBrowseNum() == null ) {
			creativeRelation.setBrowseNum(0);
		}
		if (creativeRelation.getSupportNum() == null) {
			creativeRelation.setSupportNum(0);
		}
		if (creativeRelation.getParticipateNum() == null) {
			creativeRelation.setParticipateNum(0);
		}
		creativeRelation.setDefaultValue();
		return creativeRelationDao.insertCreativeRelation(creativeRelation)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateCreativeRelation(CreativeRelation creativeRelation) {
		creativeRelation.setUpdateValue();
		return creativeRelationDao.updateCreativeRelation(creativeRelation)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteCreativeRelation(CreativeRelation creativeRelation) {
		creativeRelation.setUpdateValue();
		return creativeRelationDao.deleteCreativeRelationByLogic(creativeRelation)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public CreativeRelation findCreativeRelationById(String id) {
		return creativeRelationDao.selectCreativeRelationById(id);
	}

	@Override
	public List<CreativeRelation> findCreativeRelations(CreativeRelation creativeRelation, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		return creativeRelationDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<CreativeRelation> findCreativeRelations(Map<String, Object> parameter, PageBounds pageBounds) {
		return creativeRelationDao.findAll(parameter, pageBounds);
	}

	@Override
	public Response updateBrowseNum(CreativeRelation cr) {
		cr.setBrowseNum(1);
		return this.updateCreativeRelation(cr);
	}

}
