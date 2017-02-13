/**
 * 
 */
package com.haoyu.tip.project.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.project.entity.Project;

/**
 * @author lianghuahuang
 *
 */
public interface IProjectDao {
	
	Project selectProjectById(String id);
	
	int insertProject(Project project);
	
	int updateProject(Project project);
	
	int deleteProjectByLogic(Project project);
	
	int deleteProjectsByLogic(List<String> ids);
	
	int deleteProjectByPhysics(String id);

	List<Project> findAll(Map<String, Object> parameter);
	
	List<Project> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
