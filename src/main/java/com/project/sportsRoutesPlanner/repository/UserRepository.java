package com.project.sportsRoutesPlanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository {
    Optional<Member> findByUsername(String username);
}
