package com.haoyu.ncts.course.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.ncts.course.utils.CourseResultState;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.excel.annotations.ExcelEntity;
import com.haoyu.sip.excel.annotations.ExportField;

@ExcelEntity(sortHead = true, sheetName = "课程成绩统计", wrapText = true)
public class CourseResultStat extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	private Course course;

	private User user;

	private BigDecimal score;

	private String state;

	private String phone;
	
	private String paperworkNo;
	
	private String termId;
	
	// excel导出
	@ExportField(colName = "编码", colWidth = 3000, index = 0)
	private String ecode = "1";
	@ExportField(colName = "期次编码", colWidth = 9000, index = 1)
	private String etermId;
	@ExportField(colName = "学校名称", colWidth = 9000, index = 2)
	private String edeptName;
	@ExportField(colName = "教师编码", colWidth = 9000, index = 3)
	private String euserId;
	@ExportField(colName = "教师姓名", colWidth = 3000, index = 4)
	private String erealName;
	@ExportField(colName = "手机号码", colWidth = 6000, index = 5)
	private String ephone;
	@ExportField(colName = "课程编码", colWidth = 6000, index = 6)
	private String ecourseCode;
	@ExportField(colName = "课程名称", colWidth = 12000, index = 7)
	private String ecourseTitle;
	@ExportField(colName = "分数", colWidth = 3000, index = 8)
	private BigDecimal escore;
	@ExportField(colName = "结果", colWidth = 3000, index = 9)
	private String estate;
	@ExportField(colName = "学时", colWidth = 3000, index = 10)
	private BigDecimal estudyHours;
	@ExportField(colName = "课程类型", colWidth = 3000, index = 11)
	private String ecourseType;
	@ExportField(colName = "身份证号(导入时删除此列)", colWidth = 12000, index = 12)
	private String epaperworkNo;

	public void setExportField() {
		euserId = user.getId();
		erealName = user.getRealName();
		ephone = phone;
		ecourseCode = "GDES_"+course.getCode();
		ecourseTitle = course.getTitle();
		escore = score;
		if (StringUtils.isNotEmpty(state)) {
			if (CourseResultState.NOPASS.equals(state)) {
				estate = "未通过";
			}else if(CourseResultState.PASS.equals(state)) {
				estate = "通过";
			}
		}
		estudyHours = course.getStudyHours();
		epaperworkNo = paperworkNo;
		etermId = termId;
	}

	public String getPaperworkNo() {
		return paperworkNo;
	}

	public void setPaperworkNo(String paperworkNo) {
		this.paperworkNo = paperworkNo;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getEtermId() {
		return etermId;
	}

	public void setEtermId(String etermId) {
		this.etermId = etermId;
	}

	public String getEdeptName() {
		return edeptName;
	}

	public void setEdeptName(String edeptName) {
		this.edeptName = edeptName;
	}

	public String getEuserId() {
		return euserId;
	}

	public void setEuserId(String euserId) {
		this.euserId = euserId;
	}

	public String getErealName() {
		return erealName;
	}

	public void setErealName(String erealName) {
		this.erealName = erealName;
	}

	public String getEphone() {
		return ephone;
	}

	public void setEphone(String ephone) {
		this.ephone = ephone;
	}

	public String getEcourseCode() {
		return ecourseCode;
	}

	public void setEcourseCode(String ecourseCode) {
		this.ecourseCode = ecourseCode;
	}

	public String getEcourseTitle() {
		return ecourseTitle;
	}

	public void setEcourseTitle(String ecourseTitle) {
		this.ecourseTitle = ecourseTitle;
	}

	public BigDecimal getEscore() {
		return escore;
	}

	public void setEscore(BigDecimal escore) {
		this.escore = escore;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

	public BigDecimal getEstudyHours() {
		return estudyHours;
	}

	public void setEstudyHours(BigDecimal estudyHours) {
		this.estudyHours = estudyHours;
	}

	public String getEcourseType() {
		return ecourseType;
	}

	public void setEcourseType(String ecourseType) {
		this.ecourseType = ecourseType;
	}

	public String getEpaperworkNo() {
		return epaperworkNo;
	}

	public void setEpaperworkNo(String epaperworkNo) {
		this.epaperworkNo = epaperworkNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
