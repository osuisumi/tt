package com.haoyu.tip.faq.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.tip.faq.entity.FaqQuestion;
import com.haoyu.tip.faq.mobile.service.IMFaqQuestionService;
import com.haoyu.tip.faq.service.IFaqQuestionService;

@Controller
@RequestMapping("**/m/faq_question")
public class MFaqQuestionController extends AbstractBaseMobileController{
	
	@Resource
	private IMFaqQuestionService faqQuestionMobileService;
	@Resource
	private IFaqQuestionService faqQuestionService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response list(FaqQuestion faqQuestion){
		return faqQuestionMobileService.listFaqQuestion(faqQuestion,getPageBounds(10, true));
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ResponseBody
	public Response get(FaqQuestion faqQuestion){
		return faqQuestionMobileService.getFaqQuestion(faqQuestion);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(FaqQuestion faqQuestion){
		return faqQuestionMobileService.createFaqQuestion(faqQuestion);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(FaqQuestion faqQuestion){
		return faqQuestionMobileService.updateFaqQuestion(faqQuestion);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(FaqQuestion faqQuestion){
		return faqQuestionService.deleteFaqQuestion(faqQuestion);
	}
}