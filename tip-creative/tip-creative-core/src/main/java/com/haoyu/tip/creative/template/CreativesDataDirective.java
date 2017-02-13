package com.haoyu.tip.creative.template;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.status.entity.Status;
import com.haoyu.sip.status.service.IStatusService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.creative.entity.Creative;
import com.haoyu.tip.creative.entity.CreativeUser;
import com.haoyu.tip.creative.service.ICreativeService;
import com.haoyu.tip.creative.service.ICreativeUserService;
import com.haoyu.tip.creative.utils.CreativeUserRole;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="creativesData")
public class CreativesDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ICreativeService creativeService;
	@Resource
	private IStatusService statusService;
	@Resource
	private IAttitudeService attitudeService;
	@Resource
	private ICommentService commentService;
	@Resource
	private ICreativeUserService creativeUserService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String,Object> paramerts = getSelectParam(params);
		
		if (paramerts.containsKey("state") && StringUtils.isNotEmpty(paramerts.get("state").toString())) {
			String state = paramerts.get("state").toString();
			if ("my".equals(state)) {
					paramerts.put("userId",ThreadContext.getUser().getId());
					paramerts.put("role", CreativeUserRole.MANAGER);
			}
			if ("in_progress".equals(state)) {
				paramerts.put("startTimeLessThan",new Date());
				paramerts.put("endTimeGreater",new Date());
			}
			if ("chosen".equals(state)) {
				paramerts.put("endTimeLess",new Date());
			}
			paramerts.remove("state");
		}
		if (paramerts.containsKey("types") && StringUtils.isNotEmpty(paramerts.get("types").toString().trim())) {
			paramerts.put("types", Arrays.asList(paramerts.get("types").toString().trim().split(",")));
		}
		if (paramerts.containsKey("startTimeLessThan")) {
			SimpleDateFormat format =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTimeLessThanStr = paramerts.get("startTimeLessThan").toString();
			try {
				Date startTimeLessThan = format.parse(startTimeLessThanStr);
				paramerts.put("startTimeLessThan", startTimeLessThan);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (paramerts.containsKey("endTimeGreater")) {
			SimpleDateFormat format =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endTimeGreaterStr = paramerts.get("endTimeGreater").toString();
			try {
				Date endTimeGreater = format.parse(endTimeGreaterStr);
				paramerts.put("endTimeGreater", endTimeGreater);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		List<Creative> creatives = creativeService.findCreatives(paramerts, pageBounds);
		env.setVariable("creatives", new DefaultObjectWrapper().wrap(creatives));
		
		if (Collections3.isNotEmpty(creatives)) {			
			if(paramerts.containsKey("getAttitudeStatForSupport")){
				boolean getAttitudeStatForSupport = (boolean) paramerts.get("getAttitudeStatForSupport");
				if(getAttitudeStatForSupport){						
					Map<String, Object> param = Maps.newHashMap();
					param.put("relationIds",Collections3.extractToList(creatives, "id"));
					param.put("attitude","support");
					Map<String,AttitudeStat> attitudeStatMapForSupport = attitudeService.getAttitudeStatByParam(param);
					env.setVariable("attitudeStatMapForSupport", new DefaultObjectWrapper().wrap(attitudeStatMapForSupport));
				}
			}
			
			if (paramerts.containsKey("getCreativeUser")) {
				boolean getCreativeUser = (boolean) paramerts.get("getCreativeUser");
				if (getCreativeUser) {
					Map<String, Object> param = Maps.newHashMap();
					param.put("creativeIds",Collections3.extractToList(creatives, "id"));
					param.put("role", CreativeUserRole.MANAGER);
					Map<String, CreativeUser> creativeUserMap = creativeUserService.getCreativeUserMap(param);
					env.setVariable("creativeUserMap", new DefaultObjectWrapper().wrap(creativeUserMap));
				}
			}
			
			if (paramerts.containsKey("getCreativeAdviceNum")) {
				boolean getCreativeAdviceNum = (boolean) paramerts.get("getCreativeAdviceNum");				
				Map<String,Object> param = Maps.newHashMap();
				Map<String,Integer> creativeAdviceNumMap = Maps.newHashMap();
				for (Creative c : creatives) {
					param = Maps.newHashMap();
					param.put("relationType","creative_advice");
					param.put("relationId",c.getId());
					
					int creativeAdviceNum =  commentService.getCount(param);
					creativeAdviceNumMap.put(c.getId(), creativeAdviceNum);
				}
				env.setVariable("creativeAdviceNumMap", new DefaultObjectWrapper().wrap(creativeAdviceNumMap));
			}
			
			if (paramerts.containsKey("getAdviceUserNum")) {
				boolean getAdviceUserNum = (boolean) paramerts.get("getAdviceUserNum");				
				if (getAdviceUserNum) {
					Map<String, Object> param = Maps.newHashMap();
					param.put("creativeIds",Collections3.extractToList(creatives, "id"));
					Map<String, Integer> adviceUserNumMap =  creativeService.getAdviceUserNum(param);
					env.setVariable("adviceUserNumMap", new DefaultObjectWrapper().wrap(adviceUserNumMap));
				}
			}
			
		}
		
		if (paramerts.containsKey("getStatus")) {
			boolean status = (boolean) paramerts.get("getStatus");
			if (status && Collections3.isNotEmpty(creatives)) {
				List<String> relationIds = Collections3.extractToList(creatives, "id");
				Map<String, Map<String, Status>> statusMap = statusService.mapStatus(relationIds);
				env.setVariable("statusMap", new DefaultObjectWrapper().wrap(statusMap));
			}
		}
		
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)creatives;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		
		body.render(env.getOut());	
	}

}
