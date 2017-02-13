package com.haoyu.tip.creative.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.creative.entity.Creative;
import com.haoyu.tip.creative.entity.CreativeRelation;
import com.haoyu.tip.creative.service.ICreativeRelationService;
import com.haoyu.tip.creative.service.ICreativeService;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.service.IResourceService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="creativeData")
public class CreativeDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ICreativeService creativeService;
	@Resource
	private ICreativeRelationService creativeRelationService;
	@Resource
	private IResourceService resourceService;
	@Resource
	private IAttitudeService attitudeService;
	@Resource
	private ICommentService commentService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Map<String,Object> parameters = getSelectParam(params);
		Creative creative = null;
		
		if (parameters.containsKey("id") && StringUtils.isNotEmpty(parameters.get("id").toString())) {
			String id = (String) parameters.get("id");
			if (parameters.containsKey("op") && StringUtils.isNotEmpty(parameters.get("op").toString())) {
				PageBounds pageBoundsForOP = new PageBounds(1);
				List<Creative> creatives = creativeService.findCreativeByOp(parameters,pageBoundsForOP);
				if (creatives != null && creatives.size() > 0) {
					creative = creatives.get(0);
				}
			}else{
				creative = creativeService.findCreativeById(id);
			}
			if (creative != null) {
				if (parameters.containsKey("updateBrowseNum")) {
					boolean updateBrowseNum = (boolean) parameters.get("updateBrowseNum");
					if (updateBrowseNum) {
						if (Collections3.isNotEmpty(creative.getCreativeRelations())) {
							for(CreativeRelation creativeRelation: creative.getCreativeRelations()){
								CreativeRelation mr = new CreativeRelation();
								mr.setId(creativeRelation.getId());
								Runnable r = ()->{
									try {
										Thread.sleep(RandomUtils.nextInt(5000, 15000));
										creativeRelationService.updateBrowseNum(mr);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								};
								new Thread(r).start();
							}
						}
					}
				}
				if (parameters.containsKey("getResources")) {	
					boolean getResources = (boolean) parameters.get("getResources");
					if (getResources) {						
						Map<String, Object> param = Maps.newHashMap();
						param.put("relationId", creative.getId());
						param.put("relationIds", Lists.newArrayList(creative.getId()));
						List<Resources> resources = resourceService.list(param, null);
						if (Collections3.isNotEmpty(resources)) {
							creative.setResources(resources);
						}
					}
				}
				if(parameters.containsKey("getAttitudeStatForSupport")){
					boolean getAttitudeStatForSupport = (boolean) parameters.get("getAttitudeStatForSupport");
					if(getAttitudeStatForSupport){						
						Map<String, Object> param = Maps.newHashMap();
						param.put("relationIds",Lists.newArrayList(creative.getId()));
						param.put("attitude","support");
						Map<String,AttitudeStat> attitudeStatMapForSupport = attitudeService.getAttitudeStatByParam(param);
						env.setVariable("attitudeStatMapForSupport", new DefaultObjectWrapper().wrap(attitudeStatMapForSupport));
					}
				}
				
				if (parameters.containsKey("getAdviceNum")) {
					boolean getAdviceNum = (boolean) parameters.get("getAdviceNum");
					if (getAdviceNum) {
						Map<String,Object> param = Maps.newHashMap();
						param.put("relationType","creative_advice");
						param.put("relationId",creative.getId());
						
						int creativeAdviceNum =  commentService.getCount(param);
						env.setVariable("creativeAdviceNum", new DefaultObjectWrapper().wrap(creativeAdviceNum));
					}
				}
				
				if (parameters.containsKey("getAdviceUser")) {
					boolean getAdviceUser = (boolean) parameters.get("getAdviceUser");
					if (getAdviceUser) {
						Map<String, User> adviceUserMap = Maps.newHashMap();
						
						Map<String,Object> param = Maps.newHashMap();
						param.put("relationId",creative.getId());
						param.put("relationType","creative_advice");
						List<Comment> advices = commentService.list(param, null);
						
						if (Collections3.isNotEmpty(advices)) {
							for (Comment c : advices) {
								adviceUserMap.put(c.getCreator().getId(), c.getCreator());
							}
						}
						env.setVariable("adviceUserMap", new DefaultObjectWrapper().wrap(adviceUserMap));
					}
				}
				
				if (parameters.containsKey("getResourceCount")) {
					boolean getResourceCount = (boolean) parameters.get("getResourceCount");
					if (getResourceCount) {
						Map<String,Object> param = Maps.newHashMap();
						param.put("relationType","creative");
						param.put("relationId",creative.getId());
						
						int creativeResourceCount =  creativeService.getResourceCount(param);
						env.setVariable("creativeResourceCount", new DefaultObjectWrapper().wrap(creativeResourceCount));
					}
				}
				
				if (parameters.containsKey("getResourceCreatorCount")) {
					boolean getResourceCreatorCount = (boolean) parameters.get("getResourceCreatorCount");
					if (getResourceCreatorCount) {
						Map<String,Object> param = Maps.newHashMap();
						param.put("relationType","creative");
						param.put("relationId",creative.getId());
						
						int creativeResourceCreatorCount = creativeService.getResourceCreatorCount(param);
						env.setVariable("creativeResourceCreatorCount", new DefaultObjectWrapper().wrap(creativeResourceCreatorCount));
					}
				}
			}
		}
		env.setVariable("creativeModel", new DefaultObjectWrapper().wrap(creative));
		body.render(env.getOut());
	}

}
