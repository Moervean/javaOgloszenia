/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.service;

import main.model.Category;
import main.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByLogin(String login);
    boolean verify(String login, String password);
    void addUser(String login,String password,String email);
    User save(User user);
    void delete(Long id);
    List<User> findAll();

}
