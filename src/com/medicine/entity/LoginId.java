package com.medicine.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LoginId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class LoginId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Timestamp loginTime;
	private String remark;

	// Constructors

	/** default constructor */
	public LoginId() {
	}

	/** full constructor */
	public LoginId(Integer id, Integer userId, Timestamp loginTime,
			String remark) {
		this.id = id;
		this.userId = userId;
		this.loginTime = loginTime;
		this.remark = remark;
	}

	// Property accessors

	@Column(name = "Id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "UserId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "LoginTime", length = 19)
	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LoginId))
			return false;
		LoginId castOther = (LoginId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getLoginTime() == castOther.getLoginTime()) || (this
						.getLoginTime() != null
						&& castOther.getLoginTime() != null && this
						.getLoginTime().equals(castOther.getLoginTime())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null && castOther.getRemark() != null && this
						.getRemark().equals(castOther.getRemark())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getLoginTime() == null ? 0 : this.getLoginTime().hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		return result;
	}

}