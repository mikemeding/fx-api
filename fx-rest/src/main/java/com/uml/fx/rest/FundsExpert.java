package com.uml.fx.rest;

import com.uml.fx.response.GenericResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import os.util.json.DefaultJSONFactory;
import os.util.json.JSONObject;

/**
 *
 * ==========================================================================
 * FundsXpert
 *
 * Graphical User Interface Programming II Professor Jessie Heines, Michael
 * Meding & Jose Flores 2015-02-12
 *
 * This is the main RESTful API class which controls our main page and all its
 * contents
 * ==========================================================================
 *
 * @author mike
 */
@Path("fx")
public class FundsExpert {

	private static final Logger log = Logger.getLogger(FundsExpert.class.getName());

	private final DefaultJSONFactory JSONFactory = DefaultJSONFactory.getInstance();

	private final List<JSONObject> userList = new ArrayList<>();

	public FundsExpert() {
		JSONObject jo = JSONFactory.jsonObject("{username:\"mike\",password:\"ccaes1\"}");
		this.userList.add(jo);
		JSONObject jo2 = JSONFactory.jsonObject("{username:\"root\",password:\"admin\"}");
		this.userList.add(jo2);
	}

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
		return Response.ok("PONG", MediaType.TEXT_PLAIN).build();
	}

	/**
	 * A quick and dirty login function with no security
	 *
	 * @param req
	 * @return
	 */
	@POST
	@Path("login")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response login(@Context HttpServletRequest req
	) {
		StringBuilder sb = new StringBuilder();

		try {
			String line;

			// parse input stream into a string
			try (BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			}
			String data = sb.toString();

			log.info(data);

			// parse string to json object
			JSONObject jo = JSONFactory.jsonObject(data);

			// LOL authorize
			if (userList.contains(jo)) {
				return Response.ok().entity(GenericResponse.OK).build();
			} else {
				return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
			}

		} catch (Exception e) { // parse error
			log.log(Level.SEVERE, e.getMessage());
			return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
		}

	}
}