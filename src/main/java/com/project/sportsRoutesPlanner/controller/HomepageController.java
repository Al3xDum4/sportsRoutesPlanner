package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.model.Route;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.service.EventService;
import com.project.sportsRoutesPlanner.service.RouteService;
import com.project.sportsRoutesPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class HomepageController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserService userService;

    @GetMapping("homeallevents")
    public String showAllHomeEvents(Model model) {
        List<Event> eventList = eventService.findAll();
        model.addAttribute("allevents", eventList);
        //model.addAttribute("byEventName", Comparator.comparing(Event::getEventName));
        return "homepage/showhomepage";
    }

    @GetMapping("showevent/{id}")
    public ModelAndView showEvent(@PathVariable Integer id) {
        //Event event = eventService.findById(id);
        ModelAndView modelAndView = new ModelAndView("event/showhikingevent");
        modelAndView.addObject("route", eventService.findRouteByEvent(id));
        modelAndView.addObject("guide", eventService.findGuideByEvent(id));
        modelAndView.addObject("event", eventService.findById(id));
        return modelAndView;
    }

    @GetMapping(value = "/slick/event/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void fromDatabaseEventBackground(@PathVariable("id") Integer id, HttpServletResponse response)
            throws IOException {
        Optional<Route> route = Optional.ofNullable(eventService.findHikingRoute(id));
        if (route.isPresent()) {
            byte[] image = route.get().getImage();
            StreamUtils.copy(image, response.getOutputStream());
        }
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
