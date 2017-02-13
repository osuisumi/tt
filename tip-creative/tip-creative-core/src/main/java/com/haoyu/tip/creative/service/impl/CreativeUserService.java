package com.haoyu.tip.creative.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.creative.dao.ICreativeUserDao;
import com.haoyu.tip.creative.entity.Creative;
import com.haoyu.tip.creative.entity.CreativeRelation;
import com.haoyu.tip.creative.entity.CreativeUser;
import com.haoyu.tip.creative.service.ICreativeRelationService;
import com.haoyu.tip.creative.service.ICreativeService;
import com.haoyu.tip.creative.service.ICreativeUserService;
import com.haoyu.tip.creative.utils.CreativeClaimState;
import com.haoyu.tip.creative.utils.CreativeClaimType;

@Service
public class CreativeUserService implements ICreativeUserService{
	
	@Resource
	private ICreativeUserDao creativeUserDao;
	@Resource
	private ICreativeService creativeService;
	@Resource
	private ICreativeRelationService creativeRelationService;

	@Override
	public Response createCreativeUser(CreativeUser creativeUser) {
		Response response = Response.failInstance();
		if(StringUtils.isEmpty(creativeUser.getId())){
			creativeUser.setId(Identities.uuid2());
		}
		creativeUser.setDefaultValue();
		int count = creativeUserDao.insertCreativeUser(creativeUser);
		
		if (count > 0) {
			response = Response.successInstance(); 
			if (creativeUser.getCreative() != null ) {
				Creative creative = creativeService.findCreativeById(creativeUser.getCreative().getId());
				if (creative != null) {
					if (CreativeClaimType.CLAIM.equals(creative.getClaimType())) {					
						creative.setClaimState(CreativeClaimState.CLAIMED);
						creativeService.updateCreative(creative);
					}
					
					if (Collections3.isNotEmpty(creative.getCreativeRelations())) {
						for (CreativeRelation creativeRelation : creative.getCreativeRelations()) {		
							if (creativeRelation.getCollectDays() != null ) {
								TimePeriod collectTimePeriod = new TimePeriod();
								collectTimePeriod.setStartTime(new Date());
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(new Date());
								calendar.add(Calendar.DATE, creativeRelation.getCollectDays().intValue());
								collectTimePeriod.setEndTime(calendar.getTime());
								creativeRelation.setCollectTimePeriod(collectTimePeriod);							
								creativeRelationService.updateCreativeRelation(creativeRelation);
							}
						}
					}
				}
			}
		}
		return response;
	}

	@Override
	public Response updateCreativeUser(CreativeUser creativeUser) {
		creativeUser.setUpdateValue();
		return creativeUserDao.updateCreativeUser(creativeUser)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteCreativeUser(CreativeUser creativeUser) {
		creativeUser.setUpdateValue();
		return creativeUserDao.deleteCreativeUserByLogic(creativeUser)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public CreativeUser findCreativeUserById(String id) {
		return creativeUserDao.selectCreativeUserById(id);
	}

	@Override
	public List<CreativeUser> findCreativeUsers(CreativeUser creativeUser, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		return creativeUserDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<CreativeUser> findCreativeUsers(Map<String, Object> parameter, PageBounds pageBounds) {
		return creativeUserDao.findAll(parameter, pageBounds);
	}

	@Override
	public Map<String, CreativeUser> getCreativeUserMap(Map<String, Object> param) {
		return creativeUserDao.findAllForMap(param);
	}

}
