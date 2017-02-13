package com.haoyu.tip.project.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.tip.project.entity.Project;
import com.haoyu.tip.project.service.IProjectService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Repository
public class ProjectsDataDirective implements TemplateDirectiveModel {
	@Resource
	private IProjectService projectService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = null;
		Project project = new Project();
		String dateParam = "";
		String progressParam = "";
		if (params.containsKey("project")) {
			BeanModel beanModel = (BeanModel) params.get("project");
			project = (Project)beanModel.getWrappedObject();
		}
		if (params.containsKey("pageBounds")) {
			BeanModel beanModel = (BeanModel) params.get("pageBounds");
			pageBounds = (PageBounds)beanModel.getWrappedObject();
		}
		if(params.containsKey("dateParam")){
			dateParam = (((SimpleScalar) params.get("dateParam")).getAsString());
		}
		if(params.containsKey("progressParam")){
			progressParam = (((SimpleScalar) params.get("progressParam")).getAsString());
		}
		Map<String,Object> parammeter = new HashMap<String,Object>();
		project.setParam(parammeter);
		List<Project> projects = projectService.findProjects(parammeter,pageBounds);
		env.setVariable("projects", new DefaultObjectWrapper().wrap(projects));
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)projects;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
	}

}
