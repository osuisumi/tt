package com.haoyu.tip.course.mobile.entity;

import java.io.Serializable;
/*
 * 课程学习进度
 */
import java.math.BigDecimal;

import com.haoyu.sip.core.entity.TimePeriod;

public class MCourseStudyProgress implements Serializable {

	private static final long serialVersionUID = -679989977921910355L;

	private BigDecimal score;

	private String state;

	private int activityVideoNum;
	private int activityDiscussionNum;
	private int activityTestNum;
	private int activityAssignmentNum;
	private int activityHtmlNum;
	private int activitySurveyNum;

	private int completeVideoNum;
	private int completeDiscussionNum;
	private int completeTestNum;
	private int completeAssignmentNum;
	private int completeHtmlNum;
	private int completeSurveyNum;

	private TimePeriod timePeriod;

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

	public int getActivityVideoNum() {
		return activityVideoNum;
	}

	public void setActivityVideoNum(int activityVideoNum) {
		this.activityVideoNum = activityVideoNum;
	}

	public int getActivityDiscussionNum() {
		return activityDiscussionNum;
	}

	public void setActivityDiscussionNum(int activityDiscussionNum) {
		this.activityDiscussionNum = activityDiscussionNum;
	}

	public int getActivityTestNum() {
		return activityTestNum;
	}

	public void setActivityTestNum(int activityTestNum) {
		this.activityTestNum = activityTestNum;
	}

	public int getActivityAssignmentNum() {
		return activityAssignmentNum;
	}

	public void setActivityAssignmentNum(int activityAssignmentNum) {
		this.activityAssignmentNum = activityAssignmentNum;
	}

	public int getActivityHtmlNum() {
		return activityHtmlNum;
	}

	public void setActivityHtmlNum(int activityHtmlNum) {
		this.activityHtmlNum = activityHtmlNum;
	}

	public int getActivitySurveyNum() {
		return activitySurveyNum;
	}

	public void setActivitySurveyNum(int activitySurveyNum) {
		this.activitySurveyNum = activitySurveyNum;
	}

	public int getCompleteVideoNum() {
		return completeVideoNum;
	}

	public void setCompleteVideoNum(int completeVideoNum) {
		this.completeVideoNum = completeVideoNum;
	}

	public int getCompleteDiscussionNum() {
		return completeDiscussionNum;
	}

	public void setCompleteDiscussionNum(int completeDiscussionNum) {
		this.completeDiscussionNum = completeDiscussionNum;
	}

	public int getCompleteTestNum() {
		return completeTestNum;
	}

	public void setCompleteTestNum(int completeTestNum) {
		this.completeTestNum = completeTestNum;
	}

	public int getCompleteAssignmentNum() {
		return completeAssignmentNum;
	}

	public void setCompleteAssignmentNum(int completeAssignmentNum) {
		this.completeAssignmentNum = completeAssignmentNum;
	}

	public int getCompleteHtmlNum() {
		return completeHtmlNum;
	}

	public void setCompleteHtmlNum(int completeHtmlNum) {
		this.completeHtmlNum = completeHtmlNum;
	}

	public int getCompleteSurveyNum() {
		return completeSurveyNum;
	}

	public void setCompleteSurveyNum(int completeSurveyNum) {
		this.completeSurveyNum = completeSurveyNum;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

}
