package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.service.EventService;
import com.project.sportsRoutesPlanner.service.RouteService;
import com.project.sportsRoutesPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserService userService;

    @GetMapping("homeallevents")
    public String showHomeAllEvents(Model model) {
        List<Event> eventList = eventService.findAll();
        model.addAttribute("events", eventList);
        return "homepage/showhomepage";
    }

    @GetMapping("home/event/{id}")
    public String viewEvent(Model model, @PathVariable Integer id){
        model.addAttribute("event", eventService.findById(id));
        return "event/showevent";
    }

    @GetMapping("home/event/{id}/route")
    public String viewEventRoute(Model model, @PathVariable Integer id) {
        model.addAttribute("route", eventService.findRouteByEvent(id));
        return "event/viewroutebyevent";
    }

    @GetMapping("home/event/{id}/users")
    public String viewEventUsers(Model model, @PathVariable Integer id){
        model.addAttribute("users", eventService.findUsersByEvent(id));
        return "event/viewusersbyevent";
    }

}
