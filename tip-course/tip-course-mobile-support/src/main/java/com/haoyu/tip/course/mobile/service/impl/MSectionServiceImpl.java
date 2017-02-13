package com.haoyu.tip.course.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.entity.Section;
import com.haoyu.ncts.course.service.ISectionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.tip.course.mobile.entity.MSection;
import com.haoyu.tip.course.mobile.service.IMSectionService;

@Repository
public class MSectionServiceImpl implements IMSectionService{

	@Resource
	private ISectionService sectionService;
	
	@Override
	public Response listSection(Section section) {
		List<MSection> mSections = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		
		if (section.getCourse() != null && StringUtils.isNotEmpty(section.getCourse().getId())) {
			param.put("courseId",section.getCourse().getId());
		}
		
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(Integer.MAX_VALUE);
		pageBounds.setOrders(Order.formString("SORT_NO,CREATE_TIME"));

		List<Section> sections = sectionService.listSection(param, true, pageBounds);
		
		if (Collections3.isNotEmpty(sections)) {
			for (Section ps : sections) {			
				MSection mParentaSection = new MSection();
				BeanUtils.copyProperties(ps, mParentaSection);
				if (Collections3.isNotEmpty(ps.getChildSections())) {
					List<MSection> mChildSections = Lists.newArrayList();
					mChildSections = BeanUtils.getCopyList(ps.getChildSections(),MSection.class);
					mParentaSection.setChildMSections(mChildSections);
				}
				mSections.add(mParentaSection);
			}
			
		}
		
		return Response.successInstance().responseData(mSections);
	}

}
