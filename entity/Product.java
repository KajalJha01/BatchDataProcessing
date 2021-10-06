package com.training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.training.bean.FailedProduct;

@Entity
@Table(name="product")
public class Product{ 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uniqueNumber")
	private long uniqueNumber; //unique number

	@Column(name="upc",length=14)
	private String upc;
	@Column(name="productDesc",length=200)
	private String productDesc;
	@Column(name="artistId",length=10)
	private String artistId;
	@Column(name="orgId",length=8)
	private String orgId;
	@Column(name="configId",length=25)
	private String configId;
	@Column(name="releaseDate",length=8)
	private String releaseDate;



	public Product() {
		super();
	}
	public Product(long uniqueNumber, String upc, String productDesc, String artistId, String orgId, String configId,
			String releaseDate) {
		super();
		this.uniqueNumber = uniqueNumber;
		this.upc = upc;
		this.productDesc = productDesc;
		this.artistId = artistId;
		this.orgId = orgId;
		this.configId = configId;
		this.releaseDate = releaseDate;
	}
	//constructor without auto generated value
	public Product(String upc, String productDesc, String artistId, String orgId, String configId,
			String releaseDate) {
		super();
		this.upc = upc;
		this.productDesc = productDesc;
		this.artistId = artistId;
		this.orgId = orgId;
		this.configId = configId;
		this.releaseDate = releaseDate;
	}
	//constructor without auto generated value
	public Product(FailedProduct failedProduct) {
		super();
		this.upc = failedProduct.getUpc();
		this.productDesc = failedProduct.getProductDesc();
		this.artistId = failedProduct.getArtistId();
		this.orgId = failedProduct.getOrgId();
		this.configId = failedProduct.getConfigId();
		this.releaseDate = failedProduct.getReleaseDate();
	}

	public long getUniqueNumber() {
		return uniqueNumber;
	}
	public void setUniqueNumber(long uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
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
		return "Product [uniqueNumber=" + uniqueNumber + ", upc=" + upc + ", productDesc=" + productDesc + ", artistId=" + artistId
				+ ", orgId=" + orgId + ", configId=" + configId + ", releaseDate=" + releaseDate + "]";
	}





}
