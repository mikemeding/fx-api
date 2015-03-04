package com.uml.fx.entities;

import com.uml.fx.json.JSONArray;

import javax.ejb.Local;

/**
 * Created by mike on 3/4/15.
 */
@Local
public interface ContactService {

    boolean addContact(Contact contact);

    boolean deleteContact(Contact contact);

    boolean deleteContact(long id);

    boolean editContact(Contact contact);

    JSONArray getAll();

}
