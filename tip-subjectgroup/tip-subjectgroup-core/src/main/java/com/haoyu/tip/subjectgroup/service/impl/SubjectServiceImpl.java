package com.haoyu.tip.subjectgroup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.subjectgroup.dao.ISubjectDao;
import com.haoyu.tip.subjectgroup.entity.Subject;
import com.haoyu.tip.subjectgroup.service.ISubjectService;

@Service
public class SubjectServiceImpl implements ISubjectService {
	
	@Resource
	private ISubjectDao subjectDao;

	@Override
	public Response create(Subject obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)subjectDao);
	}

	@Override
	public Response update(Subject obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)subjectDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)subjectDao);
	}

	@Override
	public Subject get(String id) {
		return (Subject) BaseServiceUtils.get(id, (MybatisDao)subjectDao);
	}

	@Override
	public List<Subject> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)subjectDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
}
