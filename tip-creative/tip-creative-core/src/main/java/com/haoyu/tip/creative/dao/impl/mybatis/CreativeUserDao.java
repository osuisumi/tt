package com.haoyu.tip.creative.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.creative.dao.ICreativeUserDao;
import com.haoyu.tip.creative.entity.CreativeUser;

@Repository
public class CreativeUserDao extends MybatisDao implements ICreativeUserDao {

	@Override
	public CreativeUser selectCreativeUserById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertCreativeUser(CreativeUser creativeUser) {
		creativeUser.setDefaultValue();
		return super.insert(creativeUser);
	}

	@Override
	public int updateCreativeUser(CreativeUser creativeUser) {
		creativeUser.setUpdateValue();
		return super.update(creativeUser);
	}

	@Override
	public int deleteCreativeUserByLogic(CreativeUser creativeUser) {
		creativeUser.setUpdateValue();
		return super.deleteByLogic(creativeUser);
	}

	@Override
	public int deleteCreativeUserByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<CreativeUser> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<CreativeUser> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	@Override
	public Map<String, CreativeUser> findAllForMap(Map<String, Object> parameter) {
		return super.selectMap("selectByParameter", parameter, "creative.id");
	}

	
}
