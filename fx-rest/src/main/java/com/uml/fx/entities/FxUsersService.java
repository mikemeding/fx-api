/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author mike
 */
@Local
public interface FxUsersService {

	/**
	 * Create a new entry in the password table for a given userId
	 *
	 * @param userId
	 * @param passwd
	 */
	void createTestUser(String username,
			  String password,
			  String name,
			  String email,
			  Date created,
			  int active,
			  int can_edit_pages);

}
