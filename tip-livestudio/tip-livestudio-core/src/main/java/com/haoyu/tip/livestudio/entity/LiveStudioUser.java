package com.haoyu.tip.livestudio.entity;

import java.util.List;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

public class LiveStudioUser extends BaseEntity{
	private static final long serialVersionUID = -7636761207468606087L;
	private String id;
	private List<LiveStudio> liveStudios;
	private String liveStudioId;
	private User user;
	private String userType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLiveStudioId() {
		return liveStudioId;
	}
	public void setLiveStudioId(String liveStudioId) {
		this.liveStudioId = liveStudioId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<LiveStudio> getLiveStudios() {
		return liveStudios;
	}
	public void setLiveStudios(List<LiveStudio> liveStudios) {
		this.liveStudios = liveStudios;
	}
	
	
	
	

}
