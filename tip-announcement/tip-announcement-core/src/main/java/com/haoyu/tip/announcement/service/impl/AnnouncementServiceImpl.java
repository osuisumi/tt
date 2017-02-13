package com.haoyu.tip.announcement.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.announcement.dao.IAnnouncementDao;
import com.haoyu.tip.announcement.entity.Announcement;
import com.haoyu.tip.announcement.entity.AnnouncementRelation;
import com.haoyu.tip.announcement.entity.AnnouncementUser;
import com.haoyu.tip.announcement.event.CreateAnnouncementEvent;
import com.haoyu.tip.announcement.event.DeleteAnnouncementEvent;
import com.haoyu.tip.announcement.service.IAnnouncementRelationService;
import com.haoyu.tip.announcement.service.IAnnouncementService;
import com.haoyu.tip.announcement.service.IAnnouncementUserService;

@Service("announcementService")
public class AnnouncementServiceImpl implements IAnnouncementService{

	@Resource
	private IAnnouncementDao announcementDao;
	@Resource
	private IAnnouncementRelationService announcementRelationService;
	@Resource
	private IAnnouncementUserService announcementUserService;
	@Resource
	private IFileService fileService;
	@Resource
	private ApplicationContext applicationContext;
	
	
	public void setAnnouncementDao(IAnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}

	public void setAnnouncementRelationService(IAnnouncementRelationService announcementRelationService) {
		this.announcementRelationService = announcementRelationService;
	}

