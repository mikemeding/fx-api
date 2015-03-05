package com.uml.fx.rest;

import com.uml.fx.entities.Contact;
import com.uml.fx.entities.ContactService;
import com.uml.fx.entities.News;
import com.uml.fx.json.DefaultJSONFactory;
import com.uml.fx.json.JSONException;
import com.uml.fx.json.JSONObject;
import com.uml.fx.response.GenericResponse;
import org.hibernate.dialect.SybaseAnywhereDialect;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mike on 3/4/15.
 */
@Path("contact")
public class ContactRest {

    private static final Logger log = Logger.getLogger(ContactRest.class.getName());

    private final DefaultJSONFactory JSONFactory = DefaultJSONFactory.getInstance();

    @EJB
    private ContactService contact;


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
        return Response.ok("Contact PONG", MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("addContact")
    @Produces({MediaType.TEXT_PLAIN})
    public Response addContact(@Context HttpServletRequest req
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

            // pull out all fields like login.
            String name = jo.getString("name");
            String email = jo.getString("email");
            String message = jo.getString("message");
            int refundAmount = jo.getInt("refundAmount");
            Date date = new Date(System.currentTimeMillis());

            // Actually add new contact to database
            Contact newContact = new Contact(name, email, message, refundAmount, date);
            contact.addContact(newContact);

            // If all goes well
            return Response.ok().build();

        } catch (IOException | JSONException e) {
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }

    @POST
    @Path("removeContact")
    @Produces({MediaType.TEXT_PLAIN})
    public Response removeContact(@Context HttpServletRequest req
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

            // pull out all fields like login.
            String id = jo.getString("id");

            // Remove matching user from database if it exists
            contact.deleteContact(Integer.valueOf(id));

            // If all goes well
            return Response.ok().build();

        } catch (IOException | JSONException e) {
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }


    @GET
    @Path("selectAll")
    @Produces({MediaType.TEXT_PLAIN})
    public Response selectAll(@Context HttpServletRequest req
    ) {
        log.info("getting all contacts");
        return Response.ok(contact.selectAll().toString(), MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("getById")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getById(@Context HttpServletRequest req
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

            String id = jo.getString("id");

            // actually add the news
            JSONObject returnJo = contact.selectById(Integer.valueOf(id));
            return Response.ok(returnJo.toString(),MediaType.TEXT_PLAIN).build();

        } catch(IOException | JSONException e){
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }

    @POST
    @Path("getByName")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getByName(@Context HttpServletRequest req
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

            String name = jo.getString("name");

            // actually add the news
            JSONObject returnJo = contact.selectByName(name);
            return Response.ok(returnJo.toString(),MediaType.TEXT_PLAIN).build();

        } catch(IOException | JSONException e){
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }
}
