package com.haoyu.tip.faq.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.faq.entity.FaqQuestion;

public interface IFaqQuestionService {

	Response create(FaqQuestion obj);
	
	Response update(FaqQuestion obj);
	
	Response delete(String id);
	
	FaqQuestion get(String id);
	
	List<FaqQuestion> listFaqQuestion(FaqQuestion faqQuestion, PageBounds pageBounds);

	Response createFaqQuestion(FaqQuestion faqQuestion);

	Response updateFaqQuestion(FaqQuestion faqQuestion);

	Response deleteFaqQuestion(FaqQuestion faqQuestion);
	
	int count(Map<String,Object> parameter);
	
	List<FaqQuestion> listFaqQuestion(Map<String, Object> param, PageBounds pageBounds);

	FaqQuestion getFaqQuestion(String id);

	FaqQuestion getFaqQuestion(String id,boolean getFaqAnswer);

	List<FaqQuestion> list(Map<String, Object> param, PageBounds pageBounds);
}
