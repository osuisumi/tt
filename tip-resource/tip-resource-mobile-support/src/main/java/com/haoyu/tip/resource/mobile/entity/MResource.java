package com.haoyu.tip.resource.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.mobile.file.entity.MFileInfo;

public class MResource implements Serializable{

	private static final long serialVersionUID = -1867616764898610221L;

	private String id;
	
	private String title;
	
	private List<MFileInfo> fileInfos = Lists.newArrayList();

	public MResource() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MFileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<MFileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
}
