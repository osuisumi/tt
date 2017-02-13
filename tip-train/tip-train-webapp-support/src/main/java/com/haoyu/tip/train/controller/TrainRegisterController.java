package com.haoyu.tip.train.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.tip.train.service.ITrainRegisterService;

@Controller
public class TrainRegisterController extends AbstractBaseController{
	
	@Resource 
	private ITrainRegisterService trainRegisterService;

}
