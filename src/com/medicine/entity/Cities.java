package com.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cities entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cities", catalog = "medicine")
public class Cities implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cityid;
	private String city;
	private String provinceid;

	// Constructors

	/** default constructor */
	public Cities() {
	}

	/** full constructor */
	public Cities(String cityid, String city, String provinceid) {
		this.cityid = cityid;
		this.city = city;
		this.provinceid = provinceid;
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

	@Column(name = "cityid", nullable = false, length = 20)
	public String getCityid() {
		return this.cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	@Column(name = "city", nullable = false, length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "provinceid", nullable = false, length = 20)
	public String getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

}