package com.project.sportsRoutesPlanner.service;

import com.project.sportsRoutesPlanner.model.Route;
import com.project.sportsRoutesPlanner.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("IRouteService")
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public void save(Route route) {
        routeRepository.save(route);
    }

    public Route findById(Integer id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            return route.get();
        }
        return null;
    }

    public void deleteById(Integer id) {
        routeRepository.deleteById(id);
    }
}
