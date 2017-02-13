package com.haoyu.tip.subjectgroup.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.subjectgroup.entity.SubjectGroup;

public interface ISubjectGroupService {
	
	Response create(SubjectGroup obj);

	Response update(SubjectGroup obj);

	Response delete(String id);

	SubjectGroup get(String id);

	List<SubjectGroup> list(SearchParam searchParam, PageBounds pageBounds);

	Response createSubjectGroup(SubjectGroup subjectGroup);

	List<SubjectGroup> listByRole(SearchParam searchParam, PageBounds pageBounds);

	int getCount(Map<String, Object> paramMap);

}
