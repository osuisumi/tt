package com.haoyu.tip.livestudio.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.livestudio.dao.ILiveStudioDao;
import com.haoyu.tip.livestudio.entity.LiveStudio;
import com.haoyu.tip.livestudio.entity.LiveStudioRelation;
import com.haoyu.tip.livestudio.service.ILiveStudioRelationService;
import com.haoyu.tip.livestudio.service.ILiveStudioService;

@Service("liveStudioService")
public class LiveStudioServiceImpl implements ILiveStudioService {
	@Resource
	private ILiveStudioDao liveStudioDao;
	@Resource
	private ILiveStudioRelationService liveStudioRelationService;

	@Override
	public Response create(LiveStudio liveStudio) {
		liveStudio.setId(Identities.uuid2());
		if(liveStudio.getLiveStudioRelations()!=null&& liveStudio.getLiveStudioRelations().size()>0){
			for(LiveStudioRelation lr:liveStudio.getLiveStudioRelations()){
				lr.setId(DigestUtils.md5Hex(liveStudio.getId()+lr.getRelation().getId()));
				lr.setLiveStudioId(liveStudio.getId());
				liveStudioRelationService.create(lr);
			}
		}
		return BaseServiceUtils.create(liveStudio, (MybatisDao)liveStudioDao);
	}

	@Override
	public Response update(LiveStudio liveStudio) {
		return BaseServiceUtils.update(liveStudio, (MybatisDao)liveStudioDao);
	}

	@Override
	public Response deleteByPhysics(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteByLogic(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)liveStudioDao);
	}

	@Override
	public LiveStudio get(String id) {
		return (LiveStudio) BaseServiceUtils.get(id,(MybatisDao)liveStudioDao);
	}

	@Override
	public List<LiveStudio> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)liveStudioDao).selectList("select",searchParam.getParamMap(), pageBounds);
	}

	

}
