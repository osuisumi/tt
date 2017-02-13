package com.haoyu.tip.announcement.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.announcement.entity.Announcement;
import com.haoyu.tip.announcement.service.IAnnouncementService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AnnouncementsDirective extends AbstractTemplateDirectiveModel{
	
	@Resource
	private IAnnouncementService announcementService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		SearchParam searchParam = new SearchParam();
		searchParam.setParamMap(parameter);
		List<Announcement> announcements = announcementService.list(searchParam, pageBounds);
		env.setVariable("announcements", new DefaultObjectWrapper().wrap(announcements));
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)announcements;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
	}
}
