package com.haoyu.ncts.course.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseAuthorizeDao;
import com.haoyu.ncts.course.entity.CourseAuthorize;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CourseAuthorizeDao extends MybatisDao implements ICourseAuthorizeDao{

	@Override
	public List<CourseAuthorize> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

	@Override
	public int insertBatch(Map<String, Object> param) {
		return insert("insertBatch", param);
	}

	@Override
	public int deleteBatch(Map<String, Object> param) {
		return update("deleteBatch", param);
	}

	@Override
	public int insert(CourseAuthorize courseAuthorize) {
		return super.insert(courseAuthorize);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}

}
