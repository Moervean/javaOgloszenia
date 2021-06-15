package main.service;

import main.dao.UserDao;
import main.model.Category;
import main.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login).orElse(null);
    }

    @Override
    public boolean verify(String login, String password) {
        User u = userDao.findByLogin(login).orElse(null);
        return u != null ? password.equals(u.getPassword()) : false;

    }

    @Override
    public void addUser(String login, String password, String email) {
        userDao.save(new User(login,password,email));
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
