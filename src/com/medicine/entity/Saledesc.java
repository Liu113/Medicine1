package com.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Saledesc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "saledesc", catalog = "medicine")
public class Saledesc implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer saleId;
	private Integer providerId;
	private Integer medicineId;
	private Integer amout;

	// Constructors

	/** default constructor */
	public Saledesc() {
	}

	/** full constructor */
	public Saledesc(Integer saleId, Integer providerId, Integer medicineId,
			Integer amout) {
		this.saleId = saleId;
		this.providerId = providerId;
		this.medicineId = medicineId;
		this.amout = amout;
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

	@Column(name = "saleId")
	public Integer getSaleId() {
		return this.saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
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

	@Column(name = "amout")
	public Integer getAmout() {
		return this.amout;
	}

	public void setAmout(Integer amout) {
		this.amout = amout;
	}

}