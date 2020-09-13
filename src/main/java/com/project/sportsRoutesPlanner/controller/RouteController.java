package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.DifficultyLevel;
import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.model.Route;
import com.project.sportsRoutesPlanner.model.RouteCategory;
import com.project.sportsRoutesPlanner.service.RouteService;
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
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("allroutes")
    public String showAllRoutes(Model model){
        List<Route> routeList = routeService.findAll();
        model.addAttribute("routes", routeList);
        return "route/showallroutes";
    }

    @GetMapping("allhikingroutes")
    public String showAllHikingRoutes(Model model){
        List<Route> hikings = routeService.findHikings();
        model.addAttribute("hikingroutes", hikings);
        model.addAttribute("byRouteName", Comparator.comparing(Route::getRouteName));
        return "route/showallhikingroutes";
    }

    @GetMapping("/addhikingroute")
    public String addRoute(Model model){
        List<DifficultyLevel> difficultyLevels = routeService.allDifficultLevels();
        model.addAttribute("models", new Route());
        model.addAttribute("diflevels", difficultyLevels);
        return "route/addroute";
    }

    @PostMapping("/addhikingroute")
    public String addHikingRoute(@ModelAttribute Route route, String routeName, String description, Double distance,
                           Double maxAltitude, Double duration, String difficultyLevel) {
        route.setRouteName(routeName);
        route.setDescription(description);
        route.setDistance(distance);
        route.setMaxAltitude(maxAltitude);
        route.setDuration(duration);
        route.setRouteCategory(RouteCategory.HIKING);
        route.setDifficultyLevel(DifficultyLevel.valueOf(difficultyLevel));
        routeService.save(route);
        return "redirect:/allhikingroutes";
    }

    @GetMapping("/edithikingroute/{id}")
    public String editHikingRoute(Model model, @PathVariable Integer id) {
        List<DifficultyLevel> difficultyLevels = routeService.allDifficultLevels();
        Route route = routeService.findById(id);
        model.addAttribute("route", route);
        model.addAttribute("diflevels", difficultyLevels);
        return "route/editroute";
    }

    @PostMapping("/edithikingroute/{id}")
    public String editHikingRoute(@ModelAttribute Route route, @PathVariable Integer id) {
        routeService.save(route);
        return "redirect:/allhikingroutes";
    }

    @GetMapping("/deletehikingroute/{id}")
    public String deleteHikingRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/allhikingroutes";
    }

    @GetMapping("/deletecyclingroute/{id}")
    public String deleteCyclingRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/allhikingroutes";
    }

}
