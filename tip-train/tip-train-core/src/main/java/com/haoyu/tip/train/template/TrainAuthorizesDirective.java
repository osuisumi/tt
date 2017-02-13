package com.haoyu.tip.train.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.tip.train.entity.TrainAuthorize;
import com.haoyu.tip.train.service.ITrainAuthorizeService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class TrainAuthorizesDirective extends AbstractTemplateDirectiveModel {
	@Resource
	private ITrainAuthorizeService trainAuthorizeService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String, Object> parameter = getSelectParam(params);
		List<TrainAuthorize> trainAuthorizes = trainAuthorizeService.findTrainAuthorizes(parameter, pageBounds);
		env.setVariable("trainAuthorizes", new DefaultObjectWrapper().wrap(trainAuthorizes));
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList) trainAuthorizes;
			env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());

	}

}
