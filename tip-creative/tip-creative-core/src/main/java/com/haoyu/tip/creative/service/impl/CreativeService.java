package com.haoyu.tip.creative.service.impl;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.creative.dao.ICreativeDao;
import com.haoyu.tip.creative.entity.Creative;
import com.haoyu.tip.creative.entity.CreativeRelation;
import com.haoyu.tip.creative.event.BatchDeleteCreativeEvent;
import com.haoyu.tip.creative.event.CreateCreativeEvent;
import com.haoyu.tip.creative.event.DeleteCreativeEvent;
import com.haoyu.tip.creative.event.UpdateCreativeEvent;
import com.haoyu.tip.creative.service.ICreativeRelationService;
import com.haoyu.tip.creative.service.ICreativeService;
import com.haoyu.tip.creative.utils.CreativeClaimType;

@Service
public class CreativeService implements ICreativeService{
	
	@Resource
	private ICreativeDao creativeDao;
	@Resource
	private ICreativeRelationService creativeRelationService;
	@Resource  
	private ApplicationContext applicationContext;  
	@Resource
	private IFileService fileService;
	
	@Override
	public Response createCreative(Creative creative) {
		Response response = Response.failInstance();
		if(StringUtils.isEmpty(creative.getId())){
			creative.setId(Identities.uuid2());
		}
		creative.setDefaultValue();
		int count = creativeDao.insertCreative(creative);
		
		if (count > 0) {
			if (Collections3.isNotEmpty(creative.getCreativeRelations())){
				for (CreativeRelation creativeRelation : creative.getCreativeRelations()) {
					Creative c = new Creative();
					c.setId(creative.getId());
					creativeRelation.setCreative(c);
					if (!CreativeClaimType.CLAIM.equals(creative.getClaimType())) {
						TimePeriod collectTimePeriod = new TimePeriod();
						collectTimePeriod.setStartTime(new Date());
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						calendar.add(Calendar.DATE, creativeRelation.getCollectDays().intValue());
						collectTimePeriod.setEndTime(calendar.getTime());
						creativeRelation.setCollectTimePeriod(collectTimePeriod);
					
					}
					creativeRelationService.createCreativeRelation(creativeRelation);
				}
			}
			response = Response.successInstance();
			applicationContext.publishEvent(new CreateCreativeEvent(creative));
			response.setResponseData(creative);
		}
		return response;
	}

	@Override
	public Response updateCreative(Creative creative) {
		Response response = Response.failInstance();
		creative.setUpdateValue();
		int count = creativeDao.updateCreative(creative);
		if (count > 0) {
			response = Response.successInstance().responseData(creative);
			applicationContext.publishEvent(new UpdateCreativeEvent(creative));
		}
		return response;
	}

	@Override
	public Response deleteCreative(Creative creative) {
		creative.setUpdateValue();
		int count = creativeDao.deleteCreativeByLogic(creative);
		
		if (count > 0) {
			applicationContext.publishEvent(new DeleteCreativeEvent(creative));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Creative findCreativeById(String id) {
		return creativeDao.selectCreativeById(id);
	}

	@Override
	public List<Creative> findCreatives(Creative creative, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		return creativeDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<Creative> findCreatives(Map<String, Object> parameter, PageBounds pageBounds) {
		return creativeDao.findAll(parameter, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		if (param.containsKey("state") && StringUtils.isNotEmpty(param.get("state").toString())) {
			param.put("states", Arrays.asList(param.get("state").toString().split(",")));
		}
		return creativeDao.getCount(param);
	}

	@Override
	public List<Creative> findCreativeByOp(Map<String, Object> param, PageBounds pageBounds) {
		return creativeDao.selectByOp(param,pageBounds);
	}

	@Override
	public Creative findCreativeByResourceId(String resourceId) {
		return creativeDao.selectByResourceId(resourceId);
	}

	@Override
	public Response batchUpdateCreative(Creative creative) {
		Response response = Response.failInstance();
		Map<String, Object> param = Maps.newHashMap();
		
		if (StringUtils.isNotEmpty(creative.getId())) {
			param.put("ids",Arrays.asList(creative.getId().split(",")));
		}
		if (StringUtils.isNotEmpty(creative.getState())) {
			param.put("state",creative.getState());
		}
		int count = creativeDao.batchUpdateCreative(param);
		
		return count > 0 ?response.successInstance() : response.failInstance();
	}

	@Override
	public Response batchDeleteCreative(Creative creative) {
		Response response = Response.failInstance();
		Map<String, Object> param = Maps.newHashMap();
		if (StringUtils.isNotEmpty(creative.getId())) {
			param.put("ids",Arrays.asList(creative.getId().split(",")));
		}
		param.put("updateTime",System.currentTimeMillis());
		param.put("updatedbyId", ThreadContext.getUser().getId());
		int count = creativeDao.batchDeleteCreative(param);
		if(count>0){
			applicationContext.publishEvent(new BatchDeleteCreativeEvent(Arrays.asList(creative.getId().split(","))));
		}
		
		return count > 0 ?response.successInstance() : response.failInstance();
	}

	@Override
	public Map<String, Integer> getAdviceUserNum(Map<String, Object> param) {
		Map<String, Map<String, Integer>> map = creativeDao.getAdviceUserNum(param);
		Map<String, Integer> countMap = Maps.newHashMap();
		Number num = 0;
		for(String key : map.keySet()) {
			num = (Number)map.get(key).get("count");
			countMap.put(key,num.intValue());
		}
		return countMap;
	}

	@Override
	public Response updateCreativeAgreement(Creative creative) {
		Response response = Response.failInstance();
		if (Collections3.isNotEmpty(creative.getResources()) && creative.getResources().get(0) != null && Collections3.isNotEmpty(creative.getResources().get(0).getFileInfos()) && creative.getResources().get(0).getFileInfos().get(0) != null) {
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(new Relation("creative", "creative_apply_agreement"));
			fileService.updateFileList(creative.getResources().get(0).getFileInfos(), oldFileInfos, "creative", "creative_apply_agreement");
			response = Response.successInstance();
		}
		return response;
	}

	@Override
	public int getResourceCount(Map<String, Object> param) {
		return creativeDao.getResourceCount(param);
	}

	@Override
	public int getResourceCreatorCount(Map<String, Object> param) {
		return creativeDao.getResourceCreatorCount(param);
	}

}
