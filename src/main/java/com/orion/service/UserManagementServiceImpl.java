package com.orion.service;

import com.orion.dao.UserDAO;
import com.orion.dao.UserDAOImpl;
import com.orion.model.Group;
import com.orion.model.GroupMember;
import com.orion.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vova on 28.07.2017.
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> userList() {
        return userDAO.userList();
    }

    @Override
    public List<Group> groupList() {
        return null;
    }

    @Override
    public List<GroupMember> groupMemberList() {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return userDAO.getUserByID(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public GroupMember getGroupMemberById(Integer id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addGroupMember(GroupMember groupMember) {

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public void deleteGroupMember(Integer id) {

    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
}
