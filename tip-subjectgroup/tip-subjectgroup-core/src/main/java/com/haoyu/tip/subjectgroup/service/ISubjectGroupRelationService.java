package com.haoyu.tip.subjectgroup.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupRelation;

public interface ISubjectGroupRelationService {

	Response create(SubjectGroupRelation obj);

	Response update(SubjectGroupRelation obj);

	Response delete(String id);

	SubjectGroupRelation get(String id);

	List<SubjectGroupRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateMemberNum(SubjectGroupRelation subjectGroupRelation);

	Response updatePlanNum(SubjectGroupRelation subjectGroupRelation);
}
