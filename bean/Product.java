package com.training.bean;

import java.io.Serializable;

public class Product implements Serializable{

	private String upc;
	private String productDesc;
	private String artistId;
	private String orgId;
	private String configId;
	private String releaseDate;

	public Product() {
	}

	public Product(String upc, String productDesc, String artistId, String orgId, String configId, String releaseDate) {
		super();
		this.upc = upc;
		this.productDesc = productDesc;
		this.artistId = artistId;
		this.orgId = orgId;
		this.configId = configId;
		this.releaseDate = releaseDate;
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

	@Override
	public String toString() {
		return "Product [upc=" + upc + ", productDesc=" + productDesc + ", artistId=" + artistId + ", orgId=" + orgId
				+ ", configId=" + configId + ", releaseDate=" + releaseDate + "]";
	}

	
	



}