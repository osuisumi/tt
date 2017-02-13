package com.haoyu.tip.livestudio.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.livestudio.entity.LiveStudio;
import com.haoyu.tip.livestudio.entity.LiveStudioUser;
import com.haoyu.tip.livestudio.service.ILiveStudioService;
import com.haoyu.tip.livestudio.service.ILiveStudioUserService;

@Controller
@RequestMapping(value="liveStudio")
public class LiveStudioController extends AbstractBaseController{
	@Resource
	private ILiveStudioService liveStudioService;
	
	@Resource
	private ILiveStudioUserService liveStudioUserService;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String addUI(Model model,HttpServletRequest request){
		model.addAllAttributes(request.getParameterMap());
		return "liveStudio/edit_liveStudio";
	}
	@RequestMapping(value="save",method = RequestMethod.POST)
	public @ResponseBody Response saveLiveStudio(LiveStudio liveStudio){
		return this.liveStudioService.create(liveStudio);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public @ResponseBody Response deleteLiveStudio(@PathVariable String id){
		return this.liveStudioService.deleteByLogic(id);
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public @ResponseBody Response updateLiveStudio(LiveStudio liveStudio){
		return this.liveStudioService.update(liveStudio);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String getByPrimaryKey(@PathVariable String id,Model model){
		model.addAttribute("liveStudio",this.liveStudioService.get(id) );
		return null;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String listLiveStudio(SearchParam searchParam,Model model){
		model.addAttribute("liveStudios", this.liveStudioService.list(searchParam, getPageBounds(3, true)));
		model.addAllAttributes(request.getParameterMap());
		return "liveStudio/list_liveStudio";
	}
	
	@RequestMapping(value="listMore",method=RequestMethod.GET)
	public String listMoreLiveStudio(SearchParam searchParam,Model model,HttpServletRequest request) {
		searchParam.getParamMap().put("userId", ThreadContext.getUser().getId());
		model.addAttribute("liveStudios", this.liveStudioService.list(searchParam, getPageBounds(10,true)));
		model.addAllAttributes(request.getParameterMap());
		model.addAttribute("searchParam", searchParam);
		return "liveStudio/list_more_liveStudio";
	}
	
	@RequestMapping(value="signUp",method=RequestMethod.POST)
	public @ResponseBody Response signUp(LiveStudioUser liveStudioUser,Model model){
		model.addAllAttributes(request.getParameterMap());
		return this.liveStudioUserService.signUp(liveStudioUser);
	}
	@RequestMapping(value="cancelSignUp",method=RequestMethod.POST)
	public @ResponseBody Response cancelSignUp(String liveStudioId,String userId,Model model){
		model.addAllAttributes(request.getParameterMap());
		return this.liveStudioUserService.cancenSignUp(liveStudioId, userId);
	}
}
