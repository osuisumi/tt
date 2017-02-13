package com.haoyu.tip.faq.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.faq.dao.IFaqQuestionDao;
import com.haoyu.tip.faq.entity.FaqQuestion;

@Repository
public class FaqQuestionDao extends MybatisDao implements IFaqQuestionDao{

	@Override
	public List<FaqQuestion> selectFaqQuestion(FaqQuestion faqQuestion, PageBounds pageBounds) {
		return selectList("select", faqQuestion, pageBounds);
	}

	@Override
	public int deleteByIds(Map<String, Object> param) {
		return update("deleteByIds", param);
	}

	@Override
	public int count(Map<String, Object> parameter) {
		return super.selectOne("count", parameter);
	}

	@Override
	public List<FaqQuestion> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("selectFaqQuestion", param, pageBounds);
	}

	@Override
	public List<FaqQuestion> selectFaqQuestion(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("selectByParameter", param, pageBounds);
	}

	@Override
	public FaqQuestion selectFaqQuestionById(String id) {
		return super.selectOne("selectFaqQuestionById", id);
	}

}
