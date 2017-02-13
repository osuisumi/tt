package com.haoyu.tip.faq.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.faq.dao.IFaqAnswerDao;
import com.haoyu.tip.faq.entity.FaqAnswer;
import com.haoyu.tip.faq.event.CreateFaqAnswerEvent;
import com.haoyu.tip.faq.service.IFaqAnswerService;

@Service
public class FaqAnswerServiceImpl implements IFaqAnswerService{

	@Resource
	private IFaqAnswerDao faqAnswerDao;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(FaqAnswer obj) {
		Response response = BaseServiceUtils.create(obj, (MybatisDao)faqAnswerDao);
		if(response.isSuccess()){
			applicationContext.publishEvent(new CreateFaqAnswerEvent(obj));
		}
		response.setResponseData(obj);
		return response;
	}

	@Override
	public Response update(FaqAnswer obj) {
		Response response = BaseServiceUtils.update(obj, (MybatisDao)faqAnswerDao);
		if (response.isSuccess()) {
			response.setResponseData(obj);
		}
		return response;
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)faqAnswerDao);
	}

	@Override
	public FaqAnswer get(String id) {
		return (FaqAnswer) BaseServiceUtils.get(id, (MybatisDao)faqAnswerDao);
	}

	@Override
	public List<FaqAnswer> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)faqAnswerDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public List<FaqAnswer> list(FaqAnswer faqAnswer, PageBounds pageBounds) {
		SearchParam searchParam = new SearchParam();
		Map<String, Object> param = Maps.newHashMap();
		if (StringUtils.isNotEmpty(faqAnswer.getQuestionId())) {
			List<String> faqQuestionids = Arrays.asList(faqAnswer.getQuestionId().split(","));
			param.put("faqQuestionids",faqQuestionids);
		}
		searchParam.setParamMap(param);
		return this.list(searchParam, pageBounds);
	}

	@Override
	public int count(FaqAnswer faqAnswer) {
		return this.faqAnswerDao.count(faqAnswer);
	}
}
