package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.text.normalizer.NormalizerBase;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("allevents")
    public String showAllEvents(Model model) {
        List<Event> eventList = eventService.findAll();
        model.addAttribute("events", eventList);
        return "event/showallevents";
    }

    @GetMapping("/addevent")
    public String addEvent(Model model) {
        model.addAttribute("events", new Event());
        return "event/addevent";
    }

    @PostMapping("/addevent")
    public String addEvent(@ModelAttribute Event event) {
        eventService.save(event);
        return "redirect:/allevents";
    }

    @GetMapping("/editstudent/{id}")
    public String editEvent(Model model, @PathVariable Integer id) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        return "event/editevent";
    }

    @PostMapping("/editevent/{id}")
    public String editEvent(@ModelAttribute Event event, @PathVariable Integer id) {
        eventService.save(event);
        return "redirect:/allevents";
    }

    @GetMapping("/deleteevent/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventService.deleteById(id);
        return "redirect:/allevents";
    }
}
