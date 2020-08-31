package com.project.sportsRoutesPlanner;

import com.project.sportsRoutesPlanner.model.Role;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.repository.*;
import com.project.sportsRoutesPlanner.service.JavaMailSenderService;
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
    private JavaMailSenderService javaMailSenderService;
    @Autowired
    private PendingUserRepository pendingUserRepository;

//    @Bean
//    public PasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }


	public static void main(String[] args) { SpringApplication.run(SportsRoutesPlannerApplication.class, args); }

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
//	   pendingUserRepository.deleteById(1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}
