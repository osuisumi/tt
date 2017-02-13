/**
 * 
 */
package com.haoyu.tip.project.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.tip.project.dao.IProjectDao;
import com.haoyu.tip.project.entity.Project;
import com.haoyu.tip.project.service.IProjectService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class ProjectServiceImpl implements IProjectService {
	@Resource 
	private IProjectDao projectDao;
	@Resource
	private IFileService fileService;

	/* (non-Javadoc)
	 * @see com.haoyu.tip.project.service.IProjectService#createProject(com.haoyu.tip.project.entity.Project)
	 */
	@Override
	public Response createProject(Project project) {
		if(project==null){
			return Response.failInstance().responseMsg("createProject fail!project is null!");
		}
		if(StringUtils.isEmpty(project.getId())){
			project.setId(Identities.uuid2());
		}
		if(project.getFileInfo()!=null){
			fileService.createFile(project.getFileInfo(), project.getId(), "cms-project-weixinqrcode");
			FileInfo fi = project.getFileInfo();
			project.setInfoImage(fi.getUrl());
		}
		if(!CollectionUtils.isEmpty(project.getFileInfos())){
			fileService.createFileList(project.getFileInfos(), project.getId(), "project_files");
		}
		int count = projectDao.insertProject(project);
		return count>0?Response.successInstance().responseData(project):Response.failInstance().responseMsg("createProject fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.project.service.IProjectService#updateProject(com.haoyu.tip.project.entity.Project)
	 */
	@Override
	public Response updateProject(Project project) {
		if(project==null||StringUtils.isEmpty(project.getId())){
			return Response.failInstance().responseMsg("updateProject is fail!project is null or project's id is null");
		}
		if(project.getFileInfo()!=null){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(new Relation(project.getId(),"cms-project-weixinqrcode"));
			fileService.updateFileList(Lists.newArrayList(project.getFileInfo()), oldFileInfos, project.getId(), "cms-project-weixinqrcode");
			FileInfo fi = project.getFileInfo();
			project.setInfoImage(fi.getUrl());
		}
		if(!CollectionUtils.isEmpty(project.getFileInfos())){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(new Relation(project.getId(),"project_files"));
			fileService.updateFileList(project.getFileInfos(), oldFileInfos, project.getId(), "project_files");
		}
		int count = projectDao.updateProject(project);
		return count>0?Response.successInstance().responseData(project):Response.failInstance().responseMsg("updateProject fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.project.service.IProjectService#deleteProject(com.haoyu.tip.project.entity.Project)
	 */
	@Override
	public Response deleteProject(Project project) {
		if(project==null||StringUtils.isEmpty(project.getId())){
			return Response.failInstance().responseMsg("deleteProject is fail!project is null or project's id is null");
		}
		int count = projectDao.deleteProjectByLogic(project);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteProject fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.project.service.IProjectService#findProjectById(java.lang.String)
	 */
	@Override
	public Project findProjectById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return projectDao.selectProjectById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.project.service.IProjectService#findProjects(com.haoyu.tip.project.entity.Project)
	 */
	@Override
	public List<Project> findProjects(Project project) {
		return findProjects(project,null);
	}

	@Override
	public List<Project> findProjects(Project project, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		project.setParam(parameter);
		return projectDao.findAll(parameter,pageBounds);
	}

	@Override
	public List<Project> findProjects(Map<String, Object> parameter) {
		return projectDao.findAll(parameter);
	}

	@Override
	public List<Project> findProjects(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return projectDao.findAll(parameter,pageBounds);
	}

	@Override
	public Response deleteProjects(String ids) {
		if(StringUtils.isEmpty(ids)){
			return Response.failInstance().responseMsg("delete projects fail,ids is empty");
		}
		int count = projectDao.deleteProjectsByLogic(Arrays.asList(ids.split(",")));
		return count>0?Response.successInstance():Response.failInstance();
	}

}
