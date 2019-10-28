package com.medicine.bean;

import java.util.Date;

public class In<T> {
	
	private Integer id;
	private Integer inDepotId;
	private Date inTime;
	private Integer tabMan;
	private String tabManName;
	private Integer status;
	private String remark;
	private String medicineName;
	private Integer Mnum;
	private String spare1;
	private String spare2;
	private T t;
	
	public Integer getMnum() {
		return Mnum;
	}
	public void setMnum(Integer mnum) {
		Mnum = mnum;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getTabManName() {
		return tabManName;
	}
	public void setTabManName(String tabManName) {
		this.tabManName = tabManName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInDepotId() {
		return inDepotId;
	}
	public void setInDepotId(Integer inDepotId) {
		this.inDepotId = inDepotId;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Integer getTabMan() {
		return tabMan;
	}
	public void setTabMan(Integer tabMan) {
		this.tabMan = tabMan;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSpare1() {
		return spare1;
	}
	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}
	public String getSpare2() {
		return spare2;
	}
	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	@Override
	public String toString() {
		return "In [id=" + id + ", inDepotId=" + inDepotId + ", inTime=" + inTime + ", tabMan=" + tabMan
				+ ", tabManName=" + tabManName + ", status=" + status + ", remark=" + remark + ", medicineName="
				+ medicineName + ", spare1=" + spare1 + ", spare2=" + spare2 + ", t=" + t + "]";
	}
	

}
