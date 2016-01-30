/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamysql.database;

import javamysql.model.User;

/**
 *
 * @author Filippo-TheAppExpert
 */
public interface ICrud {
    
    boolean insert(User user);
    
    User getUser(String userName, String password);
}
