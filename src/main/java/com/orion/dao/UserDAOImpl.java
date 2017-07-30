package com.orion.dao;

import com.orion.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vova on 13.05.2017.
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> userList() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }

    @Override
    public User getUserByID(String id) {
        User user = (User) sessionFactory.getCurrentSession().load(
                User.class, id);
        return user ;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = getUserByID(id);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
