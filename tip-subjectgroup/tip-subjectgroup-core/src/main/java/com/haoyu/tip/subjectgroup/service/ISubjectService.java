package com.haoyu.tip.subjectgroup.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.subjectgroup.entity.Subject;

public interface ISubjectService {

	Response create(Subject obj);

	Response update(Subject obj);

	Response delete(String id);

	Subject get(String id);

	List<Subject> list(SearchParam searchParam, PageBounds pageBounds);
}
