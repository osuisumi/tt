package com.haoyu.ncts.clazz.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.ncts.clazz.entity.Class;

public interface IClassService {

	List<Class> listClass(Map<String, Object> param, PageBounds pageBounds);

	Class getClass(String id);

	Response deleteClassByLogic(Class clazz);

	Response createClass(Class clazz);

	Response updateClass(Class clazz);
	
}
