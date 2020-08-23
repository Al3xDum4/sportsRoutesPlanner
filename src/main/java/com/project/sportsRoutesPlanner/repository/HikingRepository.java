package com.project.sportsRoutesPlanner.repository;

import com.project.sportsRoutesPlanner.model.Hiking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikingRepository extends JpaRepository<Hiking,String> {
}

