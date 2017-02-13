package com.haoyu.tip.course.mobile.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.file.utils.FileUtils;

public class MCourse implements Serializable{

	private static final long serialVersionUID = -7755111791511980955L;

	private String id;
	
	private String title;
	
	private String type;
	
	private BigDecimal studyHours;
	
	private String image;
	
	private String code;
	
	private String termNo;
	
	private TimePeriod timePeriod;
	
	private int registerNum;//参与人数
	
	private String intro;//简介，与源对象不同名避免默认复制，需要简介时手动赋值
	
	private List<MSection> mSections = Lists.newArrayList();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public List<MSection> getmSections() {
		return mSections;
	}

	public void setmSections(List<MSection> mSections) {
		this.mSections = mSections;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (StringUtils.isNotEmpty(type)) {
			this.type = PropertiesLoader.get("property.course.type."+type);
		}else{
			this.type = type;
		}
	}

	public BigDecimal getStudyHours() {
		return studyHours;
	}

	public void setStudyHours(BigDecimal studyHours) {
		this.studyHours = studyHours;
	}

	public String getImage() {
		String prefix = FileUtils.getHttpHost();
		if (image != null && !image.contains(prefix)) {
			image = prefix + image;
		}
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(int registerNum) {
		this.registerNum = registerNum;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}
