package main.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NamedQuery(name="User.findByLogin",query = "select u from User u where u.login=:login")
@Entity
public class User extends AbstractModel {
    @Column(unique = true)
    private String login;
    private String password;
    private String name;
    @Column(unique = true)
    private String email;
    private UserRank userRank;
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserGroup> userGroups = new LinkedList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ad> accountOperations = new LinkedList<Ad>();

    public List<Ad> getAccountOperations() {
        return accountOperations;
    }

    public void setAccountOperations(List<Ad> accountOperations) {
        this.accountOperations = accountOperations;
    }

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        userRank = UserRank.USER;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRank getUserRank() {
        return userRank;
    }

    public void setUserRank(UserRank userRank) {
        this.userRank = userRank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void addGroup(String name) {
        // sprawdzic czy już nie jest dodana
        for (UserGroup us : userGroups) {
            if (us.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        userGroups.add(new UserGroup(name,this));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", password='****'" +
                ", email='" + email + '\'' +
                '}';
    }
}
