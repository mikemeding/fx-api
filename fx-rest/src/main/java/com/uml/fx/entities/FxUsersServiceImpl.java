/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

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
	public void createUserPasswd(int userId, String passwd) {
		FxUsers fxUser = new FxUsers(userId, passwd);
		em.persist(fxUser);
	}

}
