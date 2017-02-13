package com.haoyu.tip.resource.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.service.IResourceService;

@Controller
@RequestMapping("resource")
public class ResourceController extends AbstractBaseController{

	@Resource
	private IResourceService resourceService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(SearchParam searchParam, Model model){
		model.addAttribute("resources", resourceService.list(searchParam, getPageBounds(10, true)));
		return "resource/list_resource";
	}
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(Resources resource, Model model){
		model.addAttribute("resource", resource);
		return "resource/edit_resource";
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(Resources resource, Model model){
		model.addAttribute("resource", resourceService.get(resource.getId()));
		return "resource/edit_resource";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Response save(Resources resource, Model model){
		if (StringUtils.isEmpty(resource.getId())) {
			return resourceService.createResource(resource);
		}else{
			return resourceService.updateResource(resource);
		}
	}
	
	@RequestMapping(value="more", method=RequestMethod.GET)
	public String more(SearchParam searchParam, Model model){
		model.addAttribute("resources", resourceService.list(searchParam, getPageBounds(10, true)));
		return "resource/list_more_resource";
	}
	
	@RequestMapping(value="{id}/view", method=RequestMethod.GET)
	public String view(Resources resource, Model model){
		model.addAttribute("resource", resourceService.viewResource(resource));
		return "resource/view_resource";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Resources resource, Model model){
		return resourceService.deleteByIds(resource.getId());
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Resources resource){
		return resourceService.delete(resource.getId());
	}
}
