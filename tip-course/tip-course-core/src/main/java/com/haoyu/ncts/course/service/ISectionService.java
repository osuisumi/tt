package com.haoyu.ncts.course.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.ncts.course.entity.Section;
import com.haoyu.sip.core.service.Response;

public interface ISectionService {

	/**
	 * 获取章节列表
	 * @param section 可传参数如:course.id, isHidden, parentSection.id等
	 * @param getChilds 是否获取子章节. 如为false, 只获取课程下或章节下的第一级章节列表; 如为true, 则获取课程下或章节下的所有章节, 并封装到childSections中
	 * @return
	 */
	List<Section> listSection(Map<String, Object> params, boolean getChilds, PageBounds pageBounds);
	
	/**
	 * 创建章节
	 * @param section
	 * @return
	 */
	Response createSection(Section section);
	
	/**
	 * 编辑章节
	 * @param section
	 * @return
	 */
	Response updateSection(Section section);
	
	/**
	 * 逻辑删除章节
	 * @param section
	 * @return
	 */
	Response deleteSectionByLogic(Section section);

	Section getSection(String id);

	Response updateSectionConfig(Section section, String dateTime);
	
	List<Section> listSectionByCourseId(String courseId);
	
	List<Section> listSection(Map<String, Object> param , PageBounds pageBounds);
}
