package com.orion.controller;

import com.orion.model.Applicant;
import com.orion.model.User;
import com.orion.service.ApplicantService;
import com.orion.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Vova on 02.06.2017.
 */

@RestController
public class ApplicantRestController {

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(value="/findApplicant/{applicantId}", method = RequestMethod.GET)
    @ResponseBody
    public Applicant findApplicant(@PathVariable("applicantId") Integer applicantId){
        Applicant applicant = applicantService.getApplicantById(applicantId);
        return applicant;
    }

    @RequestMapping(path="/applicants", method = RequestMethod.GET)
    public List<Applicant> getAllEmployees(){
        return applicantService.listApplicant();
    }

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userManagementService.userList();
    }
}
