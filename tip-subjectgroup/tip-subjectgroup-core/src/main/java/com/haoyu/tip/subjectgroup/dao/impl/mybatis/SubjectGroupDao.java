package com.haoyu.tip.subjectgroup.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.subjectgroup.dao.ISubjectGroupDao;
import com.haoyu.tip.subjectgroup.entity.SubjectGroup;

@Repository
public class SubjectGroupDao extends MybatisDao implements ISubjectGroupDao{

	@Override
	public List<SubjectGroup> selectByRole(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("selectByRole", paramMap, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> paramMap) {
		return selectOne("getCount", paramMap);
	}

	@Override
	public List<SubjectGroup> selectSubjectGroup(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

}
