package com.haoyu.tip.course.mobile.entity;

import java.io.Serializable;

import com.haoyu.ncts.course.entity.Course;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.user.mobile.entity.MUser;

public class MCourseRegister implements Serializable{
	
	private static final long serialVersionUID = 3990232141342295010L;
	
	private String id;
	
	private MCourse mCourse;
	
	private double score;
	
	private String state;
	
	private MUser mUser;

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public MCourse getmCourse() {
		return mCourse;
	}

	public void setmCourse(MCourse mCourse) {
		this.mCourse = mCourse;
	}
	
	public void setCourse(Course course) {
		this.mCourse = new MCourse();
		BeanUtils.copyProperties(course, mCourse);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MUser getmUser() {
		return mUser;
	}

	public void setmUser(MUser mUser) {
		this.mUser = mUser;
	}
	
	
	

}
