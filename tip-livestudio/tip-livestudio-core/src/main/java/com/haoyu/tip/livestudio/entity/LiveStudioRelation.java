package com.haoyu.tip.livestudio.entity;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class LiveStudioRelation extends BaseEntity{
	private static final long serialVersionUID = 6583416895202328636L;
	private Relation relation;
	private String liveStudioId;
	
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	public String getLiveStudioId() {
		return liveStudioId;
	}
	public void setLiveStudioId(String liveStudioId) {
		this.liveStudioId = liveStudioId;
	}
	
	
	

}
