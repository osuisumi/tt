package com.haoyu.tip.subjectgroup.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.subjectgroup.entity.SubjectGroup;

public interface ISubjectGroupDao {

	List<SubjectGroup> selectByRole(Map<String, Object> paramMap, PageBounds pageBounds);

	int getCount(Map<String, Object> paramMap);

	List<SubjectGroup> selectSubjectGroup(Map<String, Object> paramMap, PageBounds pageBounds);

}
