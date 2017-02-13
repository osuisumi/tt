/**
 * 
 */
package com.haoyu.tip.train.entity;

import java.math.BigDecimal;
import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.tip.project.entity.Project;

/**
 * @author lianghuahuang 培训信息
 */
public class Train extends BaseEntity {

	private static final long serialVersionUID = -1315643647861134884L;

	private String id;

	private Project project;

	private String name;

	private String description;

	/**
	 * 培训状态
	 */
	private String state;

	/**
	 * 培训类型
	 */
	private String type;

	/**
	 * 学时类型
	 */
	private String studyHoursType;

	/**
	 * 培训时间
	 */
	private TimePeriod trainingTime;

	/**
	 * 报名时间
	 */
	private TimePeriod registerTime;

	/**
	 * 选课时间
	 */
	private TimePeriod electivesTime;

	/**
	 * 报名数
	 */
	private int registerNum;

	/**
	 * 选课数
	 */
	private int electivesNum;

	/**
	 * 培训对象
	 */
	private String target;

	/**
	 * 文件
	 */
	private List<FileInfo> fileInfos;

	/**
	 * 培训学时
	 */
	private BigDecimal studyHours;

	/**
	 * 培训费用
	 */
	private BigDecimal price;

	/**
	 * 收费方式
	 */
	private String chargeType;

	public Train() {
	}

	public Train(String id) {
		super();
		this.id = id;
	}

	public String getStudyHoursType() {
		return studyHoursType;
	}

	public void setStudyHoursType(String studyHoursType) {
		this.studyHoursType = studyHoursType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TimePeriod getTrainingTime() {
		return trainingTime;
	}

	public void setTrainingTime(TimePeriod trainingTime) {
		this.trainingTime = trainingTime;
	}

	public TimePeriod getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(TimePeriod registerTime) {
		this.registerTime = registerTime;
	}

	public TimePeriod getElectivesTime() {
		return electivesTime;
	}

	public void setElectivesTime(TimePeriod electivesTime) {
		this.electivesTime = electivesTime;
	}

	public int getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(int registerNum) {
		this.registerNum = registerNum;
	}

	public int getElectivesNum() {
		return electivesNum;
	}

	public void setElectivesNum(int electivesNum) {
		this.electivesNum = electivesNum;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public BigDecimal getStudyHours() {
		return studyHours;
	}

	public void setStudyHours(BigDecimal studyHours) {
		this.studyHours = studyHours;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

}
