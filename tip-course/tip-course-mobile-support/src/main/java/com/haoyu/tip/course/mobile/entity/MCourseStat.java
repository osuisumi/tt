package com.haoyu.tip.course.mobile.entity;

public class MCourseStat {
	
	private MCourse mCourse;
	
	// 课程活动数量统计
	private int activityAssignmentNum;
	
	// 课程内容统计
	private int faqQuestionNum;
	private int faqAnswerNum;
	private int noteNum;
	private int resourceNum;
	private int discussionNum;
	
	private int registerNum;
	
	
	public int getActivityAssignmentNum() {
		return activityAssignmentNum;
	}
	public void setActivityAssignmentNum(int activityAssignmentNum) {
		this.activityAssignmentNum = activityAssignmentNum;
	}
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
	public MCourse getmCourse() {
		return mCourse;
	}
	public void setmCourse(MCourse mCourse) {
		this.mCourse = mCourse;
	}
	public int getRegisterNum() {
		return registerNum;
	}
	public void setRegisterNum(int registerNum) {
		this.registerNum = registerNum;
	}
	
	
	
	
	

}
