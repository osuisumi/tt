package com.haoyu.tip.train.template;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.tip.train.entity.TrainRegister;
import com.haoyu.tip.train.service.ITrainRegisterService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class TrainRegistersDirective extends AbstractTemplateDirectiveModel{
	
	@Resource
	private ITrainRegisterService trainRegisterService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		
		List<TrainRegister> trainRegisters = trainRegisterService.findTrainRegisters(parameter, pageBounds);
		env.setVariable("trainRegisters", new DefaultObjectWrapper().wrap(trainRegisters));
		if(pageBounds!=null && pageBounds.isContainsTotalCount()){
			PageList pageList = (PageList)trainRegisters;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
	}

}
