package com.project.sportsRoutesPlanner.repository;

import com.project.sportsRoutesPlanner.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
