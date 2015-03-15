package com.uml.fx.rest;

import com.uml.fx.entities.AppraisalData;
import com.uml.fx.entities.AppraisalLocation;
import com.uml.fx.entities.AppraisalService;
import com.uml.fx.response.GenericResponse;
import com.uml.fx.response.ListResponse;
import com.uml.fx.response.MapResponse;
import com.uml.fx.response.MessageResponse;
import com.uml.fx.response.SingleResponse;
import java.util.List;
import java.util.Map;
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
 * FundsXpert Graphical User Interface Programming II Professor Jessie Heines,
 * Michael Meding & Jose Flores 2015-02-12
 * <p/>
 * API to the appraisal data.
 * <p>
 * @author mike
 */
@Path("appraisal")
public class Appraisal {

	private static final Logger log = Logger.getLogger(Appraisal.class.getName());

	@EJB
	private AppraisalService appraisal;

	/**
	 * Test if the service is available.
	 * <p>
	 * @return 200 OK
	 */
	@GET
	@Path("ping")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping() {
		return Response.ok("Appraisal PONG", MediaType.TEXT_PLAIN).build();
	}

	/**
	 * Get an appraisal location by its property identifier.
	 * <p>
	 * @param propertyId the property identifier
	 * @return
	 */
	@GET
	@Path("getAppraisalLocation/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppraisalLocationByPID(
			@PathParam("pid") String propertyId) {
		try {
			AppraisalLocation location = appraisal.getLocationByPropertyId(propertyId);
			return Response.ok(new SingleResponse<>(location)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Get the appraisal locations by district.
	 * <p>
	 * @param district the district name
	 * @param req      the HTTP request
	 * @return the list of appraisal locations
	 */
	@GET
	@Path("getAppraisalLocationByDistrict/{district}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppraisalLocationByDistrict(
			@PathParam("district") String district,
			@Context HttpServletRequest req) {
		try {
			List<AppraisalLocation> list = appraisal.listAppraisalLocationByDistrict(district);
			return Response.ok(new ListResponse<>(list)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Get the list of appraisal locations by address.
	 * <p>
	 * @param address is the address
	 * @param req     the HTTP request
	 * @return the list of appraisal locations
	 */
	@GET
	@Path("getAppraisalLocationByAddress/{address}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppraisalLocationByAddress(
			@PathParam("address") String address,
			@Context HttpServletRequest req) {
		try {
			List<AppraisalLocation> list = appraisal.listAppraisalLocationByAddress(address);
			return Response.ok(new ListResponse<>(list)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Add a new appraisal location.
	 * <p>
	 * @param dto new appraisal location
	 * @param req HTTP request
	 * @return OK
	 */
	@POST
	@Path("addAppraisalLocation")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addAppraisalLocation(
			AppraisalLocation dto,
			@Context HttpServletRequest req) {
		try {
			appraisal.addAppraisalLocation(dto);
			return Response.ok(GenericResponse.OK).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Update a appraisal location.
	 * <p>
	 * @param dto new appraisal location
	 * @param req HTTP request
	 * @return OK
	 */
	@POST
	@Path("updateAppraisalLocation")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateAppraisalLocation(
			AppraisalLocation dto,
			@Context HttpServletRequest req) {
		try {
			appraisal.updateAppraisalLocation(dto);
			return Response.ok(GenericResponse.OK).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Get the appraisal data for a particular tax year.
	 * <p>
	 * @param pid     the property identifier
	 * @param taxYear the tax year
	 * @param req     the HTTP request
	 * @return the appraisal data
	 */
	@GET
	@Path("getAppraisalData/{taxYear}/{pid}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAppraisalData(
			@PathParam("taxYear") String taxYear,
			@PathParam("pid") String pid,
			@Context HttpServletRequest req) {
		try {
			AppraisalData data = appraisal.getAppraisalDataByPIDYear(taxYear, pid);
			return Response.ok(new SingleResponse<>(data)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Get appraised properties in a certain assessment range.
	 * <p>
	 * @param taxYear the tax year
	 * @param from    starting range
	 * @param to      end range
	 * @param req     the HTTP request
	 * @return the list of properties in the specified range
	 */
	@GET
	@Path("getAppraisalDataInAssessedRange/{taxYear}/{fromValue}/{toValue}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAppraisalDataInAssessedRange(
			@PathParam("taxYear") String taxYear,
			@PathParam("fromValue") Double from,
			@PathParam("toValue") Double to,
			@Context HttpServletRequest req) {
		try {
			List<AppraisalData> list = appraisal.getAppraisalDataByAssessedRange(taxYear, from, to);
			return Response.ok(new ListResponse<>(list)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}

	/**
	 * Get the tax breakdown for a property.
	 * <p>
	 * @param year what year
	 * @param pid  the property identifier
	 * @param type tax breakdown type (full, homestead)
	 * @param req  the HTTP request
	 * @return the tax break down map
	 */
	@GET
	@Path("getTaxBreakdown/{year}/{pid}/{type}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getTaxBreakdown(
			@PathParam("year") String year,
			@PathParam("pid") String pid,
			@PathParam("type") String type,
			@Context HttpServletRequest req) {
		try {
			Map<String, Double> map = appraisal.getTaxBreakdown(year, pid, type);
			return Response.ok(new MapResponse<>(map)).build();
		} catch (Exception e) {
			return Response.serverError().entity(MessageResponse.error(e)).build();
		}
	}
}
