package com.haoyu.tip.train.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.tip.train.service.ITrainService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class TrainDirective extends AbstractTemplateDirectiveModel {
	@Resource
	private ITrainService trainService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = getId(params);
		if (!StringUtils.isEmpty(id)) {
			env.setVariable("trainModel", new DefaultObjectWrapper().wrap(trainService.findTrainById(id)));
		}
		body.render(env.getOut());
	}

}
