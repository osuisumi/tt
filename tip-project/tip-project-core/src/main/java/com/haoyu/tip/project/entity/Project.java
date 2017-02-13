/**
 * 
 */
package com.haoyu.tip.project.entity;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * @author lianghuahuang 项目信息
 */
public class Project extends BaseEntity {

	private static final long serialVersionUID = -1945642711202123286L;

	private String id;

	private String relationId;

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 项目描述
	 */
	private String description;

	/**
	 * 项目资料图片
	 */
	private String infoImage;

	/**
	 * 项目介绍视频
	 */
	private String introVideo;

	/**
	 * 项目类型
	 */
	private String type;

	/**
	 * 项目状态
	 */
	private String state;

	/**
	 * 项目网址
	 */
	private String site;

	/**
	 * 项目级别
	 */
	private String projectLevel;

	/**
	 * 开始,结束时间
	 */
	private TimePeriod timePeriod;

	// 用于接收项目封面图片
	private FileInfo fileInfo;
	// 附件
	private List<FileInfo> fileInfos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getInfoImage() {
		return infoImage;
	}

	public void setInfoImage(String infoImage) {
		this.infoImage = infoImage;
	}

	public String getIntroVideo() {
		return introVideo;
	}

	public void setIntroVideo(String introVideo) {
		this.introVideo = introVideo;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getProjectLevel() {
		return projectLevel;
	}

	public void setProjectLevel(String projectLevel) {
		this.projectLevel = projectLevel;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public void setParam(Map<String, Object> param) {
		if (StringUtils.isNotEmpty(this.getName())) {
			param.put("name", this.getName());
		}
		if (StringUtils.isNotEmpty(this.getState())) {
			param.put("state", this.getState());
		}
		if (StringUtils.isNotEmpty(this.getType())) {
			param.put("type", this.getType());
		}
		if (StringUtils.isNotEmpty(this.getRelationId())) {
			param.put("relationId", this.getRelationId());
		}
	}

}
