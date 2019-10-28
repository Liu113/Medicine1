package com.medicine.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Login entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "login", catalog = "medicine")
public class Login implements java.io.Serializable {

	// Fields

	private LoginId id;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** full constructor */
	public Login(LoginId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "Id")),
			@AttributeOverride(name = "userId", column = @Column(name = "UserId")),
			@AttributeOverride(name = "loginTime", column = @Column(name = "LoginTime", length = 19)),
			@AttributeOverride(name = "remark", column = @Column(name = "remark", length = 100)) })
	public LoginId getId() {
		return this.id;
	}

	public void setId(LoginId id) {
		this.id = id;
	}

}