package com.haoyu.tip.train.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class TrainRelation extends BaseEntity{
	private static final long serialVersionUID = 1L;

	private String id;

	private Train train;

	private Relation relation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainRelation other = (TrainRelation) obj;
		if (id == null || other.getId() == null) {
			if(this.relation == null || other.getRelation() == null){
				return false;
			}
			if(!this.relation.getId().equals(other.getRelation().getId())){
				return false;
			}
			if(!this.relation.getType().equals(other.getRelation().getType())){
				return false;
			}
			return true;
		}
		return this.id.equals(other.getId());
	}
	
	
	public static String getId(String trainId,String relationId,String relationType){
		return DigestUtils.md5Hex(trainId+ relationId + relationType); 
	}
	

}
