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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Tax rates.
 * <p>
 * @author mike
 */
@Entity
@Table(name = TaxRate.TABLENAME)
// @Cacheable(true)
@NamedQueries({
	@NamedQuery(
			name = TaxRate.SELECT_ALL,
			query = "SELECT a FROM TaxRate a"),
	@NamedQuery(
			name = TaxRate.SELECT_BY_NAME,
			query = "SELECT a FROM TaxRate a WHERE a.name like :name ORDER BY a.name"),
	@NamedQuery(
			name = TaxRate.SELECT_BY_YEAR_NAME,
			query = "SELECT a FROM TaxRate a,TaxRateEntity b WHERE a.taxRateEntity.id=b.id AND b.year=:year AND a.name=:trName"),
	@NamedQuery(
			name = TaxRate.SELECT_BY_TRE,
			query = "SELECT a FROM TaxRate a WHERE a.taxRateEntity IN (SELECT b.id FROM TaxRateEntity b WHERE b.year=:year AND b.name like :name)"),
	@NamedQuery(
			name = TaxRate.SELECT_BY_TRE_NAME,
			query = "SELECT a FROM TaxRate a WHERE a.name=:name AND a.taxRateEntity=:tre")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaxRate implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String TABLENAME = "n_tr_tax_rates";

	// Named queries
	public final static String SELECT_ALL = "TaxRate.selectAll";
	public final static String SELECT_BY_YEAR_NAME = "TaxRate.selectByYearName";
	public final static String SELECT_BY_NAME = "TaxRate.selectByName";
	public final static String SELECT_BY_TRE = "TaxRate.selectByTRE";
	public final static String SELECT_BY_TRE_NAME = "TaxRate.selectByTREName";
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
	// Optional homestead exemption
	@Column(name = "opt_hs", columnDefinition = "Decimal(13,2)")
	private double optionalHomestead;
	//
	// General homestead exemption
	@Column(name = "gen_hs", columnDefinition = "Decimal(13,2)")
	private double generalHomestead;
	//
	// Over 65 exemption
	@Column(name = "over65", columnDefinition = "Decimal(13,2)")
	private double over65;
	//
	// Disabled exemption
	@Column(name = "dis_person", columnDefinition = "Decimal(13,2)")
	private double disabledPerson;
	//
	// Tax rate
	@Column(name = "tax_rate", columnDefinition = "Decimal(13,6)")
	private double taxRate;
	//
	// Always included
	@Column(name = "always", nullable = false)
	private boolean always;
	// 
	// created
	@OneToOne
	@JoinColumn(name = "tre_id", nullable = false)
	private TaxRateEntity taxRateEntity;

	public TaxRate() {
	}

	public TaxRate(String name, TaxRateEntity taxRateEntity) {
		this.name = name;
		this.taxRateEntity = taxRateEntity;
	}

	public Long getId() {
		return id;
	}

	public void setOptionalHomestead(double optionalHomestead) {
		this.optionalHomestead = optionalHomestead;
	}

	public void setGeneralHomestead(double generalHomestead) {
		this.generalHomestead = generalHomestead;
	}

	public void setOver65(double over65) {
		this.over65 = over65;
	}

	public void setDisabledPerson(double disabledPerson) {
		this.disabledPerson = disabledPerson;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public void setAlways(boolean always) {
		this.always = always;
	}

	public String getName() {
		return name;
	}

	public double getOptionalHomestead() {
		return optionalHomestead;
	}

	public double getGeneralHomestead() {
		return generalHomestead;
	}

	public double getOver65() {
		return over65;
	}

	public double getDisabledPerson() {
		return disabledPerson;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public boolean isAlways() {
		return always;
	}

	public TaxRateEntity getTaxRateEntity() {
		return taxRateEntity;
	}

}
