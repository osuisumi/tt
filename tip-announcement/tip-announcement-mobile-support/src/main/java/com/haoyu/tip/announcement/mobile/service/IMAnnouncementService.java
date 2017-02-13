package com.haoyu.tip.announcement.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.announcement.entity.Announcement;

public interface IMAnnouncementService {

	Response listAnnouncement(Announcement announcement, PageBounds pageBounds,boolean getgetContent);

	Response viewAnnouncement(Announcement announcement);

	Response createAnnouncement(Announcement announcement);

	Response updateAnnouncement(Announcement announcement);

}
