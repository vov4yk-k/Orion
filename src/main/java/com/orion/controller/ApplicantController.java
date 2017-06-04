package com.orion.controller;


import com.orion.service.ApplicantService;
import com.orion.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @RequestMapping("/index")
    public String listApplicant(Map<String, Object> map) {

        map.put("applicant", new Applicant());
        map.put("applicantList", applicantService.listApplicant());
        
        return "applicant";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addApplicant(@ModelAttribute("applicant") Applicant applicant,
                             BindingResult result) {

        applicantService.addApplicant(applicant);

        return "redirect:/index";
    }

    @RequestMapping("/delete/{applicantId}")
    public String deleteApplicant(@PathVariable("applicantId") Integer applicantId) {

        applicantService.removeApplicant(applicantId);

        return "redirect:/index";
    }



}
