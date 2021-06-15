package main.controller.admin;

import main.model.User;
import main.model.UserGroup;
import main.model.UserRank;
import main.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class UserController implements Serializable {
    @EJB
    private UserService userService;
    private List<User> users;
    private User editedUser;
    private UserRank[] ranks;

    @PostConstruct
    private void init(){
        users = userService.findAll();
        if(users == null)
            users = new ArrayList<>();

        ranks = UserRank.values();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getEditedUser() {
        return editedUser;
    }

    public void setEditedUser(User editedUser) {
        this.editedUser = editedUser;
    }

    public UserRank[] getRanks() {
        return ranks;
    }

    public void setRanks(UserRank[] ranks) {
        this.ranks = ranks;
    }

    public void onEditUser(User user){
        editedUser = user;
    }

    public void onRemoveUser(User user) {
        userService.delete(user.getId());
        users.remove(user);
    }

    public void onSaveUser(){
        if(editedUser.getId() == null){
            users.add(editedUser);
        }

        User saved = userService.save(editedUser);
        users.replaceAll(a-> a != editedUser ? a : saved);

        editedUser = null;
    }

    public void onCancelUser(){
        users.replaceAll(a-> a != editedUser ? a : userService.findById(editedUser.getId()));

        editedUser = null;
    }


}
