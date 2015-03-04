package com.uml.fx.entities;

import com.uml.fx.json.JSONArray;
import com.uml.fx.json.JSONObject;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mike on 3/4/15.
 */
@Stateless
@Local(ContactService.class)
public class ContactServiceImpl implements ContactService {

    @PersistenceContext(unitName = "fxPU")
    private EntityManager em;

    @Override
    public boolean addContact(Contact contact) {
        return false;
    }

    @Override
    public boolean deleteContact(Contact contact) {
        return false;
    }

    @Override
    public boolean deleteContact(long id) {
        return false;
    }

    @Override
    public boolean editContact(Contact contact) {
        return false;
    }

    @Override
    public JSONArray getAll() {
        TypedQuery<Contact> query = em.createNamedQuery(Contact.SELECT_ALL, Contact.class);
        List<Contact> contactList = query.getResultList();
        JSONArray jsonArray = new JSONArray();
        for (Contact contactItem : contactList) {
            JSONObject jo = new JSONObject();
            jo.append("name", contactItem.getName());
            jo.append("email", contactItem.getEmail());
            jo.append("message", contactItem.getMessage());
            jo.append("refundAmount", (String.valueOf(contactItem.getRefundAmount())));
            jo.append("date", contactItem.getDate().toString());
            jsonArray.add(jo);
        }
        return jsonArray;
    }
}
