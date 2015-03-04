/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

import com.uml.fx.json.JSONArray;
import com.uml.fx.json.JSONObject;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * @author mike
 */
@Stateless
@Local(FxUsersService.class)
public class FxUsersServiceImpl implements FxUsersService {

    @PersistenceContext(unitName = "fxPU")
    private EntityManager em;

    private static final Logger log = getLogger(FxUsersService.class.getName());

    public FxUsersServiceImpl() {
    }

    public FxUsersServiceImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates a new user and adds it to the database. all fields must exist to add a user.
     *
     * @param username
     * @param password
     * @param name
     * @param email
     * @param created
     * @param active         either 1 or 0 for active and inactive
     * @param can_edit_pages same as active
     */
    @Override
    public void addNewUser(String username, String password, String name, String email, Date created, int active, int can_edit_pages) {
        FxUser testUser = new FxUser(username, password, name, email, created, active, can_edit_pages);
        em.persist(testUser);
    }

    /**
     * Removes the given user from the database
     *
     * @param username
     * @return false if user does not exist or if call fails
     */
    @Override
    public boolean deleteUser(String username) {
        TypedQuery<FxUser> query = em.createNamedQuery(FxUser.SELECT_BY_USERNAME, FxUser.class);
        FxUser delUser = query.setParameter("username", username).getSingleResult();

        if (delUser == null) {
            return false;
        } else {
            em.remove(delUser);
            return true;
        }

    }

    /**
     * Performs named query to select all users from the database and return only the active ones.
     *
     * @return a JSONArray of all users found in the database.
     */
    @Override
    public JSONArray selectAllActive() {
        TypedQuery<FxUser> query = em.createNamedQuery(FxUser.SELECT_ALL_ACTIVE, FxUser.class);
        JSONArray result = new JSONArray();

        // this forces lazy evaluation to get all fields
        for (FxUser user : query.getResultList()) {
            // create and extract needed fields
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("active", user.getActive());
            jsonObject.put("created", user.getCreated());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("name", user.getName());
            jsonObject.put("can_edit_pages", user.getCan_edit_pages());
            jsonObject.put("id", user.getId());
            // add to our array
            result.add(jsonObject);
        }
        return result;

    }


    @Override
    public boolean isValid(String username) {
        TypedQuery<FxUser> query = em.createNamedQuery(FxUser.SELECT_BY_USERNAME, FxUser.class);
        FxUser validUser = query.setParameter("username", username).getSingleResult();

        // if we get anything back then it must exist
        if (validUser == null) {
            return false;
        } else {
            if (validUser.getActive() == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        TypedQuery<FxUser> query = em.createNamedQuery(FxUser.AUTHENTICATE, FxUser.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        FxUser authenticatedUser = query.getSingleResult();

        if (authenticatedUser == null) {
            return false;
        } else {
            if (authenticatedUser.getActive() == 0) {
                return false;
            } else {
                return true;
            }
        }

    }

    @Override
    public String getSchema() {
//        Query query = em.createNativeQuery("DESCRIBE " + FxUser.TABLENAME);
//        List<String> schema = query.getResultList();
//        ListIterator iterator = schema.listIterator();
//        while(iterator.hasNext()){
//            Object obj = iterator.next(); // move to next object
//            String blah = obj;
//
//
//            log.info(blah.toString());
//        }


//        log.info(schema.get(0).toString());
//        return schema;
        return "Ill fix if i have time";
    }
}
