package com.uml.fx.rest;

import com.uml.fx.entities.FxUsersService;
import com.uml.fx.json.JSONArray;
import com.uml.fx.json.JSONException;
import com.uml.fx.response.GenericResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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

import com.uml.fx.json.DefaultJSONFactory;
import com.uml.fx.json.JSONObject;
import com.uml.fx.response.ListResponse;
import com.uml.fx.response.MessageResponse;

import java.util.Date;
import javax.ejb.EJB;

/**
 * ==========================================================================
 * FundsXpert
 * <p/>
 * Graphical User Interface Programming II Professor Jessie Heines, Michael
 * Meding & Jose Flores 2015-02-12
 * <p/>
 * This is the main RESTful API class which controls our main page and all its
 * contents
 * ==========================================================================
 *
 * @author mike
 */
@Path("user")
public class FundsExpert {

    private static final Logger log = Logger.getLogger(FundsExpert.class.getName());

    private final DefaultJSONFactory JSONFactory = DefaultJSONFactory.getInstance();

    @EJB
    private FxUsersService users;

    /**
     * Simply returns 200 OK if called.
     * <p/>
     *
     * @param req
     * @return
     */
    @GET
    @Path("ping")
    @Produces({MediaType.TEXT_PLAIN})
    public Response ping(@Context HttpServletRequest req
    ) {
        return Response.ok("user PONG", MediaType.TEXT_PLAIN).build();
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

        try {
            JSONObject jo = parseJSONStream(req);
            // LOL authorize
            if (users.authenticate(jo.optString("username"), jo.optString("password"))) {
                return Response.ok().entity(GenericResponse.OK).build();
            } else {
                return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
            }
        } catch (JSONException e) {
            return Response.serverError().entity(MessageResponse.info("JSON parsing error")).build();
        }
    }

    /**
     * Add new user to the database. All passwords and such are cleartext
     *
     * @param req
     * @return
     */
    @POST
    @Path("addUser")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response addFxUser(@Context HttpServletRequest req
    ) {
        try {
            // parse input JSON stream
            JSONObject jo = parseJSONStream(req);

            // pull out all fields like login.
            String username = jo.getString("username");
            String password = jo.getString("password");
            String name = jo.getString("name");
            String email = jo.getString("email");
            Date created = new Date(System.currentTimeMillis()); // creation date is today
            int active = 1;
            int can_edit_pages = 1;

            // attempt to add user to database
            // TODO: This needs to throw exception.
            users.addNewUser(username, password, name, email, created, active, can_edit_pages);

            // All went according to plan.
            return Response.ok(GenericResponse.OK, MediaType.TEXT_PLAIN).build();
        } catch (JSONException e) {
            return Response.serverError().build();
        }

    }

    @POST
    @Path("deleteUser")
    @Produces({MediaType.TEXT_PLAIN})
    public Response deleteUser(@Context HttpServletRequest req
    ) {
        // parse incomming stream
        JSONObject jo = parseJSONStream(req);

        // preform delete and check return status
        if (users.deleteUser(jo.optString("id"))) {
            return Response.ok("true", MediaType.TEXT_PLAIN).build();
        } else {
            return Response.serverError().build();
        }

    }


    /**
     * Queries and returns as a JSONArray all the active users in the database
     *
     * @param req
     * @return JSONArray
     */
    @GET
    @Path("getAllActiveUsers")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getAllActiveUsers(@Context HttpServletRequest req
    ) {
        log.info("getting active FxUsers");
        return Response.ok(users.selectAllActive().toString(), MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("isRegistered")
    @Produces({MediaType.TEXT_PLAIN})
    public Response isRegistered(@Context HttpServletRequest req
    ) {
        log.info("checking if user is registered");
        JSONObject jo = parseJSONStream(req);
        // quick and dirty
        JSONArray joa = users.selectAllActive();
        for (int x = 0; joa.length() > x; x++) {
            if (joa.getJSONObject(x).optString("username").equals(jo.optString("username"))) {
                return Response.serverError().build();
            }
        }
        return Response.ok("true", MediaType.TEXT_PLAIN).build();
    }

    /**
     * A util function to parse an input JSON stream and parses to a JSONObject
     *
     * @param req
     * @return
     */
    private JSONObject parseJSONStream(HttpServletRequest req) {
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

            return jo;

        } catch (IOException | JSONException e) {
            log.severe(e.getMessage());
            throw new JSONException("Stream parse error.");
        }

    }

}
