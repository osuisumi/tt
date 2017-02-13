package com.haoyu.tip.resource.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileInfoService;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.tip.resource.dao.IResourceExtendDao;
import com.haoyu.tip.resource.entity.ResourceExtend;
import com.haoyu.tip.resource.service.IResourceExtendService;

@Service
public class ResourceExtendServiceImpl implements IResourceExtendService{

	@Resource
	private IResourceExtendDao resourceExtendDao;
	@Resource
	private IFileService fileService;
	
	@Override
	public Response createResourceExtend(ResourceExtend resourceExtend) {
		if(resourceExtend.getCoverFileInfo()!=null){
			Response r = fileService.createFile(resourceExtend.getCoverFileInfo(), resourceExtend.getResourceId(), "resource_cover");
			if(r.isSuccess()){
				FileInfo fileInfo = resourceExtend.getCoverFileInfo();
				resourceExtend.setCoverUrl(fileInfo.getUrl());
			}
		}
		int count = resourceExtendDao.insert(resourceExtend);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateResourceExtend(ResourceExtend resourceExtend) {
		if(resourceExtend.getCoverFileInfo()!=null){
			List<FileInfo> oldCoverFileInfo = fileService.listFileInfoByRelation(new Relation(resourceExtend.getResourceId(),"resource_cover"));
			fileService.updateFileList(Lists.newArrayList(resourceExtend.getCoverFileInfo()), oldCoverFileInfo, resourceExtend.getResourceId(), "resource_cover");
			FileInfo fi = resourceExtend.getCoverFileInfo();
			resourceExtend.setCoverUrl(fi.getUrl());
		}
		return resourceExtendDao.update(resourceExtend)>0?Response.successInstance():Response.failInstance();
	}

}
