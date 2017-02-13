package com.haoyu.tip.faq.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.faq.dao.IFaqAnswerDao;
import com.haoyu.tip.faq.entity.FaqAnswer;

@Repository
public class FaqAnswerDao extends MybatisDao implements IFaqAnswerDao{

	@Override
	public int count(FaqAnswer faqAnswer) {
		return super.selectOne("count", faqAnswer);
	}

}