	public void setAnnouncementUserService(IAnnouncementUserService announcementUserService) {
		this.announcementUserService = announcementUserService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	@Override
	public Response create(Announcement obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)announcementDao);
	}

	@Override
	public Response update(Announcement obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)announcementDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)announcementDao);
	}

	@Override
	public Announcement get(String id) {
		return (Announcement) BaseServiceUtils.get(id, (MybatisDao)announcementDao);
	}

	@Override
	@Cacheable(value="listAnnouncements",condition="#searchParam.paramMap != null and #pageBounds != null and #pageBounds.page eq 1 and #searchParam.paramMap['userId'] != null and (#searchParam.paramMap['relationId'] != null or #searchParam.paramMap['relationIds'] != null)",key="T(com.haoyu.tip.announcement.service.impl.AnnouncementServiceImpl).cacheKey(#searchParam)")
	public List<Announcement> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)announcementDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
	
	public static String cacheKey(SearchParam searchParam){
		String userId = searchParam.getParamMap().get("userId") == null?"":String.valueOf(searchParam.getParamMap().get("userId"));
		String relationId = "";
		String type = searchParam.getParamMap().get("type") == null?"":String.valueOf(searchParam.getParamMap().get("type"));;
		if(searchParam.getParamMap().get("relationId")!=null){
			relationId = String.valueOf(searchParam.getParamMap().get("relationId"));
		}else if(searchParam.getParamMap().get("relationIds")!=null){
			if(searchParam.getParamMap().get("relationIds") instanceof List){
				List<Object> relationIds = (List<Object>) searchParam.getParamMap().get("relationIds");
				for(Object rid:relationIds){
					relationId = relationId + "_" + rid;
				}
			}
		}
		return DigestUtils.md5Hex(userId  + "-" + relationId + "-" + type);
	}
	
	@Override
	@CacheEvict(value="listAnnouncements",allEntries=true)
	public Response createAnnouncement(Announcement announcement) {
		Response response = this.create(announcement);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(announcement.getAnnouncementRelations())) {
				for (AnnouncementRelation ar : announcement.getAnnouncementRelations()) {
					if (ar.getRelation() != null && StringUtils.isNotEmpty(ar.getRelation().getId())) {
						if (ar.getAnnouncement() == null || StringUtils.isEmpty(ar.getAnnouncement().getId())) {
							ar.setAnnouncement(announcement);
						}
						String id = AnnouncementRelation.getId(announcement.getId(), ar.getRelation().getId());
						ar.setId(id);
						announcementRelationService.create(ar);
						ar.setAnnouncement(null);
					}
				}
			}
			fileService.createFileList(announcement.getFileInfos(), announcement.getId(), "announcement");
			applicationContext.publishEvent(new CreateAnnouncementEvent(announcement));
			response.setResponseData(announcement);
		}
		return response;
	}

	@Override
	@CacheEvict(value="listAnnouncements",allEntries=true)
	public Announcement viewAnnouncement(Announcement announcement) {
		announcement = this.get(announcement.getId());
		if (announcement != null) {
			announcement.setFileInfos(fileService.listFileInfoByRelationId(announcement.getId()));
			
			AnnouncementRelation announcementRelation = new AnnouncementRelation();
			announcementRelation.setId(announcement.getAnnouncementRelations().get(0).getId());
			announcementRelationService.updateBrowseNum(announcementRelation);
			
			if (ThreadContext.getUser() != null) {
				AnnouncementUser announcementUser = new AnnouncementUser();
				announcementUser.setAnnouncement(announcement);
				announcementUser.setId(AnnouncementUser.getId(announcement.getId(), ThreadContext.getUser().getId()));
				try {
					announcementUserService.create(announcementUser);
				} catch (DuplicateKeyException e) {
					
				}
			}
		}
		return announcement;
	}

	@Override
	@CacheEvict(value="listAnnouncements",allEntries=true)
	public Response updateAnnouncement(Announcement announcement) {
		Response response = this.update(announcement);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(announcement.getAnnouncementRelations())) {
				SearchParam searchParam =  new SearchParam();
				searchParam.getParamMap().put("announcementId", announcement.getId());
				List<AnnouncementRelation> olds = announcementRelationService.list(searchParam, null);
				
				List<Relation> oldRelations = Collections3.extractToList(olds, "relation");
				List<String> oldids = Collections3.extractToList(oldRelations, "id");
				List<Relation> newRelations = Collections3.extractToList(announcement.getAnnouncementRelations(), "relation");
				List<String> newids = Collections3.extractToList(newRelations, "id");
				List<String> addids = Collections3.subtract(newids, oldids);
				List<String> deleteids = Collections3.subtract(oldids, newids);
				
				if (Collections3.isNotEmpty(deleteids)) {
					for (String relationId : deleteids) {
						String id = AnnouncementRelation.getId(announcement.getId(), relationId);
						announcementRelationService.delete(id);
					}
				}
				if (Collections3.isNotEmpty(addids)) {
					for (String relationId : addids) {
						String id = AnnouncementRelation.getId(announcement.getId(), relationId);
						AnnouncementRelation ar = new AnnouncementRelation();
						ar.setId(id);
						ar.setAnnouncement(announcement);
						ar.setRelation(new Relation(relationId));
						announcementRelationService.create(ar);
					}
				}
			}
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(announcement.getId());
			response = fileService.updateFileList(announcement.getFileInfos(), oldFileInfos, announcement.getId(), "announcement");
			response.setResponseData(announcement);
		}
		return response;
	}

	@Override
	@CacheEvict(value="listAnnouncements",allEntries=true)
	public Response deleteAnnouncement(Announcement announcement) {
		String[] idArray = announcement.getId().split(",");
		List<String> ids = Arrays.asList(idArray);
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		announcement.setUpdatedby(ThreadContext.getUser());
		announcement.setUpdateTime(System.currentTimeMillis());
		param.put("entity", announcement);
		int count = announcementDao.deleteByIds(param);
		if (count > 0) {
			applicationContext.publishEvent(new DeleteAnnouncementEvent(ids));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public List<Announcement> list(Announcement announcement, PageBounds pageBounds,boolean getContent) {
		SearchParam searchParam = new SearchParam();
		Map<String,Object> param = Maps.newHashMap();
		
		if (Collections3.isNotEmpty(announcement.getAnnouncementRelations()) && announcement.getAnnouncementRelations().get(0) != null && announcement.getAnnouncementRelations().get(0).getRelation() != null) {
			if (StringUtils.isNotEmpty(announcement.getAnnouncementRelations().get(0).getRelation().getId())) {
				List<String> relationIds = Arrays.asList(announcement.getAnnouncementRelations().get(0).getRelation().getId().split(","));
				if (relationIds.size() > 1) {
					param.put("relationIds", relationIds);
				}else {
					param.put("relationId", announcement.getAnnouncementRelations().get(0).getRelation().getId());
				}
			}
			if (StringUtils.isNotEmpty(announcement.getAnnouncementRelations().get(0).getRelation().getType())) {
				param.put("relationType", announcement.getAnnouncementRelations().get(0).getRelation().getType());
			}
		}
		if (announcement.getAnnouncementUser() != null && StringUtils.isNotEmpty(announcement.getAnnouncementUser().getId())) {
			param.put("userId", announcement.getAnnouncementUser().getId());
		}
		if (announcement.getCreator() != null && StringUtils.isNotEmpty(announcement.getCreator().getId())) {
			param.put("creator", announcement.getCreator().getId());
		}
		param.put("userId",ThreadContext.getUser().getId());
		param.put("type", announcement.getType());
		param.put("title", announcement.getTitle());
		param.put("state", announcement.getState());
		param.put("getContent",getContent);
		searchParam.setParamMap(param);
		return this.list(searchParam, pageBounds);
	}
	

}
