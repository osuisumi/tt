package com.haoyu.tip.project.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController {
	
	@RequestMapping(value="test")
	public String test(){
		return "test";
	}
	@RequestMapping
	public String index(){
		return "index";
	}

}
