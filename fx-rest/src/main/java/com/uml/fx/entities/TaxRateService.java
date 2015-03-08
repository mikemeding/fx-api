package com.uml.fx.entities;

import java.util.List;

/**
 * Tax rate services.
 * <p>
 * @author mike
 */
public interface TaxRateService {

	/**
	 * Create a tax rate entity.
	 * <p>
	 * @param tre the tax rate entity
	 */
	void createTaxRateEntity(TaxRateEntity tre);

	/**
	 * Update a tax rate entity.
	 * <p>
	 * @param tre the tax rate entity
	 */
	void updateTaxRateEntity(TaxRateEntity tre);

	/**
	 * List the tax rate entities. You can use a simple wildcard to the name.
	 * <p>
	 * @param name the name of the tax rate entity
	 * @param year the tax year
	 * @return list of tax rate entities
	 */
	List<TaxRateEntity> listTaxRateEntity(String name, String year);

	/**
	 * Create a tax rate.
	 * <p>
	 * @param tr the tax rate
	 */
	void createTaxRate(TaxRate tr);

	/**
	 * Update a tax rate.
	 * <p>
	 * @param tr the tax rate
	 */
	void updateTaxRate(TaxRate tr);

	/**
	 * Find the tax rates by name.
	 * <p>
	 * @param name the name (can contain wildcards)
	 * @return the list of tax rates
	 */
	List<TaxRate> listTaxRates(String name);

	/**
	 * Get a list of all tax rates for a tax rate entity.
	 * <p>
	 * @param year the year of interest
	 * @param name is the name of the tax rate entity
	 * @return the list of tax rates
	 */
	List<TaxRate> listTaxRatesByTaxRateEntity(String year, String name);

}
