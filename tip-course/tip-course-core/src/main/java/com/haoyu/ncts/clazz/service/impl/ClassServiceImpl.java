package com.haoyu.ncts.clazz.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.ncts.clazz.dao.IClassDao;
import com.haoyu.ncts.clazz.entity.Class;
import com.haoyu.ncts.clazz.service.IClassService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class ClassServiceImpl implements IClassService{
	
	@Resource
	private IClassDao classDao;

	@Override
	public List<Class> listClass(Map<String, Object> param, PageBounds pageBounds) {
		return classDao.select(param, pageBounds);
	}

	@Override
	public Class getClass(String id) {
		return classDao.selectByPrimaryKey(id);
	}

	@Override
	public Response deleteClassByLogic(Class clazz) {
		String[] array = clazz.getId().split(",");
		List<String> ids = Arrays.asList(array);
		clazz.setUpdatedby(ThreadContext.getUser());
		clazz.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", clazz);
		int count = classDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response createClass(Class clazz) {
		if (StringUtils.isEmpty(clazz.getId())) {
			clazz.setId(Identities.uuid2());
		}
		clazz.setDefaultValue();
		int count = classDao.insert(clazz);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateClass(Class clazz) {
		clazz.setUpdatedby(ThreadContext.getUser());
		clazz.setUpdateTime(System.currentTimeMillis());
		int count = classDao.update(clazz);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
