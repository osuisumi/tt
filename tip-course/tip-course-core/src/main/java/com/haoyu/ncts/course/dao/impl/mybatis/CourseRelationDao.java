package com.haoyu.ncts.course.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseRelationDao;
import com.haoyu.ncts.course.entity.CourseRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CourseRelationDao extends MybatisDao implements ICourseRelationDao{

	@Override
	public CourseRelation selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertCourseRelation(CourseRelation courseRelation) {
		return super.insert(courseRelation);
	}

	@Override
	public int updateCourseRelation(CourseRelation courseRelation) {
		return super.update("update",courseRelation);
	}

	@Override
	public List<CourseRelation> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("select", parameter,pageBounds);
	}

	@Override
	public int deleteCourseRelation(Map<String, Object> parameter) {
		return super.delete("batchDelete",parameter);
	}
	

}
