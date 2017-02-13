package com.haoyu.tip.livestudio.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import com.haoyu.base.entity.BaseEntity;

public class LiveStudio extends BaseEntity{
	private static final long serialVersionUID = 4667300367104228997L;
	private String id;
	private String expertNames;
	private String title;
	private int nowNum;
	private int maxNum;
	private String description;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date endTime;
	private List<LiveStudioUser> liveStudioUsers = new ArrayList<LiveStudioUser>();
	private List<LiveStudioRelation> liveStudioRelations;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpertNames() {
		return expertNames;
	}
	public void setExpertNames(String expertNames) {
		this.expertNames = expertNames;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNowNum() {
		return nowNum;
	}
	public void setNowNum(int nowNum) {
		this.nowNum = nowNum;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<LiveStudioUser> getLiveStudioUsers() {
		return liveStudioUsers;
	}
	public void setLiveStudioUsers(List<LiveStudioUser> liveStudioUsers) {
		this.liveStudioUsers = liveStudioUsers;
	}
	public List<LiveStudioRelation> getLiveStudioRelations() {
		return liveStudioRelations;
	}
	public void setLiveStudioRelations(List<LiveStudioRelation> liveStudioRelations) {
		this.liveStudioRelations = liveStudioRelations;
	}

	
}
