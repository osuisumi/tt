package com.haoyu.tip.train.controller;

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
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.train.entity.Train;
import com.haoyu.tip.train.service.ITrainService;
import com.haoyu.tip.train.web.param.TrainParam;

@Controller
@RequestMapping("train")
public class TrainController extends AbstractBaseController{

	@Resource
	private ITrainService trainService;
	
	private String getLogicalViewNamePrefix(){
		return "";
	}
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	dateFormat.setLenient(false);  
	//true:允许输入空值，false:不能为空值
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="create",method = RequestMethod.GET)
	public String create(){
		return "train/edit_train";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response save(Train train){
		return trainService.createTrain(train);
	}
	
	@RequestMapping(value="{id}/edit",method = RequestMethod.GET)
	public String edit(Train train,Model model){
		model.addAttribute("train", trainService.findTrainById(train.getId()));
		return "train/edit_train";
	}
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Response update(Train train){
		return trainService.updateTrain(train);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(TrainParam trainParam,Model model){
		model.addAttribute("pageBounds",getPageBounds(10, true));
		model.addAttribute("trainParam", trainParam);
		return "train/list_train";
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Train train){
		return trainService.deleteTrain(train);
	}
	
	@RequestMapping(value="api",method = RequestMethod.GET)
	public List<Train> api(){
		return null;
	}
}
