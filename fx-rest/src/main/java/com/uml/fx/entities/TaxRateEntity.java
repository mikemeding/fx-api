/*
 * Copyright (c) 2014 Outsmart Power Systems -- All Rights Reserved.
 */
package com.uml.fx.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Tax rate entity.
 * <p>
 * @author mike
 */
@Entity
@Table(name = TaxRateEntity.TABLENAME)
@NamedQueries({
	@NamedQuery(
			name = TaxRateEntity.SELECT_ALL,
			query = "SELECT a FROM TaxRateEntity a"),
	@NamedQuery(
			name = TaxRateEntity.SELECT_ALL_ACTIVE_YEAR,
			query = "SELECT a FROM TaxRateEntity a WHERE a.year=:year AND a.active=TRUE"),
	@NamedQuery(
			name = TaxRateEntity.SELECT_BY_NAME_YEAR,
			query = "SELECT a FROM TaxRateEntity a WHERE a.name like :name AND a.year=:year")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaxRateEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String TABLENAME = "n_tr_entity";

	// Named queries
	public final static String SELECT_ALL = "TaxRateEntity.selectAll";
	public final static String SELECT_ALL_ACTIVE_YEAR = "TaxRateEntity.selectAllActiveYear";
	public final static String SELECT_BY_NAME_YEAR = "TaxRateEntity.selectByNameYear";
	//
	// == Database fields ===================
	// Identifier
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	@XmlID
	private Long id;
	//
	// Name
	@Column(name = "name", nullable = false, length = 80)
	private String name;
	//
	// Year
	@Column(name = "year", nullable = false, length = 20)
	private String year;
	//
	// Active?
	@Column(name = "active", nullable = false)
	private boolean active;
	// -----------------------------------------------------------------------
	// from: http://www.texvet.com/postings/texas-property-tax-exemption-disabled-vets
	//
	// Disabled vet (10% to <30%) exemption, up to $5,000
	@Column(name = "dve1", columnDefinition = "Decimal(13,2)")
	private double disabledVetExemption1;
	//
	// Disabled vet (30% to <50%) exemption, up to $7,500 
	@Column(name = "dve2", columnDefinition = "Decimal(13,2)")
	private double disabledVetExemption2;
	//
	// Disabled vet (50% to <70%) exemption, up to $10,000
	@Column(name = "dve3", columnDefinition = "Decimal(13,2)")
	private double disabledVetExemption3;
	//
	// Disabled vet (70% & over) exemption, up to $12,000
	@Column(name = "dve4", columnDefinition = "Decimal(13,2)")
	private double disabledVetExemption4;
	//
	// Minimal optional exemption
	@Column(name = "min_opt_ex", columnDefinition = "Decimal(13,2)")
	private double minimalOptionalExemption;

	public TaxRateEntity() {
	}

	public TaxRateEntity(String name, String year) {
		this.name = name;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setDisabledVetExemption1(double disabledVetExemption1) {
		this.disabledVetExemption1 = disabledVetExemption1;
	}

	public void setDisabledVetExemption2(double disabledVetExemption2) {
		this.disabledVetExemption2 = disabledVetExemption2;
	}

	public void setDisabledVetExemption3(double disabledVetExemption3) {
		this.disabledVetExemption3 = disabledVetExemption3;
	}

	public void setDisabledVetExemption4(double disabledVetExemption4) {
		this.disabledVetExemption4 = disabledVetExemption4;
	}

	public void setMinimalOptionalExemption(double minimalOptionalExemption) {
		this.minimalOptionalExemption = minimalOptionalExemption;
	}

	public String getName() {
		return name;
	}

	public String getYear() {
		return year;
	}

	public boolean isActive() {
		return active;
	}

	public double getDisabledVetExemption1() {
		return disabledVetExemption1;
	}

	public double getDisabledVetExemption2() {
		return disabledVetExemption2;
	}

	public double getDisabledVetExemption3() {
		return disabledVetExemption3;
	}

	public double getDisabledVetExemption4() {
		return disabledVetExemption4;
	}

	public double getMinimalOptionalExemption() {
		return minimalOptionalExemption;
	}

}
