package com.haoyu.ncts.course.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.Section;

public interface ISectionDao {

	List<Section> select(Map<String, Object> params, PageBounds pageBounds);

	int insert(Section section);

	int update(Section section);

	int deleteByLogic(Map<String, Object> param);

	Section selectByPrimaryKey(String id);

}
