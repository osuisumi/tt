package com.haoyu.tip.creative.entity;

import java.text.ParseException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class CreativeRelation extends BaseEntity {

	private static final long serialVersionUID = -4232544603270176844L;
	
	private String id;
	
	private Creative creative;
	
	private Relation relation;
	
	private Integer browseNum;
	
	private Integer supportNum;
	
	private Integer participateNum;
	
	private Integer collectDays;
	
	private TimePeriod collectTimePeriod;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String collectStartTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String collectEndTime;	
	
	public CreativeRelation() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}

	public Integer getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(Integer supportNum) {
		this.supportNum = supportNum;
	}

	public Integer getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(Integer participateNum) {
		this.participateNum = participateNum;
	}

	public Integer getCollectDays() {
		return collectDays;
	}

	public void setCollectDays(Integer collectDays) {
		this.collectDays = collectDays;
	}

	public TimePeriod getCollectTimePeriod() {
		if (collectTimePeriod == null) {
			if (StringUtils.isNotEmpty(collectStartTime) || StringUtils.isNotEmpty(collectEndTime)) {
				collectTimePeriod = new TimePeriod();
				try {
					if (StringUtils.isNotEmpty(collectStartTime)) {
						String[] array = collectStartTime.split(" ");
						if (array.length == 1) {
							collectStartTime += " 00:00:00";
						}
						collectTimePeriod.setStartTime(DateUtils.parseDate(collectStartTime, "yyyy-MM-dd HH:mm:ss"));
					}
					if (StringUtils.isNotEmpty(collectEndTime)) {
						String[] array = collectEndTime.split(" ");
						if (array.length == 1) {
							collectEndTime += " 23:59:59";
						}
						collectTimePeriod.setEndTime(DateUtils.parseDate(collectEndTime, "yyyy-MM-dd HH:mm:ss"));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return collectTimePeriod;
	}

	public void setCollectTimePeriod(TimePeriod collectTimePeriod) {
		this.collectTimePeriod = collectTimePeriod;
	}

	public String getCollectStartTime() {
		return collectStartTime;
	}

	public void setCollectStartTime(String collectStartTime) {
		this.collectStartTime = collectStartTime;
	}

	public String getCollectEndTime() {
		return collectEndTime;
	}

	public void setCollectEndTime(String collectEndTime) {
		this.collectEndTime = collectEndTime;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	public static String getId(String creativeId,String relationId){
		return DigestUtils.md5Hex(creativeId+relationId);
	}

	public Creative getCreative() {
		return creative;
	}

	public void setCreative(Creative creative) {
		this.creative = creative;
	}
	
}
