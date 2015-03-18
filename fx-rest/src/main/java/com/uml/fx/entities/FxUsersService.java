/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.entities;

import com.uml.fx.json.JSONArray;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * @author mike
 */
@Local
public interface FxUsersService {

    void addNewUser(String username,
                    String password,
                    String name,
                    String email,
                    Date created,
                    int active,
                    int can_edit_pages);

    boolean deleteUser(String id);

    boolean updateUser(FxUser user);

    JSONArray selectAllActive();

    boolean isValid(String id);

    boolean authenticate(String username, String password);

    String getSchema();
}
