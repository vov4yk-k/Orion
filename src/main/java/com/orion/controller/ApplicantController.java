package com.orion.controller;


import com.orion.model.User;
import com.orion.service.ApplicantService;
import com.orion.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @RequestMapping("/index")
    public String listApplicant(Map<String, Object> map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        map.put("applicant", new Applicant());
        map.put("applicantList", applicantService.listApplicant());
        map.put("userName", currentPrincipalName);

        return "applicant";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addApplicant(@ModelAttribute("applicant") Applicant applicant, BindingResult result) {
        applicantService.addApplicant(applicant);
        return "redirect:/index";
    }

    @RequestMapping("/delete/{applicantId}")
    public String deleteApplicant(@PathVariable("applicantId") Integer applicantId) {
        applicantService.removeApplicant(applicantId);
        return "redirect:/index";
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
