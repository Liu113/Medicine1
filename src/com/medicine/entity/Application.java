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
 * Application entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "application", catalog = "medicine")
public class Application implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date createtime;
	private Integer tabMan;
	private String remark;
	private String spare1;

	// Constructors

	/** default constructor */
	public Application() {
	}

	/** full constructor */
	public Application(Date createtime, Integer tabMan, String remark,
			String spare1) {
		this.createtime = createtime;
		this.tabMan = tabMan;
		this.remark = remark;
		this.spare1 = spare1;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createtime", length = 10)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "tabMan")
	public Integer getTabMan() {
		return this.tabMan;
	}

	public void setTabMan(Integer tabMan) {
		this.tabMan = tabMan;
	}

	@Column(name = "remark", length = 100)
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

}