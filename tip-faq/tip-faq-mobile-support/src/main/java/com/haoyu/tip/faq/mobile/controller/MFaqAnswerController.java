package com.haoyu.tip.faq.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.tip.faq.entity.FaqAnswer;
import com.haoyu.tip.faq.mobile.service.IMFaqAnswerService;
import com.haoyu.tip.faq.service.IFaqAnswerService;

@Controller
@RequestMapping("**/m/faq_answer")
public class MFaqAnswerController extends AbstractBaseMobileController{

	@Resource
	private IFaqAnswerService faqAnswerService;
	@Resource
	private IMFaqAnswerService faqAnswerMobileService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response list(FaqAnswer faqAnswer) {
		return faqAnswerMobileService.listFaqAnswer(faqAnswer,getPageBounds(10, true));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(FaqAnswer faqAnswer){
		return faqAnswerMobileService.createFaqAnswer(faqAnswer);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(FaqAnswer faqAnswer){
		return faqAnswerMobileService.updateFaqAnswer(faqAnswer);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(FaqAnswer faqAnswer){
		return faqAnswerService.delete(faqAnswer.getId());
	}	

}
