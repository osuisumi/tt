package com.haoyu.tip.announcement.entity;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;
/**
 * 公告类
 */
public class Announcement extends BaseEntity{
	
	private static final long serialVersionUID = -5824492062180160692L;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 类型  通知公告 1 和 教育快迅 2*/
	private String type;
	/** 发布时间 */
	private Date publishTime;
	/** 状态   “编辑中” 2  与 “已发布” 1 */
	private String state;
	
	private String isTop;
	
	private List<AnnouncementRelation> announcementRelations = Lists.newArrayList();
	
	/** 附件 */
	private List<FileInfo> fileInfos = Lists.newArrayList();
	
	private AnnouncementUser announcementUser;

	public AnnouncementUser getAnnouncementUser() {
		return announcementUser;
	}

	public void setAnnouncementUser(AnnouncementUser announcementUser) {
		this.announcementUser = announcementUser;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public List<AnnouncementRelation> getAnnouncementRelations() {
		return announcementRelations;
	}

	public void setAnnouncementRelations(List<AnnouncementRelation> announcementRelations) {
		this.announcementRelations = announcementRelations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

}
