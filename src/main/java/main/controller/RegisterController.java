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
import java.util.logging.Logger;

@Named
@ViewScoped
public class RegisterController implements Serializable {

    private final static Logger log = Logger.getLogger(RegisterController.class.getName());

    @Inject
    private UserBean userBean;

    @EJB
    private UserService userService;

    private String login;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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


    public void onRegister() throws IOException, ServletException {

        if(userService.findByLogin(login) == null){
            userService.addUser(login,password,email);
            JSF.addInfoMessage("Użytkownik zarejestrowany poprawnie");

            userBean.setLogin(login);
            userBean.setRank(userService.findByLogin(login).getUserRank());
            log.info("Zarejestrowano");
            JSF.redirect("index.xhtml");
        }else{
            log.warning("Login zajęty");
            JSF.addErrorMessage("Login zajęty");
        }
    }


}
