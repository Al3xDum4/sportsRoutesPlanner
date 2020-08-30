package com.project.sportsRoutesPlanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showHomePage() {
        return "homepage/showhomepage";
    }
}
