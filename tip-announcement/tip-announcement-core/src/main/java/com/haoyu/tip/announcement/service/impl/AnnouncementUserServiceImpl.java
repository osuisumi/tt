package com.haoyu.tip.announcement.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.announcement.dao.IAnnouncementUserDao;
import com.haoyu.tip.announcement.entity.AnnouncementUser;
import com.haoyu.tip.announcement.service.IAnnouncementUserService;

@Service
public class AnnouncementUserServiceImpl implements IAnnouncementUserService {
	
	@Resource
	private IAnnouncementUserDao announcementUserDao;
	
	@Override
	public Response create(AnnouncementUser obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)announcementUserDao);
	}
}
