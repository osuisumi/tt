package com.haoyu.tip.project.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.tip.project.service.IProjectService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class ProjectDirective extends AbstractTemplateDirectiveModel{
	@Resource
	private IProjectService projectService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = getId(params);
		if(StringUtils.isNotEmpty(id)){
			env.setVariable("project", new DefaultObjectWrapper().wrap(projectService.findProjectById(id)));
		}
		body.render(env.getOut());
	}

}
