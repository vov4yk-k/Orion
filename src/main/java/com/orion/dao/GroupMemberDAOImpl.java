package com.orion.dao;

import com.orion.model.GroupMember;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vova on 13.05.2017.
 */
@Repository
public class GroupMemberDAOImpl implements GroupMemberDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<GroupMember> listGroupMember() {
        return sessionFactory.getCurrentSession().createQuery("from GroupMember").list();
    }

    @Override
    public void addGroupMember(GroupMember groupMember) {
        sessionFactory.getCurrentSession().persist(groupMember);
    }

    @Override
    public void updateGroupMember(GroupMember groupMember) {
        sessionFactory.getCurrentSession().merge(groupMember);
    }

    @Override
    public void deleteGroupMember(GroupMember groupMember) {
        sessionFactory.getCurrentSession().delete(groupMember);
    }

    @Override
    public GroupMember getGroupMemberByID(Integer id) {
        GroupMember groupMember = (GroupMember) sessionFactory.getCurrentSession().get(
                GroupMember.class, id);
        return groupMember;
    }

}
