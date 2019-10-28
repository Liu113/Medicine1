package com.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Indepotbody entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "indepotbody", catalog = "medicine")
public class Indepotbody implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer inDepotId;
	private Integer medicineId;
	private Integer providerId;
	private Double inPrice;
	private Double outPrice;
	private Integer inNum;
	private String remark;
	private String spare;

	// Constructors

	/** default constructor */
	public Indepotbody() {
	}

	/** full constructor */
	public Indepotbody(Integer inDepotId, Integer medicineId,
			Integer providerId, Double inPrice, Double outPrice, Integer inNum,
			String remark, String spare) {
		this.inDepotId = inDepotId;
		this.medicineId = medicineId;
		this.providerId = providerId;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.inNum = inNum;
		this.remark = remark;
		this.spare = spare;
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

	@Column(name = "inDepotId")
	public Integer getInDepotId() {
		return this.inDepotId;
	}

	public void setInDepotId(Integer inDepotId) {
		this.inDepotId = inDepotId;
	}

	@Column(name = "medicineId")
	public Integer getMedicineId() {
		return this.medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	@Column(name = "providerId")
	public Integer getProviderId() {
		return this.providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	@Column(name = "inPrice", precision = 10)
	public Double getInPrice() {
		return this.inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	@Column(name = "outPrice", precision = 10)
	public Double getOutPrice() {
		return this.outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	@Column(name = "inNum")
	public Integer getInNum() {
		return this.inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "spare", length = 100)
	public String getSpare() {
		return this.spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}

}