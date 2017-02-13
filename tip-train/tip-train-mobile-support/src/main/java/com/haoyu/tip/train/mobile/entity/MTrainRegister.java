package com.haoyu.tip.train.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.user.mobile.entity.MUser;

public class MTrainRegister implements Serializable{
	
	private static final long serialVersionUID = 9126463104459534481L;
	
	private String id;
	
	private MUser mUser;

	public MUser getmUser() {
		return mUser;
	}

	public void setmUser(MUser mUser) {
		this.mUser = mUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.mUser = new MUser();
		BeanUtils.copyProperties(user, this.mUser);
	}
	

}
