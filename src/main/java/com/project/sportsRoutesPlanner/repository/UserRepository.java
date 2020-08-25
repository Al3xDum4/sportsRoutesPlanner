package com.project.sportsRoutesPlanner.repository;

import com.project.sportsRoutesPlanner.model.Member;
import com.project.sportsRoutesPlanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository {
    Optional<User> findByUsername(String username);
}
