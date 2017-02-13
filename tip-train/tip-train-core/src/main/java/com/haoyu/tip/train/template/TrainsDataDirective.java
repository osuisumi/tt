package com.haoyu.tip.train.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.tip.train.entity.Train;
import com.haoyu.tip.train.service.ITrainService;
import com.haoyu.tip.train.web.param.TrainParam;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Repository
public class TrainsDataDirective extends AbstractTemplateDirectiveModel{
	@Resource
	private ITrainService trainService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		TrainParam trainParam = new TrainParam();
		if (params.containsKey("trainParam")) {
			BeanModel beanModel = (BeanModel)params.get("trainParam");
			trainParam = (TrainParam)beanModel.getWrappedObject();
		}
		if (params.containsKey("pageBounds")) {
			BeanModel beanModel = (BeanModel) params.get("pageBounds");
			pageBounds = (PageBounds)beanModel.getWrappedObject();
		}
		Map<String,Object> parameter = getSelectParam(params);
		trainParam.setParam(parameter);
		List<Train> trains = trainService.findTrains(parameter,pageBounds);
		env.setVariable("trains", new DefaultObjectWrapper().wrap(trains));
		if(pageBounds != null && pageBounds.isContainsTotalCount()){
			PageList pageList = (PageList)trains;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
		
	}

}
