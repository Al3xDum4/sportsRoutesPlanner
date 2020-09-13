package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.model.EventCategory;
import com.project.sportsRoutesPlanner.model.Route;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.service.RouteService;
import com.project.sportsRoutesPlanner.service.EventService;
import com.project.sportsRoutesPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserService userService;

//    @GetMapping("allevents")
//    public String showAllEvents(Model model) {
//        List<Event> eventList = eventService.findAll();
//        model.addAttribute("events", eventList);
//        return "event/showallevents";
//    }

    @GetMapping("allhikingevents")
    public String showAllHikingEvents(Model model) {
        List<Event> hikingEv = eventService.findHikingEvents();
        model.addAttribute("hikingevents", hikingEv);
        model.addAttribute("byEventName", Comparator.comparing(Event::getEventName));
        return "event/showallhikingevents";
    }

    @GetMapping("/addhikingevent")
    public String addEvent(Model model) {
      List<Route> routeList = routeService.findHikings();
      List<User> guidesList = userService.findGuide();
        model.addAttribute("guide","");
        model.addAttribute("event", new Event());
        model.addAttribute("routes",routeList);
        model.addAttribute("allguides",guidesList);
        return "event/addevent";
    }

    @PostMapping("/addhikingevent")
    public String addHikingEvent(@ModelAttribute Event event,String guide){
//        System.out.println("Ghidul este:"+guide);
        User user=userService.findById(Integer.parseInt(guide));
        event.setUsersList(new ArrayList<>());
        event.getUsersList().add(user);
        event.setEventCategory(EventCategory.HIKING);
        eventService.save(event);
        return "redirect:/allhikingevents";
    }

//    @PostMapping("/addevent")
//    public String addEvent(@ModelAttribute Event event) {
//        eventService.save(event);
//        return "redirect:/allevents";
//    }

    @GetMapping("/guide/editevent/{id}")
    public String editEvent(Model model, @PathVariable Integer id) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        return "event/editevent";
    }

    @GetMapping("/guide/deleteevent/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventService.deleteById(id);
        return "redirect:/allevents";
    }
}
