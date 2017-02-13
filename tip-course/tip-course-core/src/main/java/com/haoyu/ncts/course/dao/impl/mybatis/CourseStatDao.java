package com.haoyu.ncts.course.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseStatDao;
import com.haoyu.ncts.course.entity.CourseStat;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CourseStatDao extends MybatisDao implements ICourseStatDao{

	@Override
	public List<CourseStat> findCourseStats(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("select", parameter, pageBounds);
	}

}
