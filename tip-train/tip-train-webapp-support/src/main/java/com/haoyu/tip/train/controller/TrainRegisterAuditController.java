package com.haoyu.tip.train.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.tip.train.service.ITrainRegisterAuditService;
@Controller
public class TrainRegisterAuditController extends AbstractBaseController{
	
	@Resource
	private ITrainRegisterAuditService trainRegisterAuditService;

}
