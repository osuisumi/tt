package com.haoyu.tip.schedule.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.tip.schedule.entity.Schedule;
import com.haoyu.tip.schedule.service.IScheduleService;
import com.haoyu.tip.schedule.web.param.ScheduleParam;

/*@Controller
@RequestMapping("schedule")*/
public class ScheduleController extends AbstractBaseController{
	@Resource
	private IScheduleService scheduleServiceImpl;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	dateFormat.setLenient(false);  
	//true:允许输入空值，false:不能为空值
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create(Schedule schedule,Model model){
		model.addAttribute("schedule", schedule);
		return "schedule/edit_schedule";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response save(Schedule schedule){
		return scheduleServiceImpl.createSchedule(schedule);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(ScheduleParam scheduleParam,Model model){
		model.addAttribute("pageBounds", getPageBounds(10, true));
		model.addAttribute("scheduleParam", scheduleParam);
		return "schedule/schedule";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public Response delete(Schedule schedule){
		return scheduleServiceImpl.deleteSchedule(schedule);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Response update(Schedule schedule){
		return scheduleServiceImpl.updateSchedule(schedule);
	}
	
	@RequestMapping(value="my",method = RequestMethod.GET)
	public String mySchedule(){
		return "schedule/my_schedule";
	}
	
	@RequestMapping(value="api",method=RequestMethod.GET)
	@ResponseBody
	public List<Schedule> api(ScheduleParam scheduleParam){
		return scheduleServiceImpl.findSchedules(scheduleParam);
	}
	

}
