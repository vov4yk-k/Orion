package com.orion.service;

import com.orion.dao.GroupDAO;
import com.orion.dao.GroupMemberDAO;
import com.orion.dao.UserDAO;
import com.orion.dao.UserDAOImpl;
import com.orion.model.Group;
import com.orion.model.GroupMember;
import com.orion.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Vova on 28.07.2017.
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GroupMemberDAO groupMemberDAO;

    @Autowired
    private GroupDAO groupDAO;

    private ArrayList<Locale> supportedLocales = new ArrayList<Locale>() {{
        add(Locale.ENGLISH);
        add(Locale.forLanguageTag("uk"));
    }};

    @Override
    @Transactional
    public List<User> userList() {
        return userDAO.userList();
    }

    @Override
    @Transactional
    public List<Group> groupList() {
        return groupDAO.listGroup();
    }

    @Override
    @Transactional
    public List<GroupMember> groupMemberList() {
        return groupMemberDAO.listGroupMember();
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
    @Transactional
    public GroupMember getGroupMemberById(Integer id) {
        return groupMemberDAO.getGroupMemberByID(id);
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    @Transactional
    public void addGroupMember(GroupMember groupMember) {
        User user = groupMember.getUsername();
        if(user.newEntity())
            userDAO.addUser(user);
        else{
            userDAO.updateUser(user);
        }
        if(groupMember.newEntity()){
            groupMemberDAO.addGroupMember(groupMember);
        }else{
            groupMemberDAO.updateGroupMember(groupMember);
        }
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    @Transactional
    public void deleteGroupMember(Integer id) {
        GroupMember groupMember = getGroupMemberById(id);
        User user = groupMember.getUsername();
        groupMemberDAO.deleteGroupMember(groupMember);
        userDAO.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public HashMap<String, String> getLanguages(User user) {
        LinkedHashMap<String, String> languages = new LinkedHashMap<>();
        languages.put(user.getLanguage(),user.getLocale().getDisplayName());
        List<User> users = userDAO.userList();
        supportedLocales.forEach(currentLocale -> {
            languages.put(currentLocale.getLanguage(),currentLocale.getDisplayName());
        });
        return languages;
    }
}
