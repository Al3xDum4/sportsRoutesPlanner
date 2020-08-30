package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GuideController {

    @Autowired
    private UserService userService;

    @GetMapping("allguides")
    public String showAllGuides(Model model) {
        List<User> guidesList = userService.findUserByRole();
        model.addAttribute("guides", guidesList);
        return "guide/showallguides";
    }

    @GetMapping("addguide")
    public String addGuide(Model model){
        model.addAttribute("guide",new User());
        return "guide/addguide";
    }

    @PostMapping("addguide")
    public String addGuide(@ModelAttribute User guide) {
        userService.save(guide);
        return "redirect:/allguides";
    }

}
