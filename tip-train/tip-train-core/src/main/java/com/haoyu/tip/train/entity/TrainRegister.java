/**
 * 
 */
package com.haoyu.tip.train.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

/**
 * @author lianghuahuang 培训报名
 */
public class TrainRegister extends BaseEntity {

	private static final long serialVersionUID = 4109078820456201901L;

	private String id;

	private Train train;

	/**
	 * 报名的用户
	 */
	private User user;

	/**
	 * 报名状态：与报名审核有关
	 */
	private String state;

	/**
	 * 班级id
	 */
	private String classId;
	
	private String result;
	/**
	 * 选课状态
	 * 
	 */
	private String courseRegistState;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		TrainRegister ot = (TrainRegister) other;
		if (this.id == null || ot.getId() == null) {
			if (this.user == null || this.user.getId() == null || this.train == null || this.train.getId() == null) {
				return false;
			}
			if (ot.getUser() == null || ot.getUser().getId() == null || ot.getTrain() == null || ot.getTrain().getId() == null) {
				return false;
			}
			if (this.user.getId().equals(ot.getUser().getId()) && this.train.getId().equals(ot.getTrain().getId())) {
				return true;
			}
			return false;
		}
		return this.id.equals(ot.getId());
	}

	public String getCourseRegistState() {
		return courseRegistState;
	}

	public void setCourseRegistState(String courseRegistState) {
		this.courseRegistState = courseRegistState;
	}

	
	
	

}
