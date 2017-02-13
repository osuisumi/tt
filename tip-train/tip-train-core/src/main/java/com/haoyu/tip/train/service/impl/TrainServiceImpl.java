package com.haoyu.tip.train.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;
import com.haoyu.tip.train.dao.ITrainDao;
import com.haoyu.tip.train.entity.Train;
import com.haoyu.tip.train.service.ITrainService;
import com.haoyu.tip.train.web.param.TrainParam;
@Service
public class TrainServiceImpl implements ITrainService{
	
	@Resource
	private ITrainDao trainDao;
	@Resource
	private IFileService fileService;

	@Override
	public Response createTrain(Train train) {
		if(train==null){
			return Response.failInstance().responseMsg("createTrain fail!train is null!");
		}
		if(StringUtils.isEmpty(train.getId())){
			train.setId(Identities.uuid2());
		}
		int count = trainDao.insertTrain(train);
		if(count>0&&!CollectionUtils.isEmpty(train.getFileInfos())){
			for(FileInfo fi:train.getFileInfos()){
				FileRelation fr = CollectionUtils.isEmpty(fi.getFileRelations())?null:fi.getFileRelations().get(0);
				String relationType = fr.getRelation() == null?"train_fileInfo":fr.getRelation().getType();
				fileService.createFile(fi,train.getId(),relationType);
			}
		}
		return count>0?Response.successInstance().responseData(train):Response.failInstance().responseMsg("createTrain fail!");
	}

	@Override
	public Response updateTrain(Train train) {
		if(train==null||StringUtils.isEmpty(train.getId())){
			return Response.failInstance().responseMsg("updateTrain is fail!train is null or train's id is null");
		}
		int count = trainDao.updateTrain(train);
		//todo update fileInfos
		return count>0?Response.successInstance().responseData(train):Response.failInstance().responseMsg("updateTrain fail!");
	}

	@Override
	public Response deleteTrain(Train train) {
		if(train==null||StringUtils.isEmpty(train.getId())){
			return Response.failInstance().responseMsg("deleteTrain is fail!train is null or train's id is null");
		}
		Map<String,Object> parameter = Maps.newHashMap();
		train.setUpdateValue();
		parameter.put("entity", train);
		parameter.put("ids", Arrays.asList(train.getId().split(",")));
		int count = trainDao.deleteTrainByLogic(parameter);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteTrain fail!");
	}

	@Override
	public Train findTrainById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return trainDao.selectTrainById(id);
	}

	@Override
	public List<Train> findTrains(TrainParam train) {
		return findTrains(train,null);
	}

	@Override
	public List<Train> findTrains(TrainParam train, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		train.setParam(parameter);
		return trainDao.findAll(parameter,pageBounds);
	}

	@Override
	public List<Train> findTrains(Map<String, Object> parameter) {
		return trainDao.findAll(parameter);
	}

	@Override
	public List<Train> findTrains(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return trainDao.findAll(parameter,pageBounds);
	}

	@Override
	public Map<String, Train> mapTrain(Map<String, Object> parameter) {
		return trainDao.selectForMap(parameter);
	}


}
