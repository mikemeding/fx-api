package com.uml.fx.entities;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * The appraisal services.
 * <p>
 * @author mike
 */
@Stateless
@Local(value = AppraisalService.class)
public class AppraisalServiceImpl implements AppraisalService {

	@PersistenceContext(unitName = "fxPU")
	private EntityManager em;

	/**
	 * {@inheritDoc }
	 */
	@Override
	public List<AppraisalLocation> listAppraisalLocationByAddress(String address) {
		TypedQuery<AppraisalLocation> query = em.createNamedQuery(AppraisalLocation.SELECT_BY_DISTRICT, AppraisalLocation.class);
		// replace wildcard parameter with the database wildcard parameter
		address = address.replace("*", "%");
		query.setParameter("address", address);
		query.setMaxResults(100);
		return query.getResultList();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public List<AppraisalLocation> listAppraisalLocationByDistrict(String district) {
		TypedQuery<AppraisalLocation> query = em.createNamedQuery(AppraisalLocation.SELECT_BY_DISTRICT, AppraisalLocation.class);
		district = district.replace("*", "%");
		query.setParameter("district", district);
		query.setMaxResults(100);
		return query.getResultList();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public AppraisalLocation getLocationByPropertyId(String propertyId) {
		TypedQuery<AppraisalLocation> query = em.createNamedQuery(AppraisalLocation.SELECT_BY_PROPERTY_ID, AppraisalLocation.class);
		query.setParameter("pid", propertyId);
		return query.getSingleResult();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void addAppraisalLocation(AppraisalLocation location) {
		em.persist(location);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void updateAppraisalLocation(AppraisalLocation location) {
		em.merge(location);
	}

	// ------------------------------------------------------------------------
	// Appraisal data
	/**
	 * {@inheritDoc }
	 */
	@Override
	public AppraisalData getAppraisalDataByPIDYear(String taxYear, String pid) {
		TypedQuery<AppraisalData> query = em.createNamedQuery(AppraisalData.SELECT_BY_PID_YEAR, AppraisalData.class);
		query.setParameter("taxYear", taxYear);
		query.setParameter("pid", pid);
		return query.getSingleResult();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public List<AppraisalData> getAppraisalDataByAssessedRange(String taxYear, double from, double to) {
		TypedQuery<AppraisalData> query = em.createNamedQuery(AppraisalData.SELECT_BY_ASSESSED_RANGE, AppraisalData.class);
		query.setParameter("taxYear", taxYear);
		query.setParameter("fromValue", from);
		query.setParameter("toValue", to);
		query.setMaxResults(100);
		return query.getResultList();
	}

}
