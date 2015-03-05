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
        em.persist(contact);
        return true;
    }

    @Override
    public boolean deleteContact(long id) {
        TypedQuery<Contact> query = em.createNamedQuery(Contact.SELECT_BY_ID, Contact.class);
        query.setParameter("id", id);
        Contact contact = query.getSingleResult();
        em.remove(contact);
        return false;
    }

    @Override
    public boolean editContact(Contact contact) {
        em.merge(contact);
        return false;
    }

    @Override
    public JSONArray selectAll() {
        TypedQuery<Contact> query = em.createNamedQuery(Contact.SELECT_ALL, Contact.class);
        List<Contact> contactList = query.getResultList();
        JSONArray jsonArray = new JSONArray();
        for (Contact contactItem : contactList) {
            JSONObject jo = new JSONObject();
            jo.put("id", contactItem.getId());
            jo.put("name", contactItem.getName());
            jo.put("email", contactItem.getEmail());
            jo.put("message", contactItem.getMessage());
            jo.put("refundAmount", (String.valueOf(contactItem.getRefundAmount())));
            jo.put("date", contactItem.getDate().toString());
            jsonArray.add(jo);
        }
        return jsonArray;
    }

    @Override
    public JSONObject selectById(long id) {
        TypedQuery<Contact> query = em.createNamedQuery(Contact.SELECT_BY_ID, Contact.class);
        query.setParameter("id", id);
        Contact contact = query.getSingleResult();

        JSONObject jo = new JSONObject();
        jo.put("id", contact.getId()); //expose database generated id
        jo.put("name", contact.getName());
        jo.put("email", contact.getEmail());
        jo.put("refundAmount", contact.getRefundAmount());
        jo.put("date", contact.getDate().toString());

        return jo;
    }

    @Override
    public JSONObject selectByName(String name) {
        TypedQuery<Contact> query = em.createNamedQuery(Contact.SELECT_BY_NAME, Contact.class);
        query.setParameter("name", name);
        Contact contact = query.getSingleResult();

        JSONObject jo = new JSONObject();
        jo.put("id", contact.getId()); //expose database generated id
        jo.put("name", contact.getName());
        jo.put("email", contact.getEmail());
        jo.put("refundAmount", contact.getRefundAmount());
        jo.put("date", contact.getDate().toString());

        return jo;
    }
}
