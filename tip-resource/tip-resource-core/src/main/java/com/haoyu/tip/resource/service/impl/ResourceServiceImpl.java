package com.haoyu.tip.resource.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.resource.dao.IResourceDao;
import com.haoyu.tip.resource.entity.ResourceExtend;
import com.haoyu.tip.resource.entity.ResourceRelation;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.event.CreateResourceEvent;
import com.haoyu.tip.resource.event.DeleteResourceEvent;
import com.haoyu.tip.resource.event.UpdateResourceEvent;
import com.haoyu.tip.resource.service.IResourceExtendService;
import com.haoyu.tip.resource.service.IResourceRelationService;
import com.haoyu.tip.resource.service.IResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements IResourceService{
	
	@Resource
	private IResourceDao resourceDao;
	@Resource 
	private IResourceRelationService resourceRelationService;
	@Resource
	private IFileService fileService;
	@Resource
	private ITagRelationService tagRelationService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IResourceExtendService resourceExtendService;

	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setResourceRelationService(IResourceRelationService resourceRelationService) {
		this.resourceRelationService = resourceRelationService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	public void setTagRelationService(ITagRelationService tagRelationService) {
		this.tagRelationService = tagRelationService;
	}

	@Override
	public Response create(Resources obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)resourceDao);
	}

	@Override
	public Response update(Resources obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)resourceDao);
	}

	@Override
	public Response delete(String id) {
		Response response = BaseServiceUtils.delete(id, (MybatisDao)resourceDao);
		if(response.isSuccess()){
			applicationContext.publishEvent(new DeleteResourceEvent(Lists.newArrayList(id)));
		}
		return response;
	}

	@Override
	public Resources get(String id) {
		return (Resources) BaseServiceUtils.get(id, (MybatisDao)resourceDao);
	}

	@Override
	public List<Resources> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)resourceDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
	
	@Override
	public Response createResource(Resources resource){
		Response response = this.create(resource);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(resource.getResourceRelations())) {
				for (ResourceRelation resourceRelation : resource.getResourceRelations()) {
					if (resourceRelation.getRelation() != null && StringUtils.isNotEmpty(resourceRelation.getRelation().getId())) {
						if (resourceRelation.getResource() == null || StringUtils.isEmpty(resourceRelation.getRelation().getId())) {
							resourceRelation.setResource(resource);
						}
						String id = ResourceRelation.getId(resourceRelation.getResource().getId(), resourceRelation.getRelation().getId());
						resourceRelation.setId(id);
						resourceRelation.setFileNum(BigDecimal.valueOf(resource.getFileInfos().size()));
						resourceRelationService.create(resourceRelation);
						resourceRelation.setResource(null);
					}
				}
			}
			if (resource.getResourceExtend() != null) {
				ResourceExtend resourceExtend = resource.getResourceExtend();
				resourceExtend.setResourceId(resource.getId());
				resourceExtendService.createResourceExtend(resourceExtend);
			}
			fileService.createFileList(resource.getFileInfos(), resource.getId(), "resources");
			tagRelationService.createTagRelation(resource.getTags(), new Relation(resource.getId(),"resources"), true);
			applicationContext.publishEvent(new CreateResourceEvent(resource));
			response.setResponseData(resource);
		}
		return response;
	}

	@Override
	public Resources viewResource(Resources resource) {
		resource = this.get(resource.getId());
		if (resource != null) {
			resource.setFileInfos(fileService.listFileInfoByRelation(new Relation(resource.getId(),"resources")));
			if (Collections3.isNotEmpty(resource.getResourceRelations())) {
				for(ResourceRelation resourceRelation : resource.getResourceRelations()){
					ResourceRelation rr = new ResourceRelation();
					rr.setId(resourceRelation.getId());
					resourceRelationService.updateBrowseNum(rr);
				}
			}
		}
		return resource;
	}

	@Override
	public Response updateResource(Resources resource) {
		Response response = this.update(resource);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(resource.getResourceRelations())) {
				for (ResourceRelation resourceRelation : resource.getResourceRelations()) {
					resourceRelation.setFileNum(BigDecimal.valueOf(resource.getFileInfos().size()));
					resourceRelationService.update(resourceRelation);
				}
			}
			if(resource.getResourceExtend()!=null){
				ResourceExtend resourceExtend = resource.getResourceExtend();
				resourceExtend.setResourceId(resource.getId());
				resourceExtendService.updateResourceExtend(resourceExtend);
			}
			applicationContext.publishEvent(new UpdateResourceEvent(resource));
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(new Relation(resource.getId(), "resources"));
			fileService.updateFileList(resource.getFileInfos(), oldFileInfos, resource.getId(), "resources");
			tagRelationService.createTagRelation(resource.getTags(), new Relation(resource.getId(),"resources"), true);
		}
		return response;
	}

	@Override
	public Response updateByIds(Resources resources) {
		String[] idArray = resources.getId().split(",");
		List<String> ids = Arrays.asList(idArray);
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		resources.setUpdatedby(ThreadContext.getUser());
		resources.setUpdateTime(System.currentTimeMillis());
		param.put("entity", resources);
		int count = resourceDao.updateByIds(param);
		if(count>0){
			applicationContext.publishEvent(new UpdateResourceEvent(resources));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteByIds(String ids) {
		String [] idArray = ids.split(",");
		List<String> idsList  = Arrays.asList(idArray);
		int count = resourceDao.deleteByIds(idsList);
		if (count>0) {
			applicationContext.publishEvent(new DeleteResourceEvent(idsList));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public List<Resources> list(Map<String, Object> param, PageBounds pageBounds, boolean getFile) {
		List<Resources> resources = resourceDao.select(param, pageBounds);
		if (getFile) {
			for (Resources resource : resources) {
				resource.setFileInfos(fileService.listFileInfoByRelation(new Relation(resource.getId(),"resources")));
			}
		}
		return resources;
	}

	@Override
	public List<Resources> list(Map<String, Object> param, PageBounds pageBounds) {
		return this.list(param, pageBounds, false);
	}
	
}
