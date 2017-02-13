package com.haoyu.ncts.course.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.CourseAuthorize;

public interface ICourseAuthorizeDao {

	List<CourseAuthorize> select(Map<String, Object> param, PageBounds pageBounds);

	int insertBatch(Map<String, Object> param);

	int deleteBatch(Map<String, Object> param);

	int insert(CourseAuthorize courseAuthorize);

	int deleteByLogic(Map<String, Object> param);

}
