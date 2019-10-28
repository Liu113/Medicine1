package com.medicine.bean;

import java.util.Date;

public class Sales<T> {
	private Integer id;
	private Date saleTime;
	private Double salePrice;
	private Integer saleManId;
	private String saleMan;
	private String remark;
	private String medicineName;
	private T t;
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getSaleManId() {
		return saleManId;
	}
	public void setSaleManId(Integer saleManId) {
		this.saleManId = saleManId;
	}
	public String getSaleMan() {
		return saleMan;
	}
	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	@Override
	public String toString() {
		return "Sales [id=" + id + ", saleTime=" + saleTime + ", salePrice="
				+ salePrice + ", saleManId=" + saleManId + ", saleMan="
				+ saleMan + ", remark=" + remark + ", t=" + t + "]";
	}
	
}
