package com.haoyu.ncts.course.entity;

import java.io.Serializable;

public class CourseStat implements Serializable{

	private static final long serialVersionUID = 1179890947054898367L;
	
	// 课程活动数量统计
	private int activityVideoNum;
	private int activityDiscussionNum;
	private int activityTestNum;
	private int activityAssignmentNum;
	private int activityHtmlNum;
	private int activitySurveyNum;

	// 课程活动完成人数统计
	private int completeVideoNum;
	private int completeDiscussionNum;
	private int completeTestNum;
	private int completeAssignmentNum;
	private int completeHtmlNum;
	private int completeSurveyNum;

	// 课程内容统计
	private int faqQuestionNum;
	private int faqAnswerNum;
	private int noteNum;
	private int resourceNum;
	private int discussionNum;

	private Course course;
	private int registerNum;
	private int qualifiedNum;

	public int getFaqQuestionNum() {
		return faqQuestionNum;
	}

	public void setFaqQuestionNum(int faqQuestionNum) {
		this.faqQuestionNum = faqQuestionNum;
	}

	public int getFaqAnswerNum() {
		return faqAnswerNum;
	}

	public void setFaqAnswerNum(int faqAnswerNum) {
		this.faqAnswerNum = faqAnswerNum;
	}

	public int getNoteNum() {
		return noteNum;
	}

	public void setNoteNum(int noteNum) {
		this.noteNum = noteNum;
	}

	public int getResourceNum() {
		return resourceNum;
	}

	public void setResourceNum(int resourceNum) {
		this.resourceNum = resourceNum;
	}

	public int getDiscussionNum() {
		return discussionNum;
	}

	public void setDiscussionNum(int discussionNum) {
		this.discussionNum = discussionNum;
	}

	public int getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(int registerNum) {
		this.registerNum = registerNum;
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

	public int getQualifiedNum() {
		return qualifiedNum;
	}

	public void setQualifiedNum(int qualifiedNum) {
		this.qualifiedNum = qualifiedNum;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

}
