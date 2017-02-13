package com.haoyu.tip.workshop.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.follow.entity.Follow;

public class Workshop extends BaseEntity{

	private static final long serialVersionUID = 7572685673279507534L;

	private String name;
	
	private String summary;
	
	private FileInfo image;
	
	private String state;
	
	private String imageUrl;
	
	private List<User> masters = Lists.newArrayList();
	
	private List<User> assists = Lists.newArrayList();
	
	private List<User> members = Lists.newArrayList();
	
	private List<WorkshopRelation> workshopRelations = Lists.newArrayList();
	
	private WorkshopAuthorize workshopAuthorize;
	
	private Follow follow;
	
	private String type;
	
	public List<User> getAssists() {
		return assists;
	}

	public void setAssists(List<User> assists) {
		this.assists = assists;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Follow getFollow() {
		return follow;
	}

	public void setFollow(Follow follow) {
		this.follow = follow;
	}

	public WorkshopAuthorize getWorkshopAuthorize() {
		return workshopAuthorize;
	}

	public void setWorkshopAuthorize(WorkshopAuthorize workshopAuthorize) {
		this.workshopAuthorize = workshopAuthorize;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<WorkshopRelation> getWorkshopRelations() {
		return workshopRelations;
	}

	public void setWorkshopRelations(List<WorkshopRelation> workshopRelations) {
		this.workshopRelations = workshopRelations;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<User> getMasters() {
		return masters;
	}

	public void setMasters(List<User> masters) {
		this.masters = masters;
	}

	public FileInfo getImage() {
		return image;
	}

	public void setImage(FileInfo image) {
		this.image = image;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
