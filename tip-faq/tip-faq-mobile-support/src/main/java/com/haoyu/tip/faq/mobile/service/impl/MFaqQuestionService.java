package com.haoyu.tip.faq.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;
import com.haoyu.sip.follow.mobile.entity.MFollow;
import com.haoyu.sip.follow.service.IFollowService;
import com.haoyu.sip.user.mobile.entity.MUser;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.faq.entity.FaqAnswer;
import com.haoyu.tip.faq.entity.FaqQuestion;
import com.haoyu.tip.faq.mobile.entity.MFaqQuestion;
import com.haoyu.tip.faq.mobile.service.IMFaqQuestionService;
import com.haoyu.tip.faq.service.IFaqAnswerService;
import com.haoyu.tip.faq.service.IFaqQuestionService;

@Service
public class MFaqQuestionService implements IMFaqQuestionService{

	@Resource
	private IFaqQuestionService faqQuestionService;
	@Resource
	private IFollowService followService;
	@Resource
	private IFaqAnswerService faqAnswerService;
	
	@Override
	public Response listFaqQuestion(FaqQuestion faqQuestion,PageBounds pageBounds) {
		List<MFaqQuestion> mFaqQuestions = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		if (faqQuestion.getRelation() != null ) {
			if (StringUtils.isNotEmpty(faqQuestion.getRelation().getId())) {				
				param.put("relationId",faqQuestion.getRelation().getId());
			}
			if (StringUtils.isNotEmpty(faqQuestion.getRelation().getType())) {
				param.put("relationType",faqQuestion.getRelation().getType());
			}
		}
		param.put("content",faqQuestion.getContent());
		if (faqQuestion.getCreator() != null && StringUtils.isNotEmpty(faqQuestion.getCreator().getId())) {			
			param.put("creatorId",faqQuestion.getCreator().getId());
		}
		if (faqQuestion.getFollow() != null) {
			param.put("follow",faqQuestion.getFollow());
		}
		List<FaqQuestion> faqQuestions = faqQuestionService.list(param, pageBounds);
		
		PageList pageList = (PageList)faqQuestions;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(faqQuestions)) {
			Map<String, Follow> followMap = Maps.newHashMap();
			param = Maps.newHashMap();
			param.put("creatorId",ThreadContext.getUser().getId());
			param.put("relationIds",Collections3.extractToList(faqQuestions, "id"));
			List<Follow> follows = followService.listFollow(param, null);
			
			if (Collections3.isNotEmpty(follows)) {
				followMap = Collections3.extractToMap(follows, "followEntity.id",null);
			}
			for (FaqQuestion q : faqQuestions) {
				MFaqQuestion mFaqQuestion = new MFaqQuestion();
				BeanUtils.copyProperties(q,mFaqQuestion);
				
				if (q.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(q.getCreator(),mUser);
					mFaqQuestion.setCreator(mUser);
				}
				
				FaqAnswer faqAnswer = new FaqAnswer();
				faqAnswer.setQuestionId(q.getId());
				int count = faqAnswerService.count(faqAnswer);
				mFaqQuestion.setFaqAnswerCount(count);
				if (followMap.get(q.getId()) != null ) {
					MFollow mFollow = new MFollow();
					BeanUtils.copyProperties(followMap.get(q.getId()),mFollow);
					mFaqQuestion.setFollow(mFollow);
				}
				mFaqQuestions.add(mFaqQuestion);
			}
		}
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("questions",mFaqQuestions);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response getFaqQuestion(FaqQuestion faqQuestion) {
		if (StringUtils.isNotEmpty(faqQuestion.getId())) {
			MFaqQuestion mFaqQuestion = new MFaqQuestion();
			faqQuestion = faqQuestionService.getFaqQuestion(faqQuestion.getId(),false);
			BeanUtils.copyProperties(faqQuestion,mFaqQuestion);
			
			if (faqQuestion.getCreator() != null) {
				MUser mUser = new MUser();
				BeanUtils.copyProperties(faqQuestion.getCreator(),mUser);
				mFaqQuestion.setCreator(mUser);
			}
			
			Map<String, Object> param = Maps.newHashMap();
			param.put("creatorId",ThreadContext.getUser().getId());
			param.put("relationId",faqQuestion.getId());
			List<Follow> follows = followService.listFollow(param, null);
			if (Collections3.isNotEmpty(follows)) {
				MFollow mFollow = new MFollow();
				BeanUtils.copyProperties(follows.get(0),mFollow);
				
				mFaqQuestion.setFollow(mFollow);
			}
			Relation relaton = new Relation(faqQuestion.getId());
			FollowStat followStat = followService.getFollowStatByFollowEntity(relaton);
			
			if (followStat != null) {
				mFaqQuestion.setFollowNum(followStat.getFollowNum());
			}
			return Response.successInstance().responseData(mFaqQuestion);
		}
		return Response.failInstance();
	}

	@Override
	public Response createFaqQuestion(FaqQuestion faqQuestion) {
		Response response = faqQuestionService.createFaqQuestion(faqQuestion);
		if (response.isSuccess()) {
			MFaqQuestion mFaqQuestion = new MFaqQuestion();
			if (response.getResponseData() != null) {
				faqQuestion = (FaqQuestion) response.getResponseData();
				BeanUtils.copyProperties(faqQuestion,mFaqQuestion);
				
				if (faqQuestion.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(faqQuestion.getCreator(),mUser);
					mFaqQuestion.setCreator(mUser);
				}
				
			}
			return Response.successInstance().responseData(mFaqQuestion);
		}
		return Response.failInstance();
	}

	@Override
	public Response updateFaqQuestion(FaqQuestion faqQuestion) {
		Response response = faqQuestionService.updateFaqQuestion(faqQuestion);
		if (response.isSuccess()) {
			MFaqQuestion mFaqQuestion = new MFaqQuestion();
			if (response.getResponseData() != null) {
				faqQuestion = (FaqQuestion) response.getResponseData();
				BeanUtils.copyProperties(faqQuestion,mFaqQuestion);
				
				if (faqQuestion.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(faqQuestion.getCreator(),mUser);
					mFaqQuestion.setCreator(mUser);
				}
			}
			return Response.successInstance().responseData(mFaqQuestion);
		}
		return Response.failInstance();
	}

}
