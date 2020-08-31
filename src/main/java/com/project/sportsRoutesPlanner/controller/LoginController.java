package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String loginPage() {
        return "security/login";
    }
}
