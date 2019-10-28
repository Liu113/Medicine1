package com.medicine.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Medicine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medicine", catalog = "medicine")
public class Medicine implements java.io.Serializable {

	// Fields

	private Integer mid;
	private Integer class_;
	private String mname;
	private String mdes;
	private String mimg;
	private Double inPrice;
	private Double outPrice;
	private Integer mstatus;
	private Integer mnum;
	private Integer isDelete;
	private Integer pid;
	private Date updateTime;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Medicine() {
	}

	/** full constructor */
	public Medicine(Integer class_, String mname, String mdes, String mimg,
			Double inPrice, Double outPrice, Integer mstatus, Integer mnum,
			Integer isDelete, Integer pid, Date updateTime, Date createTime) {
		this.class_ = class_;
		this.mname = mname;
		this.mdes = mdes;
		this.mimg = mimg;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.mstatus = mstatus;
		this.mnum = mnum;
		this.isDelete = isDelete;
		this.pid = pid;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mid", unique = true, nullable = false)
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "class")
	public Integer getClass_() {
		return this.class_;
	}

	public void setClass_(Integer class_) {
		this.class_ = class_;
	}

	@Column(name = "mname", length = 100)
	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Column(name = "mdes", length = 100)
	public String getMdes() {
		return this.mdes;
	}

	public void setMdes(String mdes) {
		this.mdes = mdes;
	}

	@Column(name = "mimg", length = 100)
	public String getMimg() {
		return this.mimg;
	}

	public void setMimg(String mimg) {
		this.mimg = mimg;
	}

	@Column(name = "InPrice", precision = 10)
	public Double getInPrice() {
		return this.inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	@Column(name = "OutPrice", precision = 10)
	public Double getOutPrice() {
		return this.outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	@Column(name = "mstatus")
	public Integer getMstatus() {
		return this.mstatus;
	}

	public void setMstatus(Integer mstatus) {
		this.mstatus = mstatus;
	}

	@Column(name = "mnum")
	public Integer getMnum() {
		return this.mnum;
	}

	public void setMnum(Integer mnum) {
		this.mnum = mnum;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updateTime", length = 10)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}