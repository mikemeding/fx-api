/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mike
 */
@Stateless
public class FxUsersServiceImpl implements FxUsersService {
	@PersistenceContext(unitName = "fxPU")
	private EntityManager em;
	
	
	@Override
	public void createUserPasswd(int userId, String passwd) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
}
