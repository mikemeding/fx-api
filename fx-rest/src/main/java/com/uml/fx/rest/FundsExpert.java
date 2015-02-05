/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import javax.ws.rs.Path;

/**
 * 
 *
 * @author mike
 */
@Path("fx")
public class FundsExpert {

	private static final Logger log = Logger.getLogger(FundsExpert.class.getName());
	
	
	/**
	 * Simply returns 200 OK if called.
	 * <p>
	 * @param req
	 * @return
	 */
	@GET
	@Path("ping")
	@Produces({MediaType.TEXT_PLAIN})
	public Response ping(@Context HttpServletRequest req
	) {
		return Response.ok("{\"done\":\"ok\"}\n", MediaType.TEXT_PLAIN).build();
	}
}
