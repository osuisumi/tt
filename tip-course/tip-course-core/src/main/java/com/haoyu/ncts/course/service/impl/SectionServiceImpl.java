package com.haoyu.ncts.course.service.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.dao.ISectionDao;
import com.haoyu.ncts.course.entity.Section;
import com.haoyu.ncts.course.service.ISectionService;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Collections3;

@Service
public class SectionServiceImpl implements ISectionService{
	
	@Resource
	private ISectionDao sectionDao;

	@Override
	@Cacheable(value="listSection",condition="#params[courseId] != null and #pageBounds.page == 1 and #getChilds == true", key="#params[courseId]")
	public List<Section> listSection(Map<String, Object> params, boolean getChilds, PageBounds pageBounds) {
		List<Section> parentList = Lists.newArrayList();
		if (!getChilds) {
			if (!params.containsKey("parentId")) {
				params.put("parentId", "");
			}
			parentList = sectionDao.select(params, pageBounds);
		}else{
			List<Section> sections = sectionDao.select(params, pageBounds);
			if (Collections3.isNotEmpty(sections)) {
				Map<String, Section> parentMap = Maps.newLinkedHashMap();
				for (Section sec : sections) {
					if ((sec.getParentSection() == null || StringUtils.isEmpty(sec.getParentSection().getId())) && !parentMap.containsKey(sec.getId())) {
						parentMap.put(sec.getId(), sec);
						parentList.add(sec);
					}
				}
				for (Section sec : sections) {
					if (sec.getParentSection() != null && parentMap.containsKey(sec.getParentSection().getId())) {
						parentMap.get(sec.getParentSection().getId()).getChildSections().add(sec);
					}
				}
			}
		}
		return parentList;
	}

	@Override
	@CacheEvict(value="listSection", key="#section.course.id")
	public Response createSection(Section section) {
		if (StringUtils.isEmpty(section.getId())) {
			if (section.getParentSection() != null && StringUtils.isNotEmpty(section.getParentSection().getId())) {
				section.setId(Section.generateSCId());
			}else{
				section.setId(Section.generateId());
			}
		}
		section.setDefaultValue();
		int count = sectionDao.insert(section);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	@CacheEvict(value="listSection", allEntries=true)
	public Response updateSection(Section section) {
		section.setUpdatedby(ThreadContext.getUser());
		section.setUpdateTime(System.currentTimeMillis());
		int count = sectionDao.update(section);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	@CacheEvict(value="listSection", allEntries=true)
	public Response deleteSectionByLogic(Section section) {
		String[] array = section.getId().split(",");
		List<String> ids = Arrays.asList(array);
		section.setUpdatedby(ThreadContext.getUser());
		section.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", section);
		int count = sectionDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Section getSection(String id) {
		return sectionDao.selectByPrimaryKey(id);
	}

	@Override
	public Response updateSectionConfig(Section section, String dateTime) {
		section.setTimePeriod(new TimePeriod());
		if (StringUtils.isNotEmpty(dateTime)) {
			String[] fields = dateTime.split(",");
			String dateString = fields[0];
			String hour = fields[1];
			String minute = fields[2];
			try {
				Date date = DateUtils.parseDate(dateString, "yyyy/MM/dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.set(Calendar.HOUR, Integer.parseInt(hour));
				calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
				section.getTimePeriod().setStartTime(calendar.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return this.updateSection(section);
	}

	@Override
	public List<Section> listSectionByCourseId(String courseId) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("courseId", courseId);
		List<Section> sections = sectionDao.select(param, null);
		Collections.sort(sections, new Comparator<Section>() {
			@Override
			public int compare(Section o1, Section o2) {
				if (o1.getSortNo() == null && o2.getSortNo() == null || o1.getSortNo().compareTo(o2.getSortNo()) == 0) {
					return (int) (o1.getCreateTime() - o2.getCreateTime());
				}else{
					if (o1.getSortNo() == null) {
						return -1;
					}else if(o2.getSortNo() == null){
						return 1;
					}else{
						return o1.getSortNo().compareTo(o2.getSortNo());
					}
				}
			}
		});
		return sections;
	}

	@Override
	public List<Section> listSection(Map<String, Object> param, PageBounds pageBounds) {
		return sectionDao.select(param,pageBounds);
	}

}
