/**
 * 
 */
package com.haoyu.tip.project.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.tip.project.dao.IProjectDao;
import com.haoyu.tip.project.entity.Project;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class ProjectDao extends MybatisDao implements IProjectDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.IProjectDao#selectProjectById(java.lang.String)
	 */
	@Override
	public Project selectProjectById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.IProjectDao#insertProject(com.haoyu.sip.cms.siteInfo.entity.Project)
	 */
	@Override
	public int insertProject(Project siteInfo) {
		siteInfo.setDefaultValue();
		return super.insert(siteInfo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.IProjectDao#updateProject(com.haoyu.sip.cms.siteInfo.entity.Project)
	 */
	@Override
	public int updateProject(Project siteInfo) {
		siteInfo.setUpdateValue();
		return super.update(siteInfo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.IProjectDao#deleteProjectByLogic(com.haoyu.sip.cms.siteInfo.entity.Project)
	 */
	@Override
	public int deleteProjectByLogic(Project siteInfo) {
		siteInfo.setUpdateValue();
		return super.deleteByLogic(siteInfo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.IProjectDao#deleteProjectByPhysics(java.lang.String)
	 */
	@Override
	public int deleteProjectByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.IProjectDao#findAll(java.util.Map)
	 */
	@Override
	public List<Project> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.IProjectDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Project> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

	@Override
	public int deleteProjectsByLogic(List<String> ids) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("ids", ids);
		Project entity = new Project();
		entity.setUpdateValue();
		parameter.put("entity",entity);
		return super.update("deleteProjectByIds",parameter);
	}

}
