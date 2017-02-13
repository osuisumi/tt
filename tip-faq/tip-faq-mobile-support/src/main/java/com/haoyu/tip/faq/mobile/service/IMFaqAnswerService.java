package com.haoyu.tip.faq.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.faq.entity.FaqAnswer;

public interface IMFaqAnswerService {

	Response listFaqAnswer(FaqAnswer faqAnswer, PageBounds pageBounds);

	Response createFaqAnswer(FaqAnswer faqAnswer);

	Response updateFaqAnswer(FaqAnswer faqAnswer);

}
