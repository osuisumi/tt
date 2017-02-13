package com.haoyu.tip.announcement.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;

public class AnnouncementUser extends BaseEntity{

	private static final long serialVersionUID = 7621349028369641404L;

	private Announcement announcement;
	
	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public static String getId(String announcementId, String userId){
		return DigestUtils.md5Hex(announcementId+userId);
	}
	
}
