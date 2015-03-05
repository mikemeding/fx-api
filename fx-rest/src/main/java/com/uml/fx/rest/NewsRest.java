package com.uml.fx.rest;

import com.uml.fx.entities.News;
import com.uml.fx.entities.NewsService;
import com.uml.fx.json.DefaultJSONFactory;
import com.uml.fx.json.JSONException;
import com.uml.fx.json.JSONObject;
import com.uml.fx.response.GenericResponse;

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
@Path("news")
public class NewsRest {

    private static final Logger log = Logger.getLogger(NewsRest.class.getName());

    private final DefaultJSONFactory JSONFactory = DefaultJSONFactory.getInstance();

    @EJB
    private NewsService news;

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
        return Response.ok("News PONG", MediaType.TEXT_PLAIN).build();
    }

    /**
     * Adds the news article to the database
     * @param req
     * @return
     */
    @POST
    @Path("addNews")
    @Produces({MediaType.TEXT_PLAIN})
    public Response addNews(@Context HttpServletRequest req
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
            String title = jo.getString("title");
            String text = jo.getString("text");
            Date created = new Date(System.currentTimeMillis());
            String user = jo.getString("user");

            // actually add the news
            news.addNews(new News(title,text,created,user));
            return Response.ok().build();

        } catch(IOException | JSONException e){
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }

    /**
     * remove an article from the database
     * @param req
     * @return
     */
    @POST
    @Path("removeNews")
    @Produces({MediaType.TEXT_PLAIN})
    public Response removeNews(@Context HttpServletRequest req
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

            // actually removes matching news object
            news.deleteNews(Integer.valueOf(id));
            return Response.ok().build();

        } catch(IOException | JSONException e){
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }

    /**
     * Queries and returns as a JSONArray all the active users in the database
     *
     * @param req
     * @return JSONArray
     */
    @GET
    @Path("selectAll")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getAll(@Context HttpServletRequest req
    ) {
        log.info("getting news articles");
        return Response.ok(news.selectAll().toString(), MediaType.TEXT_PLAIN).build();
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
            JSONObject returnJo = news.selectById(Integer.valueOf(id));
            return Response.ok(returnJo.toString(),MediaType.TEXT_PLAIN).build();

        } catch(IOException | JSONException e){
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }

    @POST
    @Path("getByTitle")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getByTitle(@Context HttpServletRequest req
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

            String title = jo.getString("title");

            // actually add the news
            JSONObject returnJo = news.selectByTitle(title);
            return Response.ok(returnJo.toString(),MediaType.TEXT_PLAIN).build();

        } catch(IOException | JSONException e){
            // if any parsing fails.
            log.log(Level.SEVERE, e.getMessage());
            return Response.serverError().entity(GenericResponse.FAIL_STATUS).build();
        }
    }

}
