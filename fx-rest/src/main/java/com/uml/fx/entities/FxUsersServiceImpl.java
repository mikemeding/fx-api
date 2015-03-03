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

/**
 * @author mike
 */
@Stateless
@Local(FxUsersService.class)
public class FxUsersServiceImpl implements FxUsersService {

    @PersistenceContext(unitName = "fxPU")
    private EntityManager em;

    public FxUsersServiceImpl() {
    }

    public FxUsersServiceImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates a new user and adds it to the database. all fields must exist to add a user.
     * @param username
     * @param password
     * @param name
     * @param email
     * @param created
     * @param active either 1 or 0 for active and inactive
     * @param can_edit_pages same as active
     */
    @Override
    public void addNewUser(String username, String password, String name, String email, Date created, int active, int can_edit_pages) {
        FxUser testUser = new FxUser(username, password, name, email, created, active, can_edit_pages);
        em.persist(testUser);
    }

    /**
     * Performs named query to select all users from the database and return only the active ones.
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
}
