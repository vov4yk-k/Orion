package com.orion.controller;


import com.orion.model.User;
import com.orion.service.ApplicantService;
import com.orion.model.Applicant;
import com.orion.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@Controller
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private SessionLocaleResolver sessionLocaleResolver;

    @RequestMapping("/index")
    public String listApplicant(Map<String, Object> map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userManagementService.getUserByName(currentPrincipalName);
        sessionLocaleResolver.setDefaultLocale(user.getLocale());

        map.put("applicant", new Applicant());
        map.put("applicantList", applicantService.listApplicant());
        map.put("userName", currentPrincipalName);
        map.put("recruiters", userManagementService.userList());
        map.put("currentItem", "home");

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
        return "redirect:/index";
    }

    @RequestMapping(value = "/postApplicant", method = RequestMethod.POST)
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
        dateFormat.setTimeZone(TimeZone.getTimeZone("EEST"));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsTile(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.put("userName", authentication.getName());
        model.put("currentItem", "settings");
        return "settings";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileTile(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.put("userName", authentication.getName());
        model.put("user", userManagementService.getUserByName(authentication.getName()));
        model.put("currentItem", "profile");
        return "profile";
    }

    @RequestMapping(value = "/prifileUpdate", method = RequestMethod.POST)
    public String userUpdate(@ModelAttribute(value="user") @Validated User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
        } else {
            userManagementService.updateUser(user);
        }
        return "redirect:/profile";
    }
}
