package com.orion.dao;

import com.orion.model.Group;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vova on 13.05.2017.
 */
@Repository
public class GroupDAOImpl implements GroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Group> listGroup() {
        return sessionFactory.getCurrentSession().createQuery("from Group")
                .list();
    }
}
