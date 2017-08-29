package com.orion.controller;

import com.orion.model.User;
import com.orion.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Vova on 29.08.2017.
 */
@RestController
public class UsersRestController {

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userManagementService.userList();
    }
}
