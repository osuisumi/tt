package com.haoyu.tip.course.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.ncts.course.entity.Section;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.tip.course.mobile.service.IMSectionService;

@RestController
@RequestMapping("**/m/section")
public class MSectionController extends AbstractBaseController{

	@Resource
	private IMSectionService mSectionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Response list(Section section){
		return mSectionService.listSection(section);
	}
}
