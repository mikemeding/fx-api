package com.uml.fx.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mike on 3/4/15.
 */
@Entity
@Table(name = Contact.TABLENAME,
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"id"})
)
@NamedQueries({
        @NamedQuery(
                name = Contact.SELECT_ALL,
                query = "SELECT a FROM Contact a"),
        @NamedQuery(
                name = Contact.SELECT_BY_ID,
                query = "SELECT a FROM Contact a WHERE a.id= :id"),
        @NamedQuery(
                name = Contact.SELECT_BY_NAME,
                query = "SELECT a FROM Contact a WHERE a.name= :name")
})
public class Contact implements Serializable {

    private long id;
    private String name;
    private String email;
    private String message;
    private int refundAmount;
    private Date date;

    public final static String TABLENAME = "contacts";
    public final static String SELECT_ALL = "Contact.selectAll";
    public final static String SELECT_BY_ID = "Contact.selectById";
    public final static String SELECT_BY_NAME = "Contact.selectByName";

    public Contact() {
    }

    public Contact(String name, String email, String message, int refundAmount, Date date) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.refundAmount = refundAmount;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "refundAmount")
    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
