package com.haoyu.tip.resource.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.mobile.service.IMResourceService;

@RestController
@RequestMapping("**/m/resource")
public class MResourceController extends AbstractBaseMobileController{

	@Resource
	private IMResourceService resourceMobileService;
	
	@RequestMapping(method=RequestMethod.GET)
	public Response list(Resources resource){
		return resourceMobileService.listResource(resource,getPageBounds(10, true));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Response create(Resources resource){
		return resourceMobileService.createResource(resource);
	}
}
