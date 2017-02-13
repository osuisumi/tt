package com.haoyu.tip.plan.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.file.entity.FileInfo;

public class Plan extends BaseEntity{
	
	private static final long serialVersionUID = 6046371856253678961L;
	
	private String name;

	private String title;

    private String content;
    
    private String state;
    
    private String type;
    
    private String projectContent;
    
    private String onlineProject;
    
    private String offlineProject;
    
    private String summary;
    
    private BigDecimal score;
    
    private String remark;
    
    private TimePeriod timePeriod = new TimePeriod();
    
	private List<PlanRelation> planRelations = Lists.newArrayList();
	
	private List<User> masters = Lists.newArrayList();
	
	private List<User> members = Lists.newArrayList();
	
	private PlanAuthorize planAuthorize;
	
	private List<PlanResult> planResults = Lists.newArrayList();
	
	private List<FileInfo> fileInfos = new ArrayList<FileInfo>();

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PlanResult> getPlanResults() {
		return planResults;
	}

	public void setPlanResults(List<PlanResult> planResults) {
		this.planResults = planResults;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public PlanAuthorize getPlanAuthorize() {
		return planAuthorize;
	}

	public void setPlanAuthorize(PlanAuthorize planAuthorize) {
		this.planAuthorize = planAuthorize;
	}

	public List<User> getMasters() {
		return masters;
	}

	public void setMasters(List<User> masters) {
		this.masters = masters;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getOnlineProject() {
		return onlineProject;
	}

	public void setOnlineProject(String onlineProject) {
		this.onlineProject = onlineProject;
	}

	public String getOfflineProject() {
		return offlineProject;
	}

	public void setOfflineProject(String offlineProject) {
		this.offlineProject = offlineProject;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<PlanRelation> getPlanRelations() {
		return planRelations;
	}

	public void setPlanRelations(List<PlanRelation> planRelations) {
		this.planRelations = planRelations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
	

}
