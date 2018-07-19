package com.codedito.controller;

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
        return "login_page";
    }

    @RequestMapping("/main")
    public String showUserPage(ModelMap model) {
        return "protected/main";
    }
    
    @RequestMapping("/forgot_password")
    public String showForgotPasswordForm(ModelMap model) {
        return "forgot_password";
    }

}
