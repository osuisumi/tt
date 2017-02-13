package com.haoyu.tip.train.web.param;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.tip.train.entity.Train;

public class TrainParam extends Train {

	// 培训的时间状态 进行中 未开始 已结束 (ongoing beforeStart afterEnd)
	private String trainingTimeState;

	private String registerTimeState;

	private String electivesTimeState;

	private Date minTrainingStartTime;

	private Date maxTrainingStartTime;

	private Date minTrainingEndTime;

	private Date maxTrainingEndTime;

	private Date minRegisterStartTime;

	private Date maxRegisterStartTime;

	private Date minRegisterEndTime;

	private Date maxRegisterEndTime;

	private Date minElectivesStartTime;

	private Date maxElectivesStartTime;

	private Date minElectivesEndTime;

	private Date maxElectivesEndTime;

	private String projectIds;

	public String getTrainingTimeState() {
		return trainingTimeState;
	}

	public void setTrainingTimeState(String trainingTimeState) {
		this.trainingTimeState = trainingTimeState;
	}

	public String getRegisterTimeState() {
		return registerTimeState;
	}

	public void setRegisterTimeState(String registerTimeState) {
		this.registerTimeState = registerTimeState;
	}

	public String getElectivesTimeState() {
		return electivesTimeState;
	}

	public void setElectivesTimeState(String electivesTimeState) {
		this.electivesTimeState = electivesTimeState;
	}

	public Date getMinTrainingStartTime() {
		return minTrainingStartTime;
	}

	public void setMinTrainingStartTime(Date minTrainingStartTime) {
		this.minTrainingStartTime = minTrainingStartTime;
	}

	public Date getMaxTrainingStartTime() {
		return maxTrainingStartTime;
	}

	public void setMaxTrainingStartTime(Date maxTrainingStartTime) {
		this.maxTrainingStartTime = maxTrainingStartTime;
	}

	public Date getMinTrainingEndTime() {
		return minTrainingEndTime;
	}

	public void setMinTrainingEndTime(Date minTrainingEndTime) {
		this.minTrainingEndTime = minTrainingEndTime;
	}

	public Date getMaxTrainingEndTime() {
		return maxTrainingEndTime;
	}

	public void setMaxTrainingEndTime(Date maxTrainingEndTime) {
		this.maxTrainingEndTime = maxTrainingEndTime;
	}

	public Date getMinRegisterStartTime() {
		return minRegisterStartTime;
	}

	public void setMinRegisterStartTime(Date minRegisterStartTime) {
		this.minRegisterStartTime = minRegisterStartTime;
	}

	public Date getMaxRegisterStartTime() {
		return maxRegisterStartTime;
	}

	public void setMaxRegisterStartTime(Date maxRegisterStartTime) {
		this.maxRegisterStartTime = maxRegisterStartTime;
	}

	public Date getMinRegisterEndTime() {
		return minRegisterEndTime;
	}

	public void setMinRegisterEndTime(Date minRegisterEndTime) {
		this.minRegisterEndTime = minRegisterEndTime;
	}

	public Date getMaxRegisterEndTime() {
		return maxRegisterEndTime;
	}

	public void setMaxRegisterEndTime(Date maxRegisterEndTime) {
		this.maxRegisterEndTime = maxRegisterEndTime;
	}

	public Date getMinElectivesStartTime() {
		return minElectivesStartTime;
	}

	public void setMinElectivesStartTime(Date minElectivesStartTime) {
		this.minElectivesStartTime = minElectivesStartTime;
	}

	public Date getMaxElectivesStartTime() {
		return maxElectivesStartTime;
	}

	public void setMaxElectivesStartTime(Date maxElectivesStartTime) {
		this.maxElectivesStartTime = maxElectivesStartTime;
	}

	public Date getMinElectivesEndTime() {
		return minElectivesEndTime;
	}

	public void setMinElectivesEndTime(Date minElectivesEndTime) {
		this.minElectivesEndTime = minElectivesEndTime;
	}

	public Date getMaxElectivesEndTime() {
		return maxElectivesEndTime;
	}

	public void setMaxElectivesEndTime(Date maxElectivesEndTime) {
		this.maxElectivesEndTime = maxElectivesEndTime;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}

	public void setParam(Map<String, Object> parameter) {
		// 设置按照 进行中 未开始 已结束 的参数
		if (StringUtils.isNotEmpty(this.getTrainingTimeState())) {
			if (this.getTrainingTimeState().equals("ongoing")) {
				parameter.put("maxTrainingStartTime", new Date());
				parameter.put("minTrainingEndTime", new Date());
			} else if (this.getTrainingTimeState().equals("beforeStart")) {
				parameter.put("minTrainingStartTime", new Date());
			} else if (this.getTrainingTimeState().equals("afterEnd")) {
				parameter.put("maxTrainingEndTime", new Date());
			}
		}

		if (StringUtils.isNotEmpty(this.getRegisterTimeState())) {
			if (this.getRegisterTimeState().equals("ongoing")) {
				parameter.put("maxRegisterStartTime", new Date());
				parameter.put("minRegisterEndTime", new Date());
			} else if (this.getRegisterTimeState().equals("beforeStart")) {
				parameter.put("minRegisterStartTime", new Date());
			} else if (this.getRegisterTimeState().equals("afterEnd")) {
				parameter.put("maxRegisterEndTime", new Date());
			}
		}

		if (StringUtils.isNotEmpty(this.getElectivesTimeState())) {
			if (this.getElectivesTimeState().equals("ongoing")) {
				parameter.put("maxElectivesStartTime", new Date());
				parameter.put("minElectivesMinEndTime", new Date());
			} else if (this.getElectivesTimeState().equals("beforeStart")) {
				parameter.put("minElectivesStartTime", new Date());
			} else if (this.getElectivesTimeState().equals("afterEnd")) {
				parameter.put("maxElectivesEndTime", new Date());
			}
		}

		if (StringUtils.isNotEmpty(this.getName())) {
			parameter.put("name", this.getName());
		}
		if (StringUtils.isNotEmpty(this.getState())) {
			parameter.put("state", this.getState());
		}
		if (StringUtils.isNotEmpty(this.getType())) {
			parameter.put("type", this.getType());
		}
		if (this.getMinTrainingStartTime() != null) {
			parameter.put("minTrainingStartTime", this.getMinTrainingStartTime());
		}
		if (this.getMaxTrainingStartTime() != null) {
			parameter.put("maxTrainingStartTime", this.getMaxTrainingStartTime());
		}
		if (this.getProject() != null && StringUtils.isNotEmpty(this.getProject().getId())) {
			parameter.put("projectId", this.getProject().getId());

		}
		if(StringUtils.isNotEmpty(this.projectIds)){
			parameter.put("projectIds",Arrays.asList(this.projectIds.split(",")));
		}
	}

}
