package com.haoyu.tip.livestudio.service;


import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.livestudio.entity.LiveStudioUser;

public interface ILiveStudioUserService{
	
	public Response signUp(LiveStudioUser liveStudioUser);
	
	public Response cancenSignUp(String liveStudioId,String userId);
	
}