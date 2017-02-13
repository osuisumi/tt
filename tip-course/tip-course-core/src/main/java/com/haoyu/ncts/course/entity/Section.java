package com.haoyu.ncts.course.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.google.common.collect.Lists;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.utils.Identities;

public class Section extends BaseEntity{
	
	private static final long serialVersionUID = 2983907206876371423L;

	private String id;
	
	private String title;
	
	private Course course;
	
	private Section parentSection;
	
	private TimePeriod timePeriod;
	
	private String isHidden;
	
	private BigDecimal sortNo;
	
	//以下非数据库字段
	private List<Section> childSections = Lists.newArrayList();
	
	private List<Activity> activities = Lists.newArrayList();
	
	private String startTime;
	
	private String endTime;
	
	public static String generateId(){
		return "s_"+Identities.uuid2();
	}
	
	public static String generateSCId(){
		return "sc_"+Identities.uuid2();
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TimePeriod getTimePeriod() {
		if (timePeriod == null) {
			if (startTime != null || endTime != null) {
				timePeriod = new TimePeriod();
				try {
					if (StringUtils.isNotEmpty(startTime)) {
						timePeriod.setStartTime(DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss"));
					}
					if (StringUtils.isNotEmpty(endTime)) {
						timePeriod.setEndTime(DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss"));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public Section getParentSection() {
		return parentSection;
	}

	public void setParentSection(Section parentSection) {
		this.parentSection = parentSection;
	}

	public List<Section> getChildSections() {
		return childSections;
	}

	public void setChildSections(List<Section> childSections) {
		this.childSections = childSections;
	}

	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
		this.setSortNo(BigDecimal.valueOf(9999));
	}
}
