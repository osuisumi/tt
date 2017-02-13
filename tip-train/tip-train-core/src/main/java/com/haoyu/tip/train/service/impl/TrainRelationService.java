package com.haoyu.tip.train.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.train.dao.ITrainRelationDao;
import com.haoyu.tip.train.entity.Train;
import com.haoyu.tip.train.entity.TrainRelation;
import com.haoyu.tip.train.service.ITrainRelationService;

@Service
public class TrainRelationService implements ITrainRelationService {

	@Resource
	private ITrainRelationDao trainRelationDao;

	@Override
	public Response createTrainRelation(TrainRelation trainRelation) {
		if (trainRelation == null || trainRelation.getTrain()==null || trainRelation.getRelation() == null) {
			return Response.failInstance().responseMsg("createTrainRelation fail!trainRelation or train or relation is null!");
		}
		if (StringUtils.isEmpty(trainRelation.getId())) {
			trainRelation.setId(TrainRelation.getId(trainRelation.getTrain().getId(), trainRelation.getRelation().getId(), trainRelation.getRelation().getType()));
		}
		int count = trainRelationDao.insertTrainRelation(trainRelation);
		return count > 0 ? Response.successInstance().responseData(trainRelation) : Response.failInstance().responseMsg("createTrainRelation fail!");
	}

	@Override
	public Response updateTrainRelation(TrainRelation trainRelation) {
		if (trainRelation == null || StringUtils.isEmpty(trainRelation.getId())) {
			return Response.failInstance().responseMsg("updateTrainRelation is fail!trainRelation is null or trainRelation's id is null");
		}
		int count = trainRelationDao.updateTrainRelation(trainRelation);
		return count > 0 ? Response.successInstance().responseData(trainRelation) : Response.failInstance().responseMsg("updateTrainRelation fail!");
	}

	@Override
	public Response deleteTrainRelation(TrainRelation trainRelation) {
		if (trainRelation == null || StringUtils.isEmpty(trainRelation.getId())) {
			return Response.failInstance().responseMsg("deleteTrainRelation is fail!trainRelation is null or trainRelation's id is null");
		}
		int count = trainRelationDao.deleteTrainRelationByLogic(trainRelation);
		return count > 0 ? Response.successInstance() : Response.failInstance().responseMsg("deleteTrainRelation fail!");
	}

	@Override
	public TrainRelation findTrainRelationById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return trainRelationDao.selectTrainRelationById(id);
	}

	@Override
	public List<TrainRelation> findTrainRelations(TrainRelation trainRelation) {
		return findTrainRelations(trainRelation, null);
	}

	@Override
	public List<TrainRelation> findTrainRelations(TrainRelation trainRelation, PageBounds pageBounds) {
		Map<String, Object> parameter = Maps.newHashMap();
		// todo 设置参数
		return trainRelationDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<TrainRelation> findTrainRelations(Map<String, Object> parameter) {
		return trainRelationDao.findAll(parameter);
	}

	@Override
	public List<TrainRelation> findTrainRelations(Map<String, Object> parameter, PageBounds pageBounds) {
		return trainRelationDao.findAll(parameter, pageBounds);
	}

	@Override
	public Response updateTrainRelations(String trainId, List<String> relationIds, String relationType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("trainId", trainId);
		param.put("relationType", relationType);
		List<TrainRelation> oldRelations = this.findTrainRelations(param);
		List<TrainRelation> prepareAdd = generateTrainRelations(trainId, relationIds, relationType);
		List<TrainRelation> prepareRemove = new ArrayList<TrainRelation>();
		for (TrainRelation tr : oldRelations) {
			if (!relationIds.contains(tr.getRelation().getId())) {
				prepareRemove.add(tr);
			}else{
				prepareAdd.remove(tr);
			}
		}
		Response response = Response.successInstance();
		if(!CollectionUtils.isEmpty(prepareAdd)){
			response = this.createTrainRelations(prepareAdd); 
		}
		if(response.isSuccess()){
			if(!CollectionUtils.isEmpty(prepareRemove)){
				response = this.deleteTrainRelations(prepareRemove);
			}
		}
		return response;
	}

	private List<TrainRelation> generateTrainRelations(String trainId, List<String> relationIds, String relationType) {
		List<TrainRelation> result = new ArrayList<TrainRelation>();
		if(!CollectionUtils.isEmpty(relationIds)){
			for(String relationId:relationIds){
				if(StringUtils.isNotEmpty(relationId)){
					TrainRelation trainRelation = new TrainRelation();
					Relation relation = new Relation();
					relation.setId(relationId);
					relation.setType(relationType);
					trainRelation.setRelation(relation);
					trainRelation.setId(TrainRelation.getId(trainId, relationId, relationType));
					trainRelation.setTrain(new Train(trainId));
					result.add(trainRelation);
				}
			}
		}
		return result;
	}

	@Override
	public Response createTrainRelations(List<TrainRelation> trainRelations) {
		Response response = Response.failInstance();
		for(TrainRelation tr:trainRelations){
			response = this.createTrainRelation(tr);
		}
		return response;
	}

	@Override
	public Response deleteTrainRelations(List<TrainRelation> trainRelations) {
		List<String> prepareRemoveIds = new ArrayList<String>();
		for(TrainRelation tr:trainRelations){
			if(StringUtils.isEmpty(tr.getId())){
				if(tr.getTrain() == null || tr.getRelation() == null){
					return Response.failInstance().responseMsg("train or relation is null!");
				}
				tr.setId(TrainRelation.getId(tr.getTrain().getId(), tr.getRelation().getId(), tr.getRelation().getType()));
			}
			prepareRemoveIds.add(tr.getId());
		}
		return trainRelationDao.batchDeleteByPhysics(prepareRemoveIds)>0?Response.successInstance():Response.failInstance();
	}

}
