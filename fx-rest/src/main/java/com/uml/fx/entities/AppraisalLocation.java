package com.uml.fx.entities;

import java.io.Serializable;
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

/**
 * Appraisal location: Property identifier as well as its address.
 * <p>
 * @author mike
 */
@Entity

@Table(name = AppraisalLocation.TABLENAME)
@NamedQueries({
	@NamedQuery(
			name = AppraisalLocation.SELECT_ALL,
			query = "SELECT a FROM AppraisalLocation a"),
	@NamedQuery(
			name = AppraisalLocation.SELECT_BY_ADDRESS,
			query = "SELECT a FROM AppraisalLocation a WHERE a.situsAddress like :address"),
	@NamedQuery(
			name = AppraisalLocation.SELECT_BY_PROPERTY_ID,
			query = "SELECT a FROM AppraisalLocation a WHERE a.pid=:pid"),
	@NamedQuery(
			name = AppraisalLocation.SELECT_BY_DISTRICT,
			query = "SELECT a FROM AppraisalLocation a WHERE a.appraisalDistrict like :district")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AppraisalLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String TABLENAME = "n_appraisal_location";

	// Named queries
	public final static String SELECT_ALL = "AppraisalLocation.selectAll";
	public final static String SELECT_BY_PROPERTY_ID = "AppraisalLocation.selectByPropertyId";
	public final static String SELECT_BY_ADDRESS = "AppraisalLocation.selectByAddress";
	public final static String SELECT_BY_DISTRICT = "AppraisalLocation.selectByDistrict";
	//
	// == Database fields ===================
	// Identifier
	@Id
	@Column(name = "pid", length = 80, nullable = false, unique = true)
	@XmlID
	private String pid;
	//
	// Situs address
	@Column(name = "situs_address", nullable = false, length = 80)
	private String situsAddress;
	//
	// Situs city
	@Column(name = "situs_city", nullable = false, length = 80)
	private String situsCity;
	//
	// Appraisal district
	@Column(name = "appraisal_district", nullable = false, length = 255)
	private String appraisalDistrict;

	public AppraisalLocation() {
	}

	public AppraisalLocation(String pid, String situsAddress, String situsCity, String appraisalDistrict) {
		this.pid = pid;
		this.situsAddress = situsAddress;
		this.situsCity = situsCity;
		this.appraisalDistrict = appraisalDistrict;
	}

	public String getPid() {
		return pid;
	}

	public String getSitusAddress() {
		return situsAddress;
	}

	public String getSitusCity() {
		return situsCity;
	}

	public String getAppraisalDistrict() {
		return appraisalDistrict;
	}

}
