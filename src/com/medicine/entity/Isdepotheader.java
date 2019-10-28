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
 * Isdepotheader entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "isdepotheader", catalog = "medicine")
public class Isdepotheader implements java.io.Serializable {

	// Fields

	private Integer inDepotId;
	private Date inTime;
	private Integer tabMan;
	private Integer status;
	private String remark;
	private String spare1;
	private String spare2;

	// Constructors

	/** default constructor */
	public Isdepotheader() {
	}

	/** full constructor */
	public Isdepotheader(Date inTime, Integer tabMan, Integer status,
			String remark, String spare1, String spare2) {
		this.inTime = inTime;
		this.tabMan = tabMan;
		this.status = status;
		this.remark = remark;
		this.spare1 = spare1;
		this.spare2 = spare2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "inDepotId", unique = true, nullable = false)
	public Integer getInDepotId() {
		return this.inDepotId;
	}

	public void setInDepotId(Integer inDepotId) {
		this.inDepotId = inDepotId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "inTime", length = 10)
	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "TabMan")
	public Integer getTabMan() {
		return this.tabMan;
	}

	public void setTabMan(Integer tabMan) {
		this.tabMan = tabMan;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "Remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "spare1", length = 100)
	public String getSpare1() {
		return this.spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}

	@Column(name = "spare2", length = 100)
	public String getSpare2() {
		return this.spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

}