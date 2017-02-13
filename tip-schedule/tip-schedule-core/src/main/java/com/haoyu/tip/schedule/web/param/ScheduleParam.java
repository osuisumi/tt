package com.haoyu.tip.schedule.web.param;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.utils.TimeUtils;
import com.haoyu.tip.schedule.entity.Schedule;

public class ScheduleParam extends Schedule {
	private static final long serialVersionUID = 6421617091107837020L;

	// yyyy-MM 查询该月份 yyyy查询该年 yyyy-MM-dd查询该日
	private String dateParam;

	// 状态参数 ongoing:进行中 beforeStart:为开始 afterEnd:已结束
	private String stateParam;

	private Date minStartTime;

	private Date maxStartTime;

	private Date minEndTime;

	private Date maxEndTime;
	
	private String relationIds;

	private List<Schedule> schedules = new ArrayList<Schedule>();

	public String getDateParam() {
		return dateParam;
	}

	public void setDateParam(String dateParam) {
		this.dateParam = dateParam;
	}

	public String getStateParam() {
		return stateParam;
	}

	public void setStateParam(String stateParam) {
		this.stateParam = stateParam;
	}

	public Date getMinStartTime() {
		return minStartTime;
	}

	public void setMinStartTime(Date minStartTime) {
		this.minStartTime = minStartTime;
	}

	public Date getMaxStartTime() {
		return maxStartTime;
	}

	public void setMaxStartTime(Date maxStartTime) {
		this.maxStartTime = maxStartTime;
	}

	public Date getMinEndTime() {
		return minEndTime;
	}

	public void setMinEndTime(Date minEndTime) {
		this.minEndTime = minEndTime;
	}

	public Date getMaxEndTime() {
		return maxEndTime;
	}

	public void setMaxEndTime(Date maxEndTime) {
		this.maxEndTime = maxEndTime;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public void setParam(Map<String, Object> parameter) {
		boolean flg = true; // 手动设置minstartTime等参数
		// 设置按照 年或月或日 查询时的参数
		if (StringUtils.isNotEmpty(this.getDateParam())) {
			flg = false;
			int count = 0;
			for (int i = 0; i < dateParam.length(); i++) {
				if (dateParam.charAt(i) == '-') {
					count++;
				}
			}
			if (count == 0) {
				parameter.put("minStartTime", TimeUtils.getFirstDayOfYear(Integer.valueOf(dateParam)));
				parameter.put("maxStartTime", TimeUtils.getLastDayOfYear(Integer.valueOf(dateParam)));
			} else if (count == 1) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				try {
					parameter.put("minStartTime", TimeUtils.getFirstDayOfMonth(sdf.parse(dateParam)));
					parameter.put("maxStartTime", TimeUtils.getLastDayOfMonth(sdf.parse(dateParam)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				parameter.put("minStartTime", dateParam);
				parameter.put("maxStartTime", dateParam);
			}
		}
		// 设置按照 进行中 未开始 已结束 的参数
		if (StringUtils.isNotEmpty(this.getStateParam())) {
			flg = false;
			if (this.getStateParam().equals("ongoing")) {
				parameter.put("maxStartTime", new Date());
				parameter.put("minEndTime", new Date());
			} else if (this.getStateParam().equals("beforeStart")) {
				parameter.put("minStartTime", new Date());
			} else if (this.getStateParam().equals("afterEnd")) {
				parameter.put("maxEndTime", new Date());
			}
		}
		if (this.getCreator() != null) {
			parameter.put("creator", this.getCreator().getId());
		}
		if (StringUtils.isNotEmpty(this.getTitle())) {
			parameter.put("title", this.getTitle());
		}
		if (StringUtils.isNotEmpty(this.getState())) {
			parameter.put("state", this.getState());
		}
		if (StringUtils.isNotEmpty(this.getType())) {
			parameter.put("type", this.getType());
		}
		if(StringUtils.isNotEmpty(this.relationIds)){
			parameter.put("relationIds", Arrays.asList(relationIds.split(",")));
		}
		if (flg) {
			if (this.getMinStartTime() != null) {
				parameter.put("minStartTime", this.getMinStartTime());
			}
			if (this.getMaxStartTime() != null) {
				parameter.put("maxStartTime", this.getMaxStartTime());
			}
			if (this.getMinEndTime() != null) {
				parameter.put("minEndTime", this.getMinEndTime());
			}
			if (this.getMaxEndTime() != null) {
				parameter.put("maxEndTime", this.getMaxEndTime());
			}
		}
	}

	public String getRelationIds() {
		return relationIds;
	}

	public void setRelationIds(String relationIds) {
		this.relationIds = relationIds;
	}

	
	
	

}
