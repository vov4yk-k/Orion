package com.orion.service;

import com.orion.model.Group;
import com.orion.model.GroupMember;
import com.orion.model.User;

import java.util.List;

/**
 * Created by Vova on 13.05.2017.
 */
public interface UserManagementService {

    List<User> userList();
    List<Group> groupList();
    List<GroupMember> groupMemberList();

    User getUserById(String id);
    User getUserByName(String name);
    GroupMember getGroupMemberById(Integer id);

    void addUser(User user);
    void addGroupMember(GroupMember groupMember);

    void deleteUser(String id);
    void deleteGroupMember(Integer id);
    void updateUser(User user);
}
