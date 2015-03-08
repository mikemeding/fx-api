package com.uml.fx.entities;

import java.util.List;
import java.util.Map;

/**
 * Appraisal service.
 * <p>
 * @author mike
 */
public interface AppraisalService {

	/**
	 * Get a list of all the appraisal locations matching an address.
	 * <p>
	 * @param address the address (or address snippet)
	 * @return the list of locations
	 */
	List<AppraisalLocation> listAppraisalLocationByAddress(String address);

	/**
	 * Get a list of all appraisal locations in a certain district.
	 * <p>
	 * @param district the district name
	 * @return the list of appraisal locations
	 */
	List<AppraisalLocation> listAppraisalLocationByDistrict(String district);

	/**
	 * Get an appraisal location by it property identifier.
	 * <p>
	 * @param propertyId the property identifier
	 * @return the appraisal location
	 */
	AppraisalLocation getLocationByPropertyId(String propertyId);

	/**
	 * Add an appraisal location.
	 * <p>
	 * @param location the appraisal location
	 */
	void addAppraisalLocation(AppraisalLocation location);

	/**
	 * Update an appraisal location.
	 * <p>
	 * @param location the appraisal location
	 */
	void updateAppraisalLocation(AppraisalLocation location);

	/**
	 * Get the appraisal data for a property for a tax year.
	 * <p>
	 * @param taxYear the tax year
	 * @param pid     the property id
	 * @return the appraisal data
	 */
	AppraisalData getAppraisalDataByPIDYear(String taxYear, String pid);

	/**
	 * Get a list of properties in an assessed range.
	 * <p>
	 * @param taxYear the tax year
	 * @param from    starting range
	 * @param to      end range
	 * @return the list of properties
	 */
	List<AppraisalData> getAppraisalDataByAssessedRange(String taxYear, double from, double to);

	/**
	 * Get the tax break down for a property.
	 * <p>
	 * @param year the year
	 * @param pid  the property identifier
	 * @return the tax break down
	 */
	Map<String, Double> getTaxBreakdown(String year, String pid);

}
