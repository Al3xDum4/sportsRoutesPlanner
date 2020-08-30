package com.project.sportsRoutesPlanner.service;

import com.project.sportsRoutesPlanner.model.Role;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("IUserService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUserByRole() {
        return userRepository.findAll();
    }


    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
