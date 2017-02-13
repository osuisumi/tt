package com.haoyu.tip.announcement.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.tip.announcement.entity.Announcement;
import com.haoyu.tip.announcement.entity.AnnouncementRelation;
import com.haoyu.tip.announcement.service.IAnnouncementService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AnnouncementDataDirective implements TemplateDirectiveModel{
	
	@Resource
	private IAnnouncementService announcementService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if (params.containsKey("id") && params.containsKey("relationId")) {
			String id = params.get("id").toString();
			String relationId = params.get("relationId").toString();
			Announcement announcement = new Announcement();
			announcement.setId(id);
			AnnouncementRelation announcementRelation = new AnnouncementRelation();
			announcementRelation.setRelation(new Relation(relationId));
			announcement.setAnnouncementRelations(Lists.newArrayList(announcementRelation));
			announcement = announcementService.viewAnnouncement(announcement);
			if (announcement != null) {
				env.setVariable("announcement", new DefaultObjectWrapper().wrap(announcement));
			}else{
				env.setVariable("announcement", new DefaultObjectWrapper().wrap(new Announcement()));
			}
		}
		body.render(env.getOut());
	}
}
