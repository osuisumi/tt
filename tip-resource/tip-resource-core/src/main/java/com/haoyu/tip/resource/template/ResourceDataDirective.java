package com.haoyu.tip.resource.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.service.IResourceService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="resourceDirective")
public class ResourceDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private IResourceService resourceService;
	@Resource
	private IAttitudeService attitudeService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Map<String,Object> parameters = getSelectParam(params);		
		Resources resource = null;
		
		if (parameters.containsKey("id") && StringUtils.isNotEmpty(parameters.get("id").toString())) {
			resource = resourceService.get(parameters.get("id").toString());
		}
		
		if (resource != null ) {
			if(parameters.containsKey("getAttitudeStatForSupport")){
				boolean getAttitudeStatForSupport = (boolean) parameters.get("getAttitudeStatForSupport");
				if(getAttitudeStatForSupport){						
					Map<String, Object> param = Maps.newHashMap();
					param.put("relationIds",Lists.newArrayList(resource.getId()));
					param.put("attitude","support");
					Map<String,AttitudeStat> attitudeStatMapForSupport = attitudeService.getAttitudeStatByParam(param);
					env.setVariable("attitudeStatMapForSupport", new DefaultObjectWrapper().wrap(attitudeStatMapForSupport));
				}
			}
		}
		env.setVariable("resourceModel", new DefaultObjectWrapper().wrap(resource));
		body.render(env.getOut());
		
	}

}
