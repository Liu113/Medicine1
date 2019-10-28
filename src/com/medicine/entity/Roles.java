package com.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles", catalog = "medicine")
public class Roles implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rolename;
	private String roledesc;
	private String remark;
	private String spare1;
	private String spare2;

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** full constructor */
	public Roles(String rolename, String roledesc, String remark, String spare1,
			String spare2) {
		this.rolename = rolename;
		this.roledesc = roledesc;
		this.remark = remark;
		this.spare1 = spare1;
		this.spare2 = spare2;
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

	@Column(name = "rolename", length = 100)
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "roledesc", length = 100)
	public String getRoledesc() {
		return this.roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
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

	@Column(name = "spare2", length = 100)
	public String getSpare2() {
		return this.spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

}