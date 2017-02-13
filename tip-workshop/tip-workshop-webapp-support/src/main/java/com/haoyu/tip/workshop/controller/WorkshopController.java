package com.haoyu.tip.workshop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.workshop.entity.Workshop;
import com.haoyu.tip.workshop.service.IWorkshopService;

@Controller
@RequestMapping("workshop")
public class WorkshopController extends AbstractBaseController{
	
	@Resource
	private IWorkshopService workshopService;
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(Workshop workshop, Model model){
		model.addAttribute("workshop", workshop);
		return "workshop/edit_workshop";
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(Workshop workshop, Model model){
		model.addAttribute("workshop", workshopService.get(workshop.getId()));
		return "workshop/edit_workshop";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response save(Workshop workshop){
		return workshopService.createWorkshop(workshop);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Workshop workshop){
		return workshopService.updateWorkshop(workshop);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(SearchParam searchParam, Model model){
		model.addAttribute("workshops", workshopService.list(searchParam, getPageBounds(10, true)));
		return "workshop/list_workshop";
	}
	
	@RequestMapping(value="{id}/view", method=RequestMethod.GET)
	public String view(Workshop workshop, Model model){
		model.addAttribute("workshop", workshopService.viewWorkshop(workshop));
		return "workshop/view_workshop";
	}
	
	@RequestMapping(value="more", method=RequestMethod.GET)
	public String more(SearchParam searchParam, Model model){
		model.addAttribute("workshops", workshopService.list(searchParam, getPageBounds(10, true)));
		return "workshop/list_more_workshop";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Workshop workshop){
		return workshopService.delete(workshop.getId());
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response batchDelete(Workshop workshop){
		return workshopService.deleteWorkshop(workshop);
	}

}
