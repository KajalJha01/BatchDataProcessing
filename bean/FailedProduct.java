package com.training.bean;

import java.io.Serializable;

public class FailedProduct implements Serializable{
	
	private String upc;
	private String productDesc;
	private String artistId;
	private String orgId;
	private String configId;
	private String releaseDate;
	private int messageId;
	
	public FailedProduct() {
		super();
	}
	public FailedProduct(String upc, String productDesc, String artistId, String orgId, String configId,
			String releaseDate, int messageId) {
		super();
		this.upc = upc;
		this.productDesc = productDesc;
		this.artistId = artistId;
		this.orgId = orgId;
		this.configId = configId;
		this.releaseDate = releaseDate;
		this.messageId = messageId;
	}
	// another constructor for setting product object to failedProduct parameters
	public FailedProduct(Product product, int messageId) {
		super();
		this.upc = product.getUpc();
		this.productDesc = product.getProductDesc();
		this.artistId = product.getArtistId();
		this.orgId = product.getOrgId();
		this.configId = product.getConfigId();
		this.releaseDate = product.getReleaseDate();
		this.messageId = messageId;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	@Override
	public String toString() {
		return "FailedProduct [upc=" + upc + ", productDesc=" + productDesc + ", artistId=" + artistId + ", orgId="
				+ orgId + ", configId=" + configId + ", releaseDate=" + releaseDate + ", messageId=" + messageId + "]";
	} 

	
}
