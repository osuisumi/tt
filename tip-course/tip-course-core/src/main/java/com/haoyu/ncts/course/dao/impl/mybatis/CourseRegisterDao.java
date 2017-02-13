package com.haoyu.ncts.course.dao.impl.mybatis;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.dao.ICourseRegisterDao;
import com.haoyu.ncts.course.entity.CourseRegister;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.utils.Collections3;

@Repository
public class CourseRegisterDao extends MybatisDao implements ICourseRegisterDao{

	@Override
	public List<CourseRegister> select(Map<String, Object> params, PageBounds pageBounds) {
		return super.selectList("select", params, pageBounds);
	}
	
	@Override
	public int insert(CourseRegister courseRegister) {
		return super.insert(courseRegister);
	}
	
	@Override
	public int update(Map<String,Object> param) {
		return super.update("update",param);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}
	
	@Override
	public int deleteByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public CourseRegister selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Map<String, Object> param) {
		return super.delete("delete",param);
	}

	@Override
	public Map<String, Integer> mapCount(Map<String, Object> parameter) {
		List<Map<String, Object>> numList = selectList("selectCount", parameter);
		Map<String, Integer> resultMap = Maps.newHashMap();
		if (Collections3.isNotEmpty(numList)) {
			for (Map<String, Object> map : numList) {
				String id = (String) map.get("id");
				Object num = (Object) map.get("num");
				if (num instanceof BigDecimal) {
					resultMap.put(id, ((BigDecimal) num).intValue());
				}else if(num instanceof Long){
					num = (Long) map.get("num");
					resultMap.put(id, ((Long) num).intValue());
				}
			}
		}
		return resultMap;
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

}
