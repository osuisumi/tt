package com.haoyu.tip.announcement.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.announcement.entity.AnnouncementRelation;

public interface IAnnouncementRelationService {

	Response create(AnnouncementRelation obj);

	Response update(AnnouncementRelation obj);

	Response delete(String id);

	AnnouncementRelation get(String id);

	List<AnnouncementRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateBrowseNum(AnnouncementRelation announcementRelation);
}
