package com.haoyu.ncts.course.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.ncts.course.utils.CourseType;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.faq.entity.FaqQuestion;

public class Course extends BaseEntity {

	private static final long serialVersionUID = 7490039684525808321L;

	private String id;

	private String title;

	private String type;

	private String organization;

	private String code;

	private String termNo;

	private String description;

	private String summary;

	private String content;

	private String image;

	private BigDecimal studyHours;

	private String subject;

	private String stage;
	
	private BigDecimal hourLength;
	
	private String state;
	
	private String resultSettings;
	
	private TimePeriod timePeriod;
	
	private String sourceId;
	
	private String isTemplate;
	
	private String courseCategory;
	
	//以下为非数据库字段
	private List<Section> sections = Lists.newArrayList();
	
	private String year;
	
	private String term;
	
	private List<CourseAuthorize> courseAuthorizes = Lists.newArrayList();
	
	private List<FaqQuestion> faqQuestions = Lists.newArrayList();
	
	private Map<String, Object> resultSettingsMap = Maps.newHashMap();
	//课程统计信息
	private CourseStat courseStat;
	
	private CourseRelation courseRelation;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:dd")
	private String startTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:dd")
	private String endTime;
	
	public CourseRelation getCourseRelation() {
		return courseRelation;
	}

	public void setCourseRelation(CourseRelation courseRelation) {
		this.courseRelation = courseRelation;
	}

	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static String generateId(){
		return "c_"+Identities.uuid2();
	}
	
	public Course(){
		
	}
	
	public Course(String id){
		super();
		setId(id);
	}
	
	public TimePeriod getTimePeriod() {
		if (timePeriod == null) {
			if (StringUtils.isNotEmpty(startTime) || StringUtils.isNotEmpty(endTime)) {
				timePeriod = new TimePeriod();
				try {
					if (StringUtils.isNotEmpty(startTime)) {
						String[] array = startTime.split(" ");
						if (array.length == 1) {
							startTime += " 00:00:00";
						}
						timePeriod.setStartTime(DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss"));
					}
					if (StringUtils.isNotEmpty(endTime)) {
						String[] array = endTime.split(" ");
						if (array.length == 1) {
							endTime += " 23:59:59";
						}
						timePeriod.setEndTime(DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss"));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getResultSettings() {
		if (StringUtils.isNotEmpty(resultSettings)) {
			return resultSettings;
		}
		if (resultSettingsMap != null && !resultSettingsMap.isEmpty()) {
			resultSettings = new JsonMapper().toJson(resultSettingsMap);
		}
		return resultSettings;
	}

	public void setResultSettings(String resultSettings) {
		this.resultSettings = resultSettings;
	}
	
	public Map<String, Object> getResultSettingsMap() {
		if (!resultSettingsMap.isEmpty()) {
			return resultSettingsMap;
		}
		if (StringUtils.isNotEmpty(resultSettings)) {
			resultSettingsMap = new JsonMapper().fromJson(resultSettings, HashMap.class);
		}
		return resultSettingsMap;
	}

	public void setResultSettingsMap(Map<String, Object> resultSettingsMap) {
		this.resultSettingsMap = resultSettingsMap;
	}

	public List<FaqQuestion> getFaqQuestions() {
		return faqQuestions;
	}

	public void setFaqQuestions(List<FaqQuestion> faqQuestions) {
		this.faqQuestions = faqQuestions;
	}

	public List<CourseAuthorize> getCourseAuthorizes() {
		return courseAuthorizes;
	}

	public void setCourseAuthorizes(List<CourseAuthorize> courseAuthorizes) {
		this.courseAuthorizes = courseAuthorizes;
	}

	public String getTermNo() {
		if (StringUtils.isNotEmpty(year) && StringUtils.isNotEmpty(term)) {
			setTermNo(year + "_" + term);
		}
		return termNo;
	}

	public void setTermNo(String termNo) {
		if (StringUtils.isNotEmpty(termNo)) {
			String[] array = termNo.split("_");
			setYear(array[0]);
			setTerm(array[1]);
		}
		this.termNo = termNo;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
		this.type = type;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getStudyHours() {
		return studyHours;
	}

	public void setStudyHours(BigDecimal studyHours) {
		this.studyHours = studyHours;
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

	public BigDecimal getHourLength() {
		return hourLength;
	}

	public void setHourLength(BigDecimal hourLength) {
		this.hourLength = hourLength;
	}
	
	public Object getContentObject(){
		if(CourseType.LEAD.equals(this.type) || CourseType.SELF.equals(this.type)){
			List<Section> sections = Lists.newArrayList();
			if(StringUtils.isNotEmpty(this.content)){
				JsonMapper jsonMapper = new JsonMapper();
				sections = jsonMapper.fromJson(this.content, jsonMapper.contructCollectionType(ArrayList.class, Section.class));
			}
			return sections;
		}else if(CourseType.MIC.equals(this.type)){
			return this.content;
		}else{
			return "";
		}
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public CourseStat getCourseStat() {
		return courseStat;
	}

	public void setCourseStat(CourseStat courseStat) {
		this.courseStat = courseStat;
	}

	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}


	
	
	
	
}
