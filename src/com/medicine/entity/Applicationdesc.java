package com.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Applicationdesc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "applicationdesc", catalog = "medicine")
public class Applicationdesc implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer applicationId;
	private Integer providerId;
	private Integer medicineId;
	private Integer nowNum;
	private Integer needNum;
	private String remark;
	private String spare1;

	// Constructors

	/** default constructor */
	public Applicationdesc() {
	}

	/** full constructor */
	public Applicationdesc(Integer applicationId, Integer providerId,
			Integer medicineId, Integer nowNum, Integer needNum, String remark,
			String spare1) {
		this.applicationId = applicationId;
		this.providerId = providerId;
		this.medicineId = medicineId;
		this.nowNum = nowNum;
		this.needNum = needNum;
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

	@Column(name = "applicationId")
	public Integer getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	@Column(name = "providerId")
	public Integer getProviderId() {
		return this.providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	@Column(name = "medicineId")
	public Integer getMedicineId() {
		return this.medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	@Column(name = "nowNum")
	public Integer getNowNum() {
		return this.nowNum;
	}

	public void setNowNum(Integer nowNum) {
		this.nowNum = nowNum;
	}

	@Column(name = "needNum")
	public Integer getNeedNum() {
		return this.needNum;
	}

	public void setNeedNum(Integer needNum) {
		this.needNum = needNum;
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