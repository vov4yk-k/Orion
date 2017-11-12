package com.orion.dao;

import com.orion.model.User;

import java.util.List;

/**
 * Created by Vova on 13.05.2017.
 */
public interface UserDAO {
    public List<User> userList();
    public User getUserByID(String id);
    public User getUserByName(String name);
    public void addUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);
}
