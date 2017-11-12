package com.orion.controller;

import com.orion.model.Group;
import com.orion.model.GroupMember;
import com.orion.model.User;
import com.orion.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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

    @RequestMapping(path = "/groupMembers", method = RequestMethod.GET)
    public List<GroupMember> getAllGroupMembers(){
        return userManagementService.groupMemberList();
    }

    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    public void addApplicant(@ModelAttribute("groupMember") GroupMember groupMember, HttpServletResponse response) throws IOException {
       userManagementService.addGroupMember(groupMember);
       response.sendRedirect("/settings");
    }

    @RequestMapping(value="/findGroupMember/{groupMemberId}", method = RequestMethod.GET)
    @ResponseBody
    public GroupMember findApplicant(@PathVariable("groupMemberId") Integer groupMemberId){
        GroupMember groupMember = userManagementService.getGroupMemberById(groupMemberId);
        return groupMember;
    }

    @RequestMapping(value="/deleteGroupMember/{groupMemberId}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteGroupMember(@PathVariable("groupMemberId") Integer groupMemberId, HttpServletResponse response) throws IOException{
        userManagementService.deleteGroupMember(groupMemberId);
        response.sendRedirect("/settings");
    }
}
