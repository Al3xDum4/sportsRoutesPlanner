package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Event> eventList = eventService.findAll();
        model.addAttribute("allevents", eventList);
        return "homepage/showhomepage";
    }
}
