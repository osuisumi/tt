package com.haoyu.tip.plan.entity;


import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class PlanRelation extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4740298786139487743L;

	private Plan plan;
	
    private Relation relation;
    
    private String type;
    
	private TimePeriod timePeriod = new TimePeriod();
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public static String getId(String planId, String relationId){
		return DigestUtils.md5Hex(planId+relationId);
	}
   
}