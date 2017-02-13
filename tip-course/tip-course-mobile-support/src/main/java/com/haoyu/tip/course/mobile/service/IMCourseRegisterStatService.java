package com.haoyu.tip.course.mobile.service;

import org.springframework.web.bind.annotation.PathVariable;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;

public interface IMCourseRegisterStatService {
	
	public Response listCourseRegisterStat(@PathVariable String courseId,String courseResultState,PageBounds pageBounds);

}
