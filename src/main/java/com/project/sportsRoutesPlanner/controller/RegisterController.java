package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.PendingUser;
import com.project.sportsRoutesPlanner.model.Role;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.repository.PendingUserRepository;
import com.project.sportsRoutesPlanner.service.JavaMailSenderService;
import com.project.sportsRoutesPlanner.service.RandomStringGenerator;
import com.project.sportsRoutesPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private PendingUserRepository pendingUserRepository;

    @Autowired
    private RandomStringGenerator randomStringGenerator;

    @Autowired
    private JavaMailSenderService javaMailSenderService;

    @GetMapping("/register")
    public String registerUser() {
        return "security/register";
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, String emailAddress, String firstName,
                               String lastName, Integer age, String phoneNo, HttpServletRequest request) {
        String validationUrl = "https://" + request.getServerName() + request.getContextPath();
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder().encode(password));
        user.setEmailAddress(emailAddress);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setPhoneNo(phoneNo);
        user.setRole(Role.MEMBER);
        userService.save(user);

        PendingUser pendingUser = new PendingUser();
        String activationCode = randomStringGenerator.getAlphaNumericString(20);
        pendingUser.setActivationCode(activationCode);
        javaMailSenderService.sendHTML("buhaidebalta.15@gmail.com", user.getEmailAddress(),
                "Please confirm account", randomStringGenerator.linkCreator(activationCode, validationUrl));
        pendingUser.setUser(user);
        pendingUserRepository.save(pendingUser);

        return "redirect:/login";
    }

    @GetMapping("/userValidation")
    public String validateUser(String activationCode) {
        System.out.println(activationCode);
        Optional<PendingUser> optionalPendingUser = pendingUserRepository.findByActivationCode(activationCode);
        if(optionalPendingUser.isPresent()){
            PendingUser pendingUser = optionalPendingUser.get();
            System.out.println(pendingUser.getActivationCode());
            pendingUserRepository.delete(pendingUser);
        }
        return "redirect:/login";
    }
}
