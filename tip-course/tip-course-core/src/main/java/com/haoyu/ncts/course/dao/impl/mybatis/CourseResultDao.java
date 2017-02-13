package com.haoyu.ncts.course.dao.impl.mybatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.ncts.course.dao.ICourseResultDao;
import com.haoyu.ncts.course.entity.CourseResult;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CourseResultDao extends MybatisDao implements ICourseResultDao{

	@Override
	public int update(CourseResult courseResult) {
		return super.update(courseResult);
	}

	@Override
	public int insert(CourseResult courseResult) {
		return super.insert(courseResult);
	}

	@Override
	public Map<String, CourseResult> selectForMap(Map<String, Object> param) {
		return super.selectMap("select", param, "user.id");
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

}
