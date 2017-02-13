package com.haoyu.tip.faq.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.tip.faq.entity.FaqQuestion;
import com.haoyu.tip.faq.service.IFaqQuestionService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="faqQuestionDirective")
public class FaqQuestionDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private IFaqQuestionService faqQuestionService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameters = getSelectParam(params);
		
		FaqQuestion faqQuestion = null;
		
		if (parameters.containsKey("id") && StringUtils.isNotEmpty(parameters.get("id").toString())){			
			faqQuestion = faqQuestionService.getFaqQuestion(parameters.get("id").toString());
		}
		
		env.setVariable("faqQuestionModel", new DefaultObjectWrapper().wrap(faqQuestion));
		body.render(env.getOut());
	}

}
