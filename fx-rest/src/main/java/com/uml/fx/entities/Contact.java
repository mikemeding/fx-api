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
                query = "SELECT a FROM Contact a")
})
public class Contact implements Serializable {

    private long id;
    private String name;
    private String email;
    private String message;
    private int refundAmount;
    private Date date;

    public final static String TABLENAME = "contact";
    public final static String SELECT_ALL = "Contact.selectAll";

    public Contact(long id, String name, String email, String message, int refundAmount, Date date) {
        this.id = id;
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

    @Column(name = "refund-amount")
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
