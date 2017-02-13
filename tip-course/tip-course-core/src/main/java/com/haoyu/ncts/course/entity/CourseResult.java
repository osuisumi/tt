package com.haoyu.ncts.course.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.mapper.JsonMapper;

public class CourseResult extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private Course course;
	
	private Relation relation;
	
	private User user;
	
	private BigDecimal score;
	
	private String state;
	
	private String detail;
	
	//以下为非数据库字段
	private Map<String, Object> detailMap = Maps.newHashMap();
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Map<String, Object> getDetailMap() {
		if (!detailMap.isEmpty()) {
			return detailMap;
		}
		if (StringUtils.isNotEmpty(detail)) {
			detailMap = new JsonMapper().fromJson(detail, HashMap.class);
		}
		return detailMap;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public void setDetailMap(Map<String, Object> detailMap) {
		this.detailMap = detailMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static String getId(String courseId, String userId) {
		return DigestUtils.md5Hex(courseId+userId);
	}

}
