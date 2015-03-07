package com.uml.fx.entities;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The appraisal data.
 * <p>
 * @author mike
 */
@Entity
@Table(name = AppraisalData.TABLENAME)
@NamedQueries({
	@NamedQuery(
			name = AppraisalData.SELECT_ALL,
			query = "SELECT a FROM AppraisalData a"),
	@NamedQuery(
			name = AppraisalData.SELECT_BY_PID,
			query = "SELECT a FROM AppraisalData a WHERE a.pid=:pid"),
	@NamedQuery(
			name = AppraisalData.SELECT_BY_PID_YEAR,
			query = "SELECT a FROM AppraisalData a WHERE a.pid=:pid AND a.taxYear=:taxYear"),
	@NamedQuery(
			name = AppraisalData.SELECT_BY_ASSESSED_RANGE,
			query = "SELECT a FROM AppraisalData a WHERE a.taxYear=:taxYear AND a.assessedValue>=:fromValue AND a.assessedValue<=:toValue"),
	@NamedQuery(
			name = AppraisalData.SELECT_BY_NAME,
			query = "SELECT a FROM AppraisalData a WHERE a.name like :name")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AppraisalData implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String TABLENAME = "n_appraisal_data";

	// Named queries
	public final static String SELECT_ALL = "AppraisalData.selectAll";
	public final static String SELECT_BY_NAME = "AppraisalData.selectByName";
	public final static String SELECT_BY_PID = "AppraisalData.selectByPID";
	public final static String SELECT_BY_PID_YEAR = "AppraisalData.selectByPIDYear";
	public final static String SELECT_BY_ASSESSED_RANGE = "AppraisalData.selectByAssessedRange";
	//
	// == Database fields ===================
	// Identifier
	@Id
	@Column(name = "pid", length = 80, nullable = false, unique = true)
	@XmlID
	private String pid;
	// Tax year
	@Id
	@Column(name = "tax_year", length = 80, nullable = false, unique = true)
	@XmlID
	private String taxYear;
	//
	// Name
	@Column(name = "name", nullable = false, length = 80)
	private String name;
	//
	// City
	@Column(name = "city", nullable = false, length = 80)
	private String city;
	//
	// state
	@Column(name = "state", nullable = false, length = 80)
	private String state;
	//
	// zip
	@Column(name = "zip", nullable = false, length = 80)
	private String zip;
	//
	// Taxing entities
	@Column(name = "taxing_entities", nullable = false, length = 80)
	@XmlTransient
	private String taxingEntities;
	//
	// market value (this should be Currency or BigDecimal really)
	@Column(name = "market_value", nullable = false)
	private double marketValue;
	//
	// assessed value (this should be Currency or BigDecimal really)
	@Column(name = "assessed_value", nullable = false)
	private double assessedValue;
	//
	// deed transfer
	@Column(name = "deed_xfer", nullable = false, length = 80)
	private String deedTransfer;
	//
	// improvement
	@Column(name = "improvement", nullable = false, length = 80)
	private String improvement;
	//
	// homestead exempeted tax (this should be Currency or BigDecimal really)
	@Column(name = "hs_tax", nullable = false)
	private double hsTax;
	//
	// full tax (this should be Currency or BigDecimal really)
	@Column(name = "full_tax", nullable = false)
	private double fullTax;

	public AppraisalData() {
	}

	public AppraisalData(String pid, String taxYear) {
		this.pid = pid;
		this.taxYear = taxYear;
	}

	@XmlElement(name="taxingEntities")
	public Set<String> taxingEntites() {
		Set<String> tes = new TreeSet<>();
		String[] parts = taxingEntities.split(",");
		for (String part : parts) {
			tes.add(part.trim());
		}
		return tes;
	}

	public void addTaxingEntity(String te) {
		taxingEntities = taxingEntities + "," + te;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public void setAssessedValue(double assessedValue) {
		this.assessedValue = assessedValue;
	}

	public void setDeedTransfer(String deedTransfer) {
		this.deedTransfer = deedTransfer;
	}

	public void setImprovement(String improvement) {
		this.improvement = improvement;
	}

	public void setHsTax(double hsTax) {
		this.hsTax = hsTax;
	}

	public void setFullTax(double fullTax) {
		this.fullTax = fullTax;
	}

	public String getPid() {
		return pid;
	}

	public String getTaxYear() {
		return taxYear;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getAssessedValue() {
		return assessedValue;
	}

	public String getDeedTransfer() {
		return deedTransfer;
	}

	public String getImprovement() {
		return improvement;
	}

	public double getHsTax() {
		return hsTax;
	}

	public double getFullTax() {
		return fullTax;
	}

}
