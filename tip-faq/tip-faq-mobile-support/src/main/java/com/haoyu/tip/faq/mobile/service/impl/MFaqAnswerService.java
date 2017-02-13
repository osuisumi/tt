package com.haoyu.tip.faq.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.user.mobile.entity.MUser;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.faq.entity.FaqAnswer;
import com.haoyu.tip.faq.mobile.entity.MFaqAnswer;
import com.haoyu.tip.faq.mobile.service.IMFaqAnswerService;
import com.haoyu.tip.faq.service.IFaqAnswerService;

@Service
public class MFaqAnswerService implements IMFaqAnswerService{

	@Resource
	private IFaqAnswerService faqAnswerService;
	
	@Override
	public Response listFaqAnswer(FaqAnswer faqAnswer, PageBounds pageBounds) {
		List<MFaqAnswer> mFaqAnswers = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		param.put("faqQuestionids",Lists.newArrayList(faqAnswer.getQuestionId()));
		SearchParam searchParam = new SearchParam();
		searchParam.setParamMap(param);
		List<FaqAnswer> faqAnswers = faqAnswerService.list(searchParam, pageBounds);
		
		PageList pageList = (PageList)faqAnswers;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(faqAnswers)) {
			for (FaqAnswer a : faqAnswers) {
				MFaqAnswer mFaqAnswer = new MFaqAnswer();
				BeanUtils.copyProperties(a,mFaqAnswer);
				
				if (a.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(a.getCreator(),mUser);
					mFaqAnswer.setCreator(mUser);
				}
				mFaqAnswers.add(mFaqAnswer);
			}
		}
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("answers",mFaqAnswers);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response createFaqAnswer(FaqAnswer faqAnswer) {
		Response response = faqAnswerService.create(faqAnswer);
		if (response.isSuccess()) {
			MFaqAnswer mFaqAnswer = new MFaqAnswer();
			if (response.getResponseData() != null) {	
				faqAnswer = (FaqAnswer) response.getResponseData();
				BeanUtils.copyProperties(faqAnswer,mFaqAnswer);
				if (faqAnswer.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(faqAnswer.getCreator(),mUser);
					mFaqAnswer.setCreator(mUser);
				}
			}
			return Response.successInstance().responseData(mFaqAnswer);
		}
		return Response.failInstance();
	}

	@Override
	public Response updateFaqAnswer(FaqAnswer faqAnswer) {
		Response response = faqAnswerService.update(faqAnswer);
		if (response.isSuccess()) {
			MFaqAnswer mFaqAnswer = new MFaqAnswer();
			if (response.getResponseData() != null) {
				faqAnswer = (FaqAnswer) response.getResponseData();
				BeanUtils.copyProperties(faqAnswer,mFaqAnswer);
				if (faqAnswer.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(faqAnswer.getCreator(),mUser);
					mFaqAnswer.setCreator(mUser);
				}

			}
			return Response.successInstance().responseData(mFaqAnswer);
		}
		return Response.failInstance();
	}

}
