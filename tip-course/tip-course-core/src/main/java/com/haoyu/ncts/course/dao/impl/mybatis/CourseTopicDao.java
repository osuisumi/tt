package com.haoyu.ncts.course.dao.impl.mybatis;


import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.dao.ICourseTopicDao;
import com.haoyu.ncts.course.entity.CourseTopic;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CourseTopicDao extends MybatisDao implements ICourseTopicDao {

	@Override
	public CourseTopic selectCourseTopicById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertCourseTopic(CourseTopic courseTopic) {
		courseTopic.setDefaultValue();
		return super.insert(courseTopic);
	}

	@Override
	public int updateCourseTopic(CourseTopic courseTopic) {
		courseTopic.setUpdateValue();
		return super.update(courseTopic);
	}

	@Override
	public int deleteCourseTopicByLogic(CourseTopic courseTopic) {
		courseTopic.setUpdateValue();
		return super.deleteByLogic(courseTopic);
	}

	@Override
	public int deleteCourseTopicByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<CourseTopic> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	
}
