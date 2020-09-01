package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.Role;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GuideController {

    @Autowired
    private UserService userService;

    @GetMapping("allguides")
    public String showAllGuides(Model model) {
        List<User> guidesList = userService.findGuide();
        model.addAttribute("guides", guidesList);
        return "guide/showallguides";
    }

    @GetMapping("/addguide")
    public String addGuide(Model model) {
        model.addAttribute("guide", new User());
        return "guide/addguide";
    }

    @PostMapping("/addguide")
    public String addGuide(@ModelAttribute User user, String firstName, String lastName,
                           Integer age, String emailAddress, String phoneNo) {

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmailAddress(emailAddress);
        user.setPhoneNo(phoneNo);
        user.setRole(Role.GUIDE);
        userService.save(user);
        return "redirect:/allguides";
    }

    @GetMapping("/deleteguide/{id}")
    public String deleteGuide(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/allguides";
    }

    @GetMapping("/editguide/{id}")
    public String editGuide(Model model, @PathVariable Integer id) {
        User guide = userService.findById(id);

        model.addAttribute("guide", guide);
        return "guide/editguide";
    }

    @PostMapping("/editguide/{id}")
    public String editGuide(@ModelAttribute User guide, @PathVariable Integer id) {
        userService.save(guide);
        return "redirect:/allguides";
    }
}



