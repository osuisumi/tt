package com.haoyu.ncts.course.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseRegisterStatDao;
import com.haoyu.ncts.course.entity.CourseRegisterStat;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CourseRegisterStatDao extends MybatisDao implements ICourseRegisterStatDao{

	@Override
	public List<CourseRegisterStat> findCourseRegisterStats(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("select", parameter, pageBounds);
	}

}
