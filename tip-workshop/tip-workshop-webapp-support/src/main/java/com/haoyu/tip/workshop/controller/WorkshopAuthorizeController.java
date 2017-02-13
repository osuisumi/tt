package com.haoyu.tip.workshop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.workshop.entity.Workshop;
import com.haoyu.tip.workshop.entity.WorkshopAuthorize;
import com.haoyu.tip.workshop.service.IWorkshopAuthorizeService;

@Controller
@RequestMapping("workshop/authorize")
public class WorkshopAuthorizeController extends AbstractBaseController{
	
	@Resource
	private IWorkshopAuthorizeService workshopAuthorizeService;
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(WorkshopAuthorize workshopAuthorize, Model model){
		model.addAttribute("workshopAuthorize", workshopAuthorize);
		return "workshop/add_workshop_authorize";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(SearchParam searchParam, Model model){
		model.addAttribute("workshopAuthorizes", workshopAuthorizeService.list(searchParam, getPageBounds(10, true)));
		model.addAllAttributes(request.getParameterMap());
		return "workshop/list_workshop_authorize";
	}
	
	@RequestMapping(value="more", method=RequestMethod.GET)
	public String more(SearchParam searchParam, Model model){
		model.addAttribute("workshopAuthorizes", workshopAuthorizeService.list(searchParam, getPageBounds(10, true)));
		model.addAllAttributes(request.getParameterMap());
		return "workshop/list_more_workshop_authorize";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(WorkshopAuthorize workshopAuthorize){
		return workshopAuthorizeService.deleteWorkshopAuthorize(workshopAuthorize);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response batchDelete(WorkshopAuthorize workshopAuthorize){
		return workshopAuthorizeService.deleteWorkshopAuthorizeBatch(workshopAuthorize);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response update(WorkshopAuthorize workshopAuthorize){
		return workshopAuthorizeService.updateWorkshopAuthorize(workshopAuthorize);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response updateBatch(WorkshopAuthorize workshopAuthorize){
		return workshopAuthorizeService.updateWorkshopAuthorize(workshopAuthorize);
	}
	
	@RequestMapping(value="batch",method=RequestMethod.POST)
	@ResponseBody
	public Response createBatch(Workshop workshop){
		return workshopAuthorizeService.createWorkshopAuthorize(workshop);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(WorkshopAuthorize workshopAuthorize){
		workshopAuthorize.setUser(ThreadContext.getUser());
		return workshopAuthorizeService.createWorkshopAuthorize(workshopAuthorize);
	}
	
}
