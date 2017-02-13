package com.haoyu.tip.plan.controller;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.plan.entity.Plan;
import com.haoyu.tip.plan.service.IPlanService;

@Controller
@RequestMapping("plan")
public class PlanController extends AbstractBaseController{

	@Resource
	private IPlanService planService;

	@RequestMapping(method=RequestMethod.GET)
	public String list(SearchParam searchParam, Model model){
		model.addAttribute("plans", planService.list(searchParam, getPageBounds(10, true)));
		return "plan/list_plan";
	}
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(Plan plan, Model model){
		model.addAttribute("plan", plan);
		return "plan/edit_plan";
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(Plan plan, Model model){
		model.addAttribute("plan",planService.get(plan.getId()));
		return "plan/edit_plan";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Response save(Plan plan,String startTime,String endTime, Model model){
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				plan.getPlanRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
			if (StringUtils.isNotEmpty(endTime)) {
				plan.getPlanRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("plan", planService.get(plan.getId()));
		if (StringUtils.isEmpty(plan.getId())) {
			return planService.createPlan(plan);
		}else{
			return planService.updatePlan(plan);
		}
	}
	
	@RequestMapping(value="more", method=RequestMethod.GET)
	public String more(SearchParam searchParam, Model model){
		model.addAttribute("plans", planService.list(searchParam, getPageBounds(10, true)));
		
		return "plan/list_more_plan";
	}
	
	@RequestMapping(value="{id}/view", method=RequestMethod.GET)
	public String view(Plan plan, Model model){	
		model.addAttribute("plan", planService.viewPlan(plan));
		return "plan/view_plan";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Plan plan){
		return planService.deletePlan(plan);
	}
}
