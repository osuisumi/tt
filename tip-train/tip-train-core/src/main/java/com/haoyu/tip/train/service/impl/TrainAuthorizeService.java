package com.haoyu.tip.train.service.impl;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.train.dao.ITrainAuthorizeDao;
import com.haoyu.tip.train.entity.TrainAuthorize;
import com.haoyu.tip.train.service.ITrainAuthorizeService;

@Service
public class TrainAuthorizeService implements ITrainAuthorizeService{
	@Resource
	private ITrainAuthorizeDao TrainAuthorizeDao;

	@Override
	public Response createTrainAuthorize(TrainAuthorize TrainAuthorize) {
		if(StringUtils.isEmpty(TrainAuthorize.getId())){
			TrainAuthorize.setId(Identities.uuid2());
		}
		TrainAuthorize.setDefaultValue();
		return TrainAuthorizeDao.insertTrainAuthorize(TrainAuthorize)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateTrainAuthorize(TrainAuthorize TrainAuthorize) {
		TrainAuthorize.setUpdateValue();
		return TrainAuthorizeDao.updateTrainAuthorize(TrainAuthorize)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteTrainAuthorize(TrainAuthorize TrainAuthorize) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("ids", Arrays.asList(TrainAuthorize.getId().split(",")));
		TrainAuthorize.setUpdateValue();
		parameter.put("entity", TrainAuthorize);
		return TrainAuthorizeDao.deleteTrainAuthorizeByLogic(parameter)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public TrainAuthorize findTrainAuthorizeById(String id) {
		return TrainAuthorizeDao.selectTrainAuthorizeById(id);
	}

	@Override
	public List<TrainAuthorize> findTrainAuthorizes(TrainAuthorize TrainAuthorize, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		return TrainAuthorizeDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<TrainAuthorize> findTrainAuthorizes(Map<String, Object> parameter, PageBounds pageBounds) {
		return TrainAuthorizeDao.findAll(parameter, pageBounds);
	}

}
