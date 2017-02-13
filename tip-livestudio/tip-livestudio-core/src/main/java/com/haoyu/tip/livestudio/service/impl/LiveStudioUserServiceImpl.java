package com.haoyu.tip.livestudio.service.impl;

import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.livestudio.dao.ILiveStudioDao;
import com.haoyu.tip.livestudio.dao.ILiveStudioUserDao;
import com.haoyu.tip.livestudio.entity.LiveStudioUser;
import com.haoyu.tip.livestudio.service.ILiveStudioUserService;

@Service("liveStudioUserService")
public class LiveStudioUserServiceImpl implements ILiveStudioUserService{
	@Resource
	private ILiveStudioUserDao liveStudioUserDao;
	
	@Resource
	private ILiveStudioDao liveStudioDao;
	
	@Override
	public Response signUp(LiveStudioUser liveStudioUser) {
		if(!StringUtils.isBlank(liveStudioUser.getUser().getId())){
			liveStudioUser.setId(DigestUtils.md5Hex(liveStudioUser.getLiveStudioId()+liveStudioUser.getUser().getId()));
		}
		Response response = BaseServiceUtils.create(liveStudioUser,(MybatisDao)liveStudioUserDao);
		if(response.isSuccess()){
			liveStudioDao.updateNowNum(liveStudioUser.getLiveStudioId());
		}
		return response;
	}

	@Override
	public Response cancenSignUp(String liveStudioId, String userId) {
		String id = DigestUtils.md5Hex(liveStudioId+userId);
		int count = ((MybatisDao)liveStudioUserDao).deleteByPhysics(id);
		if(count>0){
			liveStudioDao.updateNowNum(liveStudioId);
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

}
