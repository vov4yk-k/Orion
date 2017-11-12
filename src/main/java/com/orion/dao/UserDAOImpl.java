package com.orion.dao;

import com.orion.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public User getUserByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("name",name)).uniqueResult();
        return user;
    }

    @Override
    public void addUser(User user) {
        //sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void deleteUser(User user) {
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public void updateUser(User user) {
        //sessionFactory.getCurrentSession().update(user);
        sessionFactory.getCurrentSession().merge(user);
    }

}
