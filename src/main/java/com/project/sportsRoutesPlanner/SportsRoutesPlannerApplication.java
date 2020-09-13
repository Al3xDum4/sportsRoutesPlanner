package com.project.sportsRoutesPlanner;

import com.project.sportsRoutesPlanner.model.Role;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.repository.*;
import com.project.sportsRoutesPlanner.service.SendGridEmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import org.apache.catalina.realm.X509UsernameRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication
public class SportsRoutesPlannerApplication implements CommandLineRunner, WebMvcConfigurer {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private SendGridEmailService sendGridEmailService;
    @Autowired
    private PendingUserRepository pendingUserRepository;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SportsRoutesPlannerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//	   User user = new User();
//	   user.setFirstName("Andreea");
//	   user.setLastName("Sperdea");
//	   user.setUsername("andreea");
//	   user.setPassword("1234");
//	   user.setAge(35);
//	   user.setEmailAddress("a@b.com");
//	   user.setPhoneNo("0733");
//	   user.setRole(Role.ADMIN);
//	   userRepository.save(user);
//   pendingUserRepository.deleteById(1);
        //userRepository.deleteById(7);
        routeRepository.deleteById(7);


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}
