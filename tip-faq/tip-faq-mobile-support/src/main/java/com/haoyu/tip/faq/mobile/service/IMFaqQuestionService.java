package com.haoyu.tip.faq.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.faq.entity.FaqQuestion;

public interface IMFaqQuestionService {

	Response listFaqQuestion(FaqQuestion faqQuestion,PageBounds pageBounds);

	Response getFaqQuestion(FaqQuestion faqQuestion);

	Response createFaqQuestion(FaqQuestion faqQuestion);

	Response updateFaqQuestion(FaqQuestion faqQuestion);

}
