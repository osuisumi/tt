/**
 * 
 */
package com.haoyu.tip.project.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.project.entity.Project;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IProjectService{
	
	Response createProject(Project project);
	
	Response updateProject(Project project);
	
	Response deleteProject(Project project);
	
	Response deleteProjects(String ids);
	
	Project findProjectById(String id);
	
	List<Project> findProjects(Project project);
	
	List<Project> findProjects(Project project,PageBounds pageBounds);
	
	List<Project> findProjects(Map<String,Object> parameter);
	
	List<Project> findProjects(Map<String,Object> parameter,PageBounds pageBounds);
}
