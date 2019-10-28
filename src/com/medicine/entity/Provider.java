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
 * Provider entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "provider", catalog = "medicine")
public class Provider implements java.io.Serializable {

	// Fields

	private Integer pid;
	private String pname;
	private String address;
	private String principal;
	private String telephone;
	private Integer isDelete;
	private Date createtime;
	private Integer pstatus;
	private String spare1;
	private String spare2;

	// Constructors

	/** default constructor */
	public Provider() {
	}

	/** full constructor */
	public Provider(String pname, String address, String principal,
			String telephone, Integer isDelete, Date createtime,
			Integer pstatus, String spare1, String spare2) {
		this.pname = pname;
		this.address = address;
		this.principal = principal;
		this.telephone = telephone;
		this.isDelete = isDelete;
		this.createtime = createtime;
		this.pstatus = pstatus;
		this.spare1 = spare1;
		this.spare2 = spare2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pid", unique = true, nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "pname", length = 100)
	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "principal", length = 100)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "telephone", length = 100)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createtime", length = 10)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "pstatus")
	public Integer getPstatus() {
		return this.pstatus;
	}

	public void setPstatus(Integer pstatus) {
		this.pstatus = pstatus;
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