package com.haoyu.tip.creative.template;

import java.io.IOException;
import java.rmi.Remote;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.tip.creative.service.ICreativeService;
import com.haoyu.tip.creative.utils.CreativeState;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="creativeNumData")
public class CreativeNumDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ICreativeService creativeService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		
		parameter.put("states", Lists.newArrayList(CreativeState.PASSED,CreativeState.EXCELLENT,CreativeState.NO_EXCELLENT));
		int allNum = creativeService.getCount(parameter);
		env.setVariable("allNum", new DefaultObjectWrapper().wrap(allNum));
		
		if (ThreadContext.getUser() != null && StringUtils.isNotEmpty(ThreadContext.getUser().getId())) {			
			parameter.remove("states");
			parameter.put("userId", ThreadContext.getUser().getId());
			int myNum = creativeService.getCount(parameter);
			env.setVariable("myNum", new DefaultObjectWrapper().wrap(myNum));
		}
		
		parameter.remove("userId");
		parameter.put("states", Lists.newArrayList(CreativeState.PASSED,CreativeState.EXCELLENT,CreativeState.NO_EXCELLENT));
		parameter.put("startTimeLessThan",new Date());
		parameter.put("endTimeGreater",new Date());
		int inProgressNum = creativeService.getCount(parameter);
		env.setVariable("inProgressNum", new DefaultObjectWrapper().wrap(inProgressNum));
		
		parameter.remove("startTimeLessThan");
		parameter.remove("endTimeGreater");
		parameter.put("endTimeLess",new Date());
		int endNum = creativeService.getCount(parameter);
		env.setVariable("endNum", new DefaultObjectWrapper().wrap(endNum));
		
		body.render(env.getOut());
	}

}
