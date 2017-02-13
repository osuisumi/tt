package com.haoyu.ncts.course.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ISectionDao;
import com.haoyu.ncts.course.entity.Section;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class SectionDao extends MybatisDao implements ISectionDao{

	@Override
	public List<Section> select(Map<String, Object> params, PageBounds pageBounds) {
		return selectList("select", params, pageBounds);
	}

	@Override
	public int insert(Section section) {
		return super.insert(section);
	}

	@Override
	public int update(Section section) {
		return super.update(section);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return update("deleteByLogic", param);
	}

	@Override
	public Section selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

}
