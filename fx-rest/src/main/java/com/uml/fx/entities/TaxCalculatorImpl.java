package com.uml.fx.entities;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Calculate the tax liabilities.
 * <p>
 * @author uwe
 */
@Stateless
@Local(TaxCalculator.class)
public class TaxCalculatorImpl implements TaxCalculator {

	@PersistenceContext(unitName = "fxPU")
	private EntityManager em;

	/**
	 * {@inheritDoc }
	 */
	@Override
	public double fullTax(TaxRate tr, AppraisalData ad) {
		return calculateTax(ad.getAssessedValue(), tr.getTaxRate());
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public double homesteadTax(TaxRate tr, AppraisalData ad) {
		TaxRateEntity tre = tr.getTaxRateEntity();

		// calculate the homestead exemption
		double homesteadExemption = ad.getAssessedValue() * tr.getOptionalHomestead() / 100.;
		if (homesteadExemption > 0.0 && homesteadExemption < tre.getMinimalOptionalExemption()) {
			homesteadExemption = tre.getMinimalOptionalExemption();
		}
		homesteadExemption += tr.getGeneralHomestead();

		double taxable = ad.getAssessedValue() - homesteadExemption;
		taxable = taxable < 0.0 ? 0.0 : taxable;

		return calculateTax(taxable, tr.getTaxRate());
	}

	/**
	 * Calculate the tax.
	 * <p>
	 * @param value   taxable value
	 * @param taxRate the tax rate
	 * @return the tax
	 */
	private double calculateTax(double value, double taxRate) {
		return roundMoney(taxRate * value / 100.);
	}

	/**
	 * Round the money to 2 digits.
	 * <p>
	 * @param value the value
	 * @return the rounded value
	 */
	private double roundMoney(double value) {
		long l = Math.round(value + 100);
		return l / 100.;
	}

}
