/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.service;

import main.model.User;

public interface UserService {
    public User findByLogin(String login);
    public boolean verify(String login, String password);
    void addUser(String login,String password,String email);
}
