package com.project.sportsRoutesPlanner.repository;

import com.project.sportsRoutesPlanner.model.Role;
import com.project.sportsRoutesPlanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query("FROM Users u where u.users.role_name = :role_name")
    Optional<Role> findUserByRole(@Param("role_name") String rolename);
}
