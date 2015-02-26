/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * SCHEMA +---------------------+-------------+------+-----+---------+-------+ |
 * Field | Type | Null | Key | Default | Extra |
 * +---------------------+-------------+------+-----+---------+-------+ | list |
 * int(11) | YES | | NULL | | | list_active | int(11) | YES | | NULL | | |
 * list_can_edit_pages | int(11) | YES | | NULL | | | list_email | varchar(31) |
 * YES | | NULL | | | list_fullname | varchar(27) | YES | | NULL | | | list_id |
 * int(11) | YES | | NULL | | | list_username | varchar(10) | YES | | NULL | |
 * +---------------------+-------------+------+-----+---------+-------+
 *
 * @author mike
 */
@Entity
@Table(name = "fx_passwd")
@XmlRootElement
public class FxUsers implements Serializable {

	private int userId;
	private String passwd;

	public FxUsers() {
	}

	public FxUsers(int userId, String passwd) {
		this.userId = userId;
		this.passwd = passwd;
	}

	@Id
	public int getUserId() {
		return this.userId;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
