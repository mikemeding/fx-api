package com.uml.fx.rest;

import com.uml.fx.entities.TaxRate;
import com.uml.fx.entities.TaxRateEntity;
import com.uml.fx.entities.TaxRateService;
import com.uml.fx.response.GenericResponse;
import com.uml.fx.response.ListResponse;
import com.uml.fx.response.MessageResponse;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Deal with the tax rates and tax rate entities.
 * <p>
 * @author mike
 */
@Path("taxRates")
public class TaxRates {

	private static final Logger log = Logger.getLogger(TaxRates.class.getName());

	@EJB
	private TaxRateService taxRates;

	/**
	 * Create a tax rate entity.
	 * <p>
	 * @param dto the tax rate entity fields
	 * @param req the HTTP request
	 * @return 200 OK
	 */
	@POST
	@Path("createTaxRateEntity")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createTaxRateEntity(
			TaxRateEntity dto,
			@Context HttpServletRequest req) {
		try {
			taxRates.createTaxRateEntity(dto);
			return Response.ok(GenericResponse.OK).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Update a tax rate entity.
	 * <p>
	 * @param dto the tax rate entity
	 * @param req the HTTP request
	 * @return 200 OK
	 */
	@POST
	@Path("updateTaxRateEntity")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateTaxRateEntity(
			TaxRateEntity dto,
			@Context HttpServletRequest req) {
		try {
			taxRates.updateTaxRateEntity(dto);
			return Response.ok(GenericResponse.OK).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Get a list of tax rate entities.
	 * <p>
	 * @param year the tax year
	 * @param name name of the tax rate entity (wildcard allowed)
	 * @param req  the HTTP request
	 * @return the list of tax rate entities
	 */
	@GET
	@Path("listTaxRateEntities/{year}/{name}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response listTaxRateEntities(
			@PathParam("year") String year,
			@PathParam("name") String name,
			@Context HttpServletRequest req) {
		try {
			List<TaxRateEntity> list = taxRates.listTaxRateEntity(name, year);
			return Response.ok(new ListResponse<>(list)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * List the tax rates for a tax rate entity.
	 * <p>
	 * @param year the desired year
	 * @param name the tax rate entity name (can be wildcard)
	 * @param req  the HTTP request
	 * @return the list of tax rates
	 */
	@GET
	@Path("listTaxRatesForTaxRateEntity/{year}/{name}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response listTaxRatesForTaxRateEntity(
			@PathParam("year") String year,
			@PathParam("name") String name,
			@Context HttpServletRequest req) {
		try {
			List<TaxRate> list = taxRates.listTaxRatesByTaxRateEntity(year, name);
			return Response.ok(new ListResponse<>(list)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}
}
