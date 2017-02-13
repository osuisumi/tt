package com.haoyu.tip.faq.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.tip.faq.entity.FaqQuestion;
import com.haoyu.tip.faq.service.IFaqQuestionService;

@Controller
@RequestMapping("**/faq_question")
public class FaqQuestionController extends AbstractBaseController{
	
	@Resource
	private IFaqQuestionService faqQuestionService;
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(FaqQuestion faqQuestion, Model model){
		model.addAttribute("faqQuestion", faqQuestion);
		return "faq/edit_faq_question";
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(FaqQuestion faqQuestion, Model model){
		faqQuestion = faqQuestionService.get(faqQuestion.getId());
		model.addAttribute("faqQuestion", faqQuestionService.get(faqQuestion.getId()));
		return "faq/edit_faq_question";
	}

	@RequestMapping(method=RequestMethod.GET)
	public String listFaqQuestion(FaqQuestion faqQuestion, Model model){
		List<FaqQuestion> faqQuestions = faqQuestionService.listFaqQuestion(faqQuestion, getPageBounds(10,true));
		model.addAttribute("faqQuestions", faqQuestions);
		return "faq/list_faq_question";
	}
	
	@RequestMapping(value="more", method=RequestMethod.GET)
	public String listMoreFaqQuestion(FaqQuestion faqQuestion, Model model){
		List<FaqQuestion> faqQuestions = faqQuestionService.listFaqQuestion(faqQuestion, getPageBounds(10,true));
		model.addAttribute("faqQuestions", faqQuestions);
		return "faq/list_more_faq_question";
	}
	
	@RequestMapping(value="save" ,method=RequestMethod.POST)
	@ResponseBody
	public Response save(FaqQuestion faqQuestion){
		if (StringUtils.isEmpty(faqQuestion.getId())) {
			return faqQuestionService.createFaqQuestion(faqQuestion);
		}else{
			return faqQuestionService.updateFaqQuestion(faqQuestion);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(FaqQuestion faqQuestion){
		return faqQuestionService.createFaqQuestion(faqQuestion);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response update(FaqQuestion faqQuestion){
		return faqQuestionService.updateFaqQuestion(faqQuestion);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public Response delete(FaqQuestion faqQuestion){
		return this.faqQuestionService.deleteFaqQuestion(faqQuestion);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public Response deleteBatch(FaqQuestion faqQuestion){
		return this.faqQuestionService.deleteFaqQuestion(faqQuestion);
	}
	
}
