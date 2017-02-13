package com.haoyu.ncts.course.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.dao.ICourseDao;
import com.haoyu.ncts.course.entity.Course;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CourseDao extends MybatisDao implements ICourseDao{

	@Override
	public List<Course> select(Map<String, Object> params, PageBounds pageBounds) {
		return selectList("select", params, pageBounds);
	}

	@Override
	public Course selectByPrimaryKey(String id) {
		return this.selectByPrimaryKey(id, null);
	}

	@Override
	public int insert(Course course) {
		return super.insert(course);
	}

	@Override
	public int update(Course course) {
		return super.update(course);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return update("deleteByLogic", param);
	}

	@Override
	public int deleteByPhysics(List<String> ids) {
		return delete("deleteByPhysics", ids);
	}

	@Override
	public int retrieve(Map<String, Object> param) {
		return update("retrieve", param);
	}

	@Override
	public int getCount(Map<String, Object> params) {
		return selectOne("getCount", params);
	}

	@Override
	public int updateByIds(Map<String, Object> param) {
		return update("updateByIds", param);
	}

	@Override
	public Course selectByPrimaryKey(String id, String getStat) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("id", id);
		if(StringUtils.isNotEmpty(getStat)){
			parameter.put("getStat", getStat);
		}
		return super.selectOne("selectByPrimaryKey", parameter);
	}
	
	@Override
	public String getCourseMaxCode() {
		return selectOne("getCourseMaxCode");
	}
	

	@Override
	public List<Course> selectBySource(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("selectBySource", param, pageBounds);
	}
}
