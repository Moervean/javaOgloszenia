package main.dao;

import main.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDaoImpl extends AbstractDaoJpaImpl<User> implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        return findSingle("User.findByLogin","login",login);
    }



}
