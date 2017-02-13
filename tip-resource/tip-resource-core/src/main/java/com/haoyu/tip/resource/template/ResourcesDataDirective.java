package com.haoyu.tip.resource.template;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.service.IResourceService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="resourcesDirective")
public class ResourcesDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private IResourceService resourceService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String,Object> parameter = getSelectParam(params);
		boolean getFile = false;
		if (params.containsKey("getFile")) {
			TemplateBooleanModel model = (TemplateBooleanModel) params.get("getFile");
			if (model != null) {
				getFile = model.getAsBoolean();
			}
		}
		List<Resources> resources = resourceService.list(parameter, pageBounds, getFile);
		env.setVariable("resources", new DefaultObjectWrapper().wrap(resources));
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)resources;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
	}

}
