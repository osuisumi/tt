package com.haoyu.tip.resource.entity;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class ResourceRelation extends BaseEntity {

	private static final long serialVersionUID = -4437656137125270000L;

	private Resources resource;

	private Relation relation;

	private int browseNum;

	private int downloadNum;

	private BigDecimal fileNum;

	private int replyNum;

	private int collectNum;

	private int supportNum;

	private int voteNum;

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public BigDecimal getFileNum() {
		return fileNum;
	}

	public void setFileNum(BigDecimal fileNum) {
		this.fileNum = fileNum;
	}

	public Resources getResource() {
		return resource;
	}

	public void setResource(Resources resource) {
		this.resource = resource;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}

	public int getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}

	public static String getId(String resourceId, String relationId) {
		return DigestUtils.md5Hex(resourceId + relationId);
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public int getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}

}
