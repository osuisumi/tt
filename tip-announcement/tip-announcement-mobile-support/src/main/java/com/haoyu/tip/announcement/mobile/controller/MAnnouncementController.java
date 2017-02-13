package com.haoyu.tip.announcement.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.tip.announcement.entity.Announcement;
import com.haoyu.tip.announcement.mobile.service.IMAnnouncementService;
import com.haoyu.tip.announcement.service.IAnnouncementService;

@Controller
@RequestMapping("**/m/announcement")
public class MAnnouncementController extends AbstractBaseMobileController{

	@Resource
	private IAnnouncementService announcementService;
	@Resource
	private IMAnnouncementService announcementMobileService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Response list(Announcement announcement){
		return announcementMobileService.listAnnouncement(announcement,getPageBounds(10,true),"true".equals(request.getParameter("getContent"))?true:false);
	}
	
	@RequestMapping(value="view/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Response view(Announcement announcement){
		return announcementMobileService.viewAnnouncement(announcement);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(Announcement announcement){
		return announcementMobileService.createAnnouncement(announcement);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Announcement announcement){
		return announcementMobileService.updateAnnouncement(announcement);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Announcement announcement){
		return announcementService.deleteAnnouncement(announcement);
	}
}