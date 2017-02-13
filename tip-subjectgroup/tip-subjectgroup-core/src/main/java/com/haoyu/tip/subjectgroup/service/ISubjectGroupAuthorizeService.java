package com.haoyu.tip.subjectgroup.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.subjectgroup.entity.SubjectGroup;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupAuthorize;

public interface ISubjectGroupAuthorizeService {

	Response create(SubjectGroupAuthorize obj);

	Response update(SubjectGroupAuthorize obj);

	Response delete(String id);

	SubjectGroupAuthorize get(String id);

	List<SubjectGroupAuthorize> list(SearchParam searchParam, PageBounds pageBounds);

	Response deleteByPhysics(SubjectGroupAuthorize subjectGroupAuthorize);

	Response updateToMember(SubjectGroupAuthorize subjectGroupAuthorize);

	Response saveMaster(SubjectGroup subjectGroup, String userId);

	Response updateByIds(List<String> ids, SubjectGroupAuthorize subjectGroupAuthorize);

	Response saveMember(SubjectGroup subjectGroup);

	List<User> listMemberUser(SearchParam searchParam, PageBounds pageBounds);

	Response updateSubjectGroupAuthorize(SubjectGroupAuthorize subjectGroupAuthorize, String subjectGroupRelationId);

	Response deleteMember(SubjectGroupAuthorize subjectGroupAuthorize, String subjectGroupRelationId);

	Response deleteByUserId(SubjectGroupAuthorize subjectGroupAuthorize);

}
