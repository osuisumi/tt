package com.haoyu.tip.subjectgroup.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.entity.User;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupAuthorize;

public interface ISubjectGroupAuthorizeDao {

	int insertByIds(Map<String, Object> param);

	int deleteByPhysics(SubjectGroupAuthorize subjectGroupAuthorize);

	int updateToMember(SubjectGroupAuthorize subjectGroupAuthorize);

	int updateByIds(Map<String, Object> param);

	List<User> selectMemberUser(Map<String, Object> paramMap, PageBounds pageBounds);

	int deleteByUserId(SubjectGroupAuthorize subjectGroupAuthorize);

	int insertBySubjectGroupIds(Map<String, Object> param);

}
