/**
 * 
 */
package com.haoyu.tip.train.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

/**
 * @author lianghuahuang
 * 培训报名审核
 */
public class TrainRegisterAudit extends BaseEntity {
	
	private String id;
	/**
	 * 审核报名
	 */
	private TrainRegister trainRegister;
	
	/**
	 * 审核结果
	 */
	private String auditResult;
	
	/**
	 * 审核意见
	 */
	private String auditOpinion;
	
	/**
	 * 审核人
	 */
	private User auditUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TrainRegister getTrainRegister() {
		return trainRegister;
	}

	public void setTrainRegister(TrainRegister trainRegister) {
		this.trainRegister = trainRegister;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}
}
