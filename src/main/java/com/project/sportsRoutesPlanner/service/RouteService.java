package com.project.sportsRoutesPlanner.service;

import com.project.sportsRoutesPlanner.model.*;
import com.project.sportsRoutesPlanner.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("IRouteService")
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public Route save(Route route) {
        return routeRepository.save(route);
    }

    public Route findById(Integer id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            return route.get();
        }
        return null;
    }

    public List<Route> findHikings() {
        List<Route> routes = routeRepository.findAll();
        List<Route> hikingRoutes = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            if (((routes.get(i).getRouteCategory()).equals(RouteCategory.HIKING))) {
                hikingRoutes.add(routes.get(i));
            }
        }
        return hikingRoutes;
    }

    public List<DifficultyLevel> allDifficultLevels() {
        List<DifficultyLevel> difficultyLevels = new ArrayList<>();
        for (DifficultyLevel df : DifficultyLevel.values()) {
            difficultyLevels.add(df);
        }
        return difficultyLevels;
    }

    public void deleteById(Integer id) {
        routeRepository.deleteById(id);
    }
}
