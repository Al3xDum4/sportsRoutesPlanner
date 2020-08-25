package com.project.sportsRoutesPlanner.repository;

import com.project.sportsRoutesPlanner.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
