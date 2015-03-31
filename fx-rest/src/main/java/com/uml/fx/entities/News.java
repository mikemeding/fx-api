package com.uml.fx.entities;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 ==========================================================================
 FundsXpert

 Graphical User Interface Programming II
 Professor Jessie Heines
 Michael Meding & Jose Flores
 2015-02-12

 This is the News entity which handles the blog style news feed on the news
 page of fundsxpert
 ==========================================================================
 */
@Entity
@Table(name = News.TABLENAME,
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"id"})
)
@NamedQueries({
        @NamedQuery(
                name = News.SELECT_ALL,
                query = "SELECT a FROM News a"),
        @NamedQuery(
                name = News.SELECT_BY_ID,
                query = "SELECT a FROM News a WHERE a.id=:id"),
        @NamedQuery(
                name = News.SELECT_BY_TITLE,
                query = "SELECT a FROM News a WHERE a.title =:title")
})
public class News implements Serializable {

    public final static String TABLENAME = "news";
    public final static String SELECT_ALL = "News.selectAll";
    public final static String SELECT_BY_ID = "News.selectById";
    public final static String SELECT_BY_TITLE = "News.selectByTitle";

    private long id;
    private String title;
    private String text;
    private Date created;
    private String user;

    public News() {
    }

    public News(String title, String text, Date created, String user) {
        this.title = title;
        this.text = text;
        this.created = created;
        this.user = user;
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

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}