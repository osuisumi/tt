package com.haoyu.tip.faq.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.faq.entity.FaqQuestion;

public interface IFaqQuestionDao {

	List<FaqQuestion> selectFaqQuestion(FaqQuestion faqQuestion, PageBounds pageBounds);

	List<FaqQuestion> select(Map<String, Object> param, PageBounds pageBounds);

	int deleteByIds(Map<String, Object> param);
	
	int count(Map<String,Object> parameter);

	List<FaqQuestion> selectFaqQuestion(Map<String, Object> param, PageBounds pageBounds);

	FaqQuestion selectFaqQuestionById(String id);

}
