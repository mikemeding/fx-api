package com.uml.fx.rest;

import com.uml.fx.entities.ContactService;
import com.uml.fx.json.DefaultJSONFactory;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
}
