package com.orion.controller;


import com.orion.service.ApplicantService;
import com.orion.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        return "home";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
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

    /*@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeTile(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.put("applicant", new Applicant());
        model.put("applicantList", applicantService.listApplicant());
        model.put("userName", currentPrincipalName);

        return "home";
    }*/

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsTile(ModelMap model) {
        return "settings";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileTile(ModelMap model) {
        return "profile";
    }
}
