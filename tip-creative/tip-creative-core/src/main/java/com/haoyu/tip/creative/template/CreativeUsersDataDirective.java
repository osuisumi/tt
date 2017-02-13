package com.haoyu.tip.creative.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.tip.creative.entity.CreativeUser;
import com.haoyu.tip.creative.service.ICreativeUserService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="creativeUsersData")
public class CreativeUsersDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ICreativeUserService creativeUserService;
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String,Object> paramerts = getSelectParam(params);
		
		if (paramerts.containsKey("getTypeManager")) {
			boolean getTypeManager = (boolean) paramerts.get("getTypeManager");
			if (getTypeManager) {
				paramerts.put("creativeIds",Lists.newArrayList("study","teach","innovate"));
			}
		}
		
		List<CreativeUser> creativeUsers = creativeUserService.findCreativeUsers(paramerts, pageBounds);
		
		env.setVariable("creativeUsers", new DefaultObjectWrapper().wrap(creativeUsers));
		body.render(env.getOut());	
	}

}
