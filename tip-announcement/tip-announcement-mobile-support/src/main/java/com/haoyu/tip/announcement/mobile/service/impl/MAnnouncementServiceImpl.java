package com.haoyu.tip.announcement.mobile.service.impl;

import java.util.Arrays;
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
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.announcement.entity.Announcement;
import com.haoyu.tip.announcement.mobile.entity.MAnnouncement;
import com.haoyu.tip.announcement.mobile.service.IMAnnouncementService;
import com.haoyu.tip.announcement.service.IAnnouncementService;

@Service
public class MAnnouncementServiceImpl implements IMAnnouncementService{

	@Resource
	private IAnnouncementService announcementService;
	
	@Override
	public Response listAnnouncement(Announcement announcement, PageBounds pageBounds,boolean getContent) {
		List<MAnnouncement> mAnnouncements = Lists.newArrayList();
		SearchParam searchParam = new SearchParam();
		Map<String, Object> param = Maps.newHashMap();
		if (Collections3.isNotEmpty(announcement.getAnnouncementRelations()) && announcement.getAnnouncementRelations().get(0) != null && announcement.getAnnouncementRelations().get(0).getRelation() != null) {			
			param.put("relationIds",Arrays.asList(announcement.getAnnouncementRelations().get(0).getRelation().getId().split(",")));
			param.put("relationType", announcement.getAnnouncementRelations().get(0).getRelation().getType());
		}
		param.put("getContent",getContent);
		if (ThreadContext.getUser() != null && StringUtils.isNotEmpty(ThreadContext.getUser().getId())) {
			param.put("userId",ThreadContext.getUser().getId());
		}
		if(StringUtils.isNotEmpty(announcement.getType())){
			param.put("type",announcement.getType());
		}
		searchParam.setParamMap(param);
		List<Announcement> announcements = announcementService.list(searchParam, pageBounds);
		
		PageList pageList = (PageList)announcements;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(announcements)) {
			for (Announcement a : announcements) {
				MAnnouncement mAnnouncement = new MAnnouncement();
				BeanUtils.copyProperties(a, mAnnouncement);
				
				if (a.getAnnouncementUser() != null && StringUtils.isNotEmpty(a.getAnnouncementUser().getId())) {
					mAnnouncement.setHadView(true);
				}else {
					mAnnouncement.setHadView(false);
				}
				mAnnouncements.add(mAnnouncement);
			}
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("announcements",mAnnouncements);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response viewAnnouncement(Announcement announcement) {
		announcement = announcementService.viewAnnouncement(announcement);
		MAnnouncement mAnnouncement = new MAnnouncement();
		BeanUtils.copyProperties(announcement, mAnnouncement);
		return Response.successInstance().responseData(mAnnouncement);
	}

	@Override
	public Response createAnnouncement(Announcement announcement) {
		Response response = announcementService.createAnnouncement(announcement);
		if (response.isSuccess()) {
			MAnnouncement mAnnouncement = new MAnnouncement();
			if (response.getResponseData() != null) {
				BeanUtils.copyProperties(response.getResponseData(),mAnnouncement);
			}
			return Response.successInstance().responseData(mAnnouncement);
		}
		return Response.failInstance();
	}

	@Override
	public Response updateAnnouncement(Announcement announcement) {
		Response response = announcementService.updateAnnouncement(announcement);
		if (response.isSuccess()) {
			MAnnouncement mAnnouncement = new MAnnouncement();
			if (response.getResponseData() != null) {
				BeanUtils.copyProperties(response.getResponseData(),mAnnouncement);
			}
			return Response.successInstance().responseData(mAnnouncement);
		}
		return Response.failInstance();
	}

}
