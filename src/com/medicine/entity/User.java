package com.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "medicine")
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String loginname;
	private String password;
	private String email;
	private String address;
	private Integer role;
	private Integer salary;
	private String salaryDesc;
	private Integer status;
	private String spare1;
	private String spare2;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String username, String loginname, String password,
			String email, String address, Integer role, Integer salary,
			String salaryDesc, Integer status, String spare1, String spare2) {
		this.username = username;
		this.loginname = loginname;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
		this.salary = salary;
		this.salaryDesc = salaryDesc;
		this.status = status;
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

	@Column(name = "username", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "loginname", length = 100)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "role")
	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Column(name = "salary")
	public Integer getSalary() {
		return this.salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Column(name = "salaryDesc", length = 100)
	public String getSalaryDesc() {
		return this.salaryDesc;
	}

	public void setSalaryDesc(String salaryDesc) {
		this.salaryDesc = salaryDesc;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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