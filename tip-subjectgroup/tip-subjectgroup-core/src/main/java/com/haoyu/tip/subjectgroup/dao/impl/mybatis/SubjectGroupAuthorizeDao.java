package com.haoyu.tip.subjectgroup.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.subjectgroup.dao.ISubjectGroupAuthorizeDao;
import com.haoyu.tip.subjectgroup.entity.SubjectGroupAuthorize;

@Repository
public class SubjectGroupAuthorizeDao extends MybatisDao implements ISubjectGroupAuthorizeDao{

	@Override
	public int insertByIds(Map<String, Object> param) {
		return insert("insertByIds", param);
	}

	@Override
	public int deleteByPhysics(SubjectGroupAuthorize subjectGroupAuthorize) {
		return delete("deleteByPhysics", subjectGroupAuthorize);
	}

	@Override
	public int updateToMember(SubjectGroupAuthorize subjectGroupAuthorize) {
		return update("updateToMember", subjectGroupAuthorize);
	}

	@Override
	public int updateByIds(Map<String, Object> param) {
		return update("updateByIds", param);
	}

	@Override
	public List<User> selectMemberUser(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("selectMemberUser", paramMap, pageBounds);
	}

	@Override
	public int deleteByUserId(SubjectGroupAuthorize subjectGroupAuthorize) {
		return delete("deleteByUserId", subjectGroupAuthorize);
	}

	@Override
	public int insertBySubjectGroupIds(Map<String, Object> param) {
		return insert("insertBySubjectGroupIds", param);
	}

}
