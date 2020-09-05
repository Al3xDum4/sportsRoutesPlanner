package com.project.sportsRoutesPlanner.controller;

import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "security/login";
    }
}
