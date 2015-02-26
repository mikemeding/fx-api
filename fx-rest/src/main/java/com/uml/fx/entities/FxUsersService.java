/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

/**
 *
 * @author mike
 */
public interface FxUsersService {
	/**
	 * Create a new entry in the password table for a given userId
	 * @param userId
	 * @param passwd 
	 */
	void createUserPasswd(int userId, String passwd);
	
}
