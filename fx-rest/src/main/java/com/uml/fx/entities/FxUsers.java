/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * +----------------+--------------+------+-----+---------+-------+ | Field |
 * Type | Null | Key | Default | Extra |
 * +----------------+--------------+------+-----+---------+-------+ | id |
 * bigint(20) | NO | PRI | NULL | | | username | varchar(50) | YES | | NULL | |
 * | password | varchar(50) | YES | | NULL | | | name | varchar(255) | YES | |
 * NULL | | | email | varchar(255) | YES | | NULL | | | created | datetime | YES
 * | | NULL | | | active | tinyint(4) | NO | | NULL | | | can_edit_pages |
 * tinyint(4) | NO | | NULL | |
 * +----------------+--------------+------+-----+---------+-------+
 *
 * @author mike
 */
@Entity
@Table(name = "users")
public class FxUsers implements Serializable {

	long id;
	String username;
	String password;
	String name;
	String email;
	Date created;
	int active;
	int can_edit_pages;

	public FxUsers() {
	}

	public FxUsers(String username, String password, String name, String email, Date created, int active, int can_edit_pages) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.created = created;
		this.active = active;
		this.can_edit_pages = can_edit_pages;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}		

	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="created")
	@Temporal(TemporalType.DATE)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name="active")
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Column(name="can_edit_pages")
	public int getCan_edit_pages() {
		return can_edit_pages;
	}

	public void setCan_edit_pages(int can_edit_pages) {
		this.can_edit_pages = can_edit_pages;
	}

}
