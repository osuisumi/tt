package com.haoyu.tip.resource.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.resource.dao.IResourceRelationDao;
import com.haoyu.tip.resource.entity.ResourceRelation;
import com.haoyu.tip.resource.service.IResourceRelationService;

@Service("resourceRelationService")
public class ResourceRelationServiceImpl implements IResourceRelationService{

	@Resource
	private IResourceRelationDao resourceRelationDao;
	
	
	
	public void setResourceRelationDao(IResourceRelationDao resourceRelationDao) {
		this.resourceRelationDao = resourceRelationDao;
	}

	@Override
	public Response create(ResourceRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)resourceRelationDao);
	}

	@Override
	public Response update(ResourceRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)resourceRelationDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)resourceRelationDao);
	}

	@Override
	public ResourceRelation get(String id) {
		return (ResourceRelation) BaseServiceUtils.get(id, (MybatisDao)resourceRelationDao);
	}

	@Override
	public List<ResourceRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)resourceRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
	
	@Override
	public Response updateBrowseNum(ResourceRelation obj) {
		obj.setBrowseNum(1);
		return this.update(obj);
	}
}
