/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

import java.util.Date;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mike
 */
@Stateless
@Local(FxUsersService.class)
public class FxUsersServiceImpl implements FxUsersService {

	@PersistenceContext(unitName = "fxPU")
	private EntityManager em;

	public FxUsersServiceImpl() {
	}

	public FxUsersServiceImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void createTestUser(String username, String password, String name, String email, Date created, int active, int can_edit_pages) {	
		FxUsers testUser = new FxUsers(username, password, name, email, created, active, can_edit_pages);
		em.persist(testUser);
	}
	
	

	

}
