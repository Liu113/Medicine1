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
 * Sale entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sale", catalog = "medicine")
public class Sale implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date saleTime;
	private Double salePrice;
	private Integer saleManId;
	private String saleMan;
	private String remark;

	// Constructors

	/** default constructor */
	public Sale() {
	}

	/** full constructor */
	public Sale(Date saleTime, Double salePrice, Integer saleManId,
			String saleMan, String remark) {
		this.saleTime = saleTime;
		this.salePrice = salePrice;
		this.saleManId = saleManId;
		this.saleMan = saleMan;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "saleTime", length = 10)
	public Date getSaleTime() {
		return this.saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	@Column(name = "salePrice", precision = 10)
	public Double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	@Column(name = "saleManId")
	public Integer getSaleManId() {
		return this.saleManId;
	}

	public void setSaleManId(Integer saleManId) {
		this.saleManId = saleManId;
	}

	@Column(name = "saleMan", length = 100)
	public String getSaleMan() {
		return this.saleMan;
	}

	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}