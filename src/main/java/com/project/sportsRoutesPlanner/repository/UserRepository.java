package com.project.sportsRoutesPlanner.repository;

import com.project.sportsRoutesPlanner.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}
