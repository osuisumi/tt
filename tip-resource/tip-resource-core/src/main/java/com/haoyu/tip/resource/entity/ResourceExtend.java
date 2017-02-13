package com.haoyu.tip.resource.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.haoyu.sip.file.entity.FileInfo;

public class ResourceExtend implements Serializable {

	private static final long serialVersionUID = -6653315550807365164L;

	private String resourceId;

	private float evaluateResult;

	private String isOriginal;

	private String subject;

	private String stage;

	private String grade;

	private String tbVersion;

	private String post;

	private String type;

	private String chapter;

	private String section;

	private String previewUrl;

	private int version;

	private String prize;

	private String isHidden;

	private BigDecimal topDays;

	private Date topDate;

	private String author;// 作者

	private String source;// 来源

	private Date issueDate;// 发行时间

	private String coverUrl;// 封面URL

	private FileInfo coverFileInfo;

	private String bIndex;// 中图法分类号

	private BigDecimal price;// 价格
	
	private String topic;//主题

	public Date getTopDate() {
		return topDate;
	}

	public void setTopDate(Date topDate) {
		this.topDate = topDate;
	}

	public BigDecimal getTopDays() {
		return topDays;
	}

	public void setTopDays(BigDecimal topDays) {
		this.topDays = topDays;
	}

	public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public float getEvaluateResult() {
		return evaluateResult;
	}

	public void setEvaluateResult(float evaluateResult) {
		this.evaluateResult = evaluateResult;
	}

	public String getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(String isOriginal) {
		this.isOriginal = isOriginal;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTbVersion() {
		return tbVersion;
	}

	public void setTbVersion(String tbVersion) {
		this.tbVersion = tbVersion;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public FileInfo getCoverFileInfo() {
		return coverFileInfo;
	}

	public void setCoverFileInfo(FileInfo coverFileInfo) {
		this.coverFileInfo = coverFileInfo;
	}

	public String getbIndex() {
		return bIndex;
	}

	public void setbIndex(String bIndex) {
		this.bIndex = bIndex;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
