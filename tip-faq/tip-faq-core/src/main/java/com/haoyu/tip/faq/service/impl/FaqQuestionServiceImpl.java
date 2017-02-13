package com.haoyu.tip.faq.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.faq.dao.IFaqQuestionDao;
import com.haoyu.tip.faq.entity.FaqAnswer;
import com.haoyu.tip.faq.entity.FaqQuestion;
import com.haoyu.tip.faq.service.IFaqAnswerService;
import com.haoyu.tip.faq.service.IFaqQuestionService;

@Service
public class FaqQuestionServiceImpl implements IFaqQuestionService{
	
	@Resource
	private IFaqQuestionDao faqQuestionDao;
	@Resource
	private IFaqAnswerService faqAnswerService;

	@Override
	public Response create(FaqQuestion obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)faqQuestionDao);
	}

	@Override
	public Response update(FaqQuestion obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)faqQuestionDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)faqQuestionDao);
	}

	@Override
	public FaqQuestion get(String id) {
		return (FaqQuestion) BaseServiceUtils.get(id, (MybatisDao)faqQuestionDao);
	}

	@Override
	public List<FaqQuestion> listFaqQuestion(FaqQuestion faqQuestion, PageBounds pageBounds) {
		return faqQuestionDao.selectFaqQuestion(faqQuestion, pageBounds);
	}

	@Override
	public Response createFaqQuestion(FaqQuestion faqQuestion) {
		Response response = this.create(faqQuestion);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(faqQuestion.getFaqAnswers())) {
				for (FaqAnswer faqAnswer : faqQuestion.getFaqAnswers()) {
					faqAnswer.setQuestionId(faqQuestion.getId());
					faqAnswerService.create(faqAnswer);
				}
			}
			response.setResponseData(faqQuestion);
		}
		return response;
	}

	@Override
	public Response updateFaqQuestion(FaqQuestion faqQuestion) {
		Response response = this.update(faqQuestion);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(faqQuestion.getFaqAnswers())) {
				for (FaqAnswer faqAnswer : faqQuestion.getFaqAnswers()) {
					faqAnswerService.update(faqAnswer);
				}
			}
			response.setResponseData(faqQuestion);
		}
		return response;
	}

	@Override
	public Response deleteFaqQuestion(FaqQuestion faqQuestion) {
		String[] array = faqQuestion.getId().split(",");
		List<String> ids = Arrays.asList(array);
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		faqQuestion.setUpdatedby(ThreadContext.getUser());
		faqQuestion.setUpdateTime(System.currentTimeMillis());
		param.put("entity", faqQuestion);
		int count = faqQuestionDao.deleteByIds(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public int count(Map<String, Object> parameter) {
		return faqQuestionDao.count(parameter);
	}

	@Override
	public List<FaqQuestion> listFaqQuestion(Map<String, Object> param, PageBounds pageBounds) {
		return faqQuestionDao.selectFaqQuestion(param, pageBounds);
	}

	@Override
	public FaqQuestion getFaqQuestion(String id) {
		FaqQuestion faqQuestion = this.get(id);
		if (faqQuestion != null && StringUtils.isNotEmpty(faqQuestion.getId())) {
			SearchParam searchParam = new SearchParam();
			Map<String, Object> param = Maps.newHashMap();
			param.put("faqQuestionids",Lists.newArrayList(faqQuestion.getId()));
			searchParam.setParamMap(param);
			faqQuestion.setFaqAnswers(faqAnswerService.list(searchParam, null));
		}
		return faqQuestion;
	}

	@Override
	public FaqQuestion getFaqQuestion(String id, boolean getFaqAnswer) {
		if (getFaqAnswer) {
			return this.getFaqQuestion(id);
		}
		return faqQuestionDao.selectFaqQuestionById(id);
	}

	@Override
	public List<FaqQuestion> list(Map<String, Object> param, PageBounds pageBounds) {
		return faqQuestionDao.select(param, pageBounds);
	}

}
