package com.haoyu.tip.train.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.tip.train.entity.Train;
import com.haoyu.tip.train.service.ITrainService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Repository
public class TrainMapDirective extends AbstractTemplateDirectiveModel{
	
	@Resource
	private ITrainService trainService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		Map<String, Train> trainMap = trainService.mapTrain(parameter);
		env.setVariable("trainMap", new DefaultObjectWrapper().wrap(trainMap));
		body.render(env.getOut());
		
	}

}
