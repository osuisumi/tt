package com.haoyu.tip.resource.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.tag.entity.Tag;

public class Resources extends BaseEntity{

	private static final long serialVersionUID = 8619500894895835342L;

	private String title;
	
	private String summary;
	
	private String type;
	
	private String state;	//auditing //reject published
	
	private String belong;
	
	private String privilege;
	
	private List<ResourceRelation> resourceRelations = Lists.newArrayList();
	
	private List<FileInfo> fileInfos = Lists.newArrayList();
	
	private List<Tag> tags = Lists.newArrayList();
	
	private ResourceExtend resourceExtend;
	
	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public ResourceExtend getResourceExtend() {
		return resourceExtend;
	}

	public void setResourceExtend(ResourceExtend resourceExtend) {
		this.resourceExtend = resourceExtend;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public List<ResourceRelation> getResourceRelations() {
		return resourceRelations;
	}

	public void setResourceRelations(List<ResourceRelation> resourceRelations) {
		this.resourceRelations = resourceRelations;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
