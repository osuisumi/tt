package com.haoyu.tip.faq.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.faq.entity.FaqAnswer;

public interface IFaqAnswerService {

	Response create(FaqAnswer obj);
	
	Response update(FaqAnswer obj);
	
	Response delete(String id);
	
	FaqAnswer get(String id);
	
	List<FaqAnswer> list(SearchParam searchParam, PageBounds pageBounds);
	
	List<FaqAnswer> list(FaqAnswer obj, PageBounds pageBounds);

	int count(FaqAnswer faqAnswer);

}
