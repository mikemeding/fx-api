package com.uml.fx.entities;

import com.uml.fx.json.JSONArray;
import com.uml.fx.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by mike on 3/4/15.
 */
@Local
public interface ContactService {

    boolean addContact(Contact contact);

    boolean deleteContact(long id);

    boolean editContact(Contact contact);

    JSONArray selectAll();

    JSONObject selectById(long id);

    JSONObject selectByName(String name);

}
