package com.haoyu.tip.resource.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.utils.FileUtils;
import com.haoyu.sip.mobile.file.entity.MFileInfo;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.mobile.entity.MResource;
import com.haoyu.tip.resource.mobile.service.IMResourceService;
import com.haoyu.tip.resource.service.IResourceService;

@Service
public class MResourceServiceImpl implements IMResourceService{

	@Resource
	private IResourceService resourceService;
	
	@Override
	public Response listResource(Resources resource, PageBounds pageBounds) {
		List<MResource> mResources = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		if (Collections3.isNotEmpty(resource.getResourceRelations()) && resource.getResourceRelations().get(0) != null && resource.getResourceRelations().get(0).getRelation() != null) {
			param.put("relationId",resource.getResourceRelations().get(0).getRelation().getId());
			param.put("relationType",resource.getResourceRelations().get(0).getRelation().getType());
		}
		List<Resources> resources = resourceService.list(param, pageBounds, true);
		
		if (Collections3.isNotEmpty(resources)) {
			for (Resources r : resources) {
				MResource mResource = new MResource();
				BeanUtils.copyProperties(r,mResource);
				
				if (Collections3.isNotEmpty(r.getFileInfos())) {
					mResource.setFileInfos(BeanUtils.getCopyList(r.getFileInfos(), MFileInfo.class));
				}
				mResources.add(mResource);
			}
		}
		
		PageList pageList = (PageList)mResources;
		Paginator paginator = pageList.getPaginator();
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("resources",mResources);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response createResource(Resources resource) {
	    if(resource.getFileInfos() != null){
	        List<FileInfo> fileInfos = resource.getFileInfos();
	        String prefix = FileUtils.getHttpHost();
	        for (FileInfo fileInfo : fileInfos) {
                if (StringUtils.isNotEmpty(fileInfo.getUrl()) && fileInfo.getUrl().startsWith(prefix)) {
                    fileInfo.setUrl(fileInfo.getUrl().replace(prefix,""));
                }
            }
	    }
		Response response = resourceService.createResource(resource);
		if (response.isSuccess()) {
			MResource mResource = new MResource();
			if (response.getResponseData() != null) {	
				resource = (Resources) response.getResponseData();
				if(resource.getFileInfos() != null){
		            List<FileInfo> fileInfos = resource.getFileInfos();
		            String prefix = FileUtils.getHttpHost();
		            for (FileInfo fileInfo : fileInfos) {
		                fileInfo.setUrl(prefix + fileInfo.getUrl());
		            }
		        }
				BeanUtils.copyProperties(resource,mResource);
			}
			return Response.successInstance().responseData(mResource);
		}
		return Response.failInstance();
	}

}
