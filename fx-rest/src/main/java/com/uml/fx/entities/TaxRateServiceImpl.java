package com.uml.fx.entities;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * The tax rate services.
 * <p>
 * @author mike
 */
@Stateless
@Local(value = TaxRateService.class)
public class TaxRateServiceImpl implements TaxRateService {

	@PersistenceContext(unitName = "fxPU")
	private EntityManager em;

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void createTaxRateEntity(TaxRateEntity tre) {
		em.persist(tre);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void updateTaxRateEntity(TaxRateEntity tre) {
		em.merge(tre);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public List<TaxRateEntity> listTaxRateEntity(String name, String year) {
		name = name.contains("*") ? name.replace('*', '%') : name + "%";

		TypedQuery<TaxRateEntity> query = em.createNamedQuery(TaxRateEntity.SELECT_BY_NAME_YEAR, TaxRateEntity.class);
		query.setParameter("name", name);
		query.setParameter("year", year);
		query.setMaxResults(100);
		return query.getResultList();
	}

	// -----------------------------------------------------------------------
	// Tax Rates
	/**
	 * {@inheritDoc }
	 */
	@Override
	public void createTaxRate(TaxRate tr) {
		em.persist(tr);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void updateTaxRate(TaxRate tr) {
		em.merge(tr);

	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public List<TaxRate> listTaxRates(String name) {
		name = name.contains("*") ? name.replace('*', '%') : name + "%";

		TypedQuery<TaxRate> query = em.createNamedQuery(TaxRate.SELECT_BY_NAME, TaxRate.class);
		query.setParameter("name", name);
		query.setMaxResults(100);
		return query.getResultList();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public List<TaxRate> listTaxRatesByTaxRateEntity(String year, String name) {
		name = name.contains("*") ? name.replace('*', '%') : name + "%";

		TypedQuery<TaxRate> query = em.createNamedQuery(TaxRate.SELECT_BY_TRE, TaxRate.class);
		query.setParameter("year", year);
		query.setParameter("name", name);
		return query.getResultList();
	}

}
