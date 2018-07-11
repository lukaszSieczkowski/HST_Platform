package com.codedito.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }


    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @RequestMapping("protected/user/userpage")
    public String showUserPage(ModelMap model) {
        model.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        return "protected/user/user_page";
    }

    @RequestMapping("protected/admin/adminpage")
    public String showAdminPage(ModelMap model) {
        model.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        return "protected/admin/admin_page";
    }
}
