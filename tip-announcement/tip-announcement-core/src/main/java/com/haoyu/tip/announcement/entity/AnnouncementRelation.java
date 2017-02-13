package com.haoyu.tip.announcement.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class AnnouncementRelation extends BaseEntity{
	
	private static final long serialVersionUID = -7751869719868853874L;

	private Announcement announcement;
	
	private Relation relation;
	
	private int browseNum;

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}
	
	public static String getId(String announcementId, String relationId){
		return DigestUtils.md5Hex(announcementId+relationId);
	}
	
}
