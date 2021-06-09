/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import main.bean.UserBean;
import main.service.UserService;
import main.util.JSF;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginController implements Serializable {

    @Inject
    private UserBean userBean;

    @EJB
    private UserService userService;

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // akcja logowania
    public void onLogin() throws IOException, ServletException {
        if (userService.verify(login, password)) {
            userBean.setLogin(login);
            JSF.redirect("index.xhtml");
        } else {
            JSF.addErrorMessage("Niepoprawne dane");
        }
    }
    // akcja wylogowania
    public void onLogout() throws IOException {
        JSF.invalidateSession();
        JSF.redirect("index.xhtml");
    }

}
