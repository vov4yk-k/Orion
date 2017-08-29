package com.orion.controller;

import com.orion.model.Applicant;
import com.orion.model.User;
import com.orion.service.ApplicantService;
import com.orion.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Vova on 02.06.2017.
 */

@RestController
public class ApplicantRestController {

    @Autowired
    private ApplicantService applicantService;

    @RequestMapping(value="/findApplicant/{applicantId}", method = RequestMethod.GET)
    @ResponseBody
    public Applicant findApplicant(@PathVariable("applicantId") Integer applicantId){
        Applicant applicant = applicantService.getApplicantById(applicantId);
        return applicant;
    }

    @RequestMapping(path = "/applicants", method = RequestMethod.GET)
    public List<Applicant> getAllApplicants(){
        return applicantService.listApplicant();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EEST"));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
