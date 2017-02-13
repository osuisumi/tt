package com.haoyu.tip.announcement.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.announcement.entity.Announcement;

public interface IAnnouncementService {

	Response create(Announcement obj);

	Response update(Announcement obj);

	Response delete(String id);

	Announcement get(String id);

	List<Announcement> list(SearchParam searchParam, PageBounds pageBounds);

	Response createAnnouncement(Announcement announcement);

	Announcement viewAnnouncement(Announcement announcement);

	Response updateAnnouncement(Announcement announcement);

	Response deleteAnnouncement(Announcement announcement);

	List<Announcement> list(Announcement announcement, PageBounds pageBounds,boolean getContent);

}
