package com.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "classlist", catalog = "medicine")
public class Classlist implements java.io.Serializable {

	// Fields

	private Integer class_;
	private String className;
	private String spare;

	// Constructors

	/** default constructor */
	public Classlist() {
	}

	/** full constructor */
	public Classlist(String className, String spare) {
		this.className = className;
		this.spare = spare;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "class", unique = true, nullable = false)
	public Integer getClass_() {
		return this.class_;
	}

	public void setClass_(Integer class_) {
		this.class_ = class_;
	}

	@Column(name = "className", length = 100)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "spare", length = 100)
	public String getSpare() {
		return this.spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}

}