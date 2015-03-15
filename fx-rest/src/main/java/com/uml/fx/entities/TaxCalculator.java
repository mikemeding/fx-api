package com.uml.fx.entities;

/**
 * Tax calculations.
 * <p>
 * @author mike
 */
public interface TaxCalculator {

	/**
	 * Calculate the full tax on the assessed value.
	 * <p>
	 * @param tr the tax rate
	 * @param ad the appraisal data
	 * @return the tax liability
	 */
	double fullTax(TaxRate tr, AppraisalData ad);

	/**
	 * Calculate the homestead exempted tax on the assessed value.
	 * <p>
	 * @param tr the tax rate
	 * @param ad the appraisal data
	 * @return the tax liability
	 */
	double homesteadTax(TaxRate tr, AppraisalData ad);

}
