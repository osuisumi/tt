package com.haoyu.tip.announcement.service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.announcement.entity.AnnouncementUser;

public interface IAnnouncementUserService {
	
	Response create(AnnouncementUser obj);

}
