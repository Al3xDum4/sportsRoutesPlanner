package com.project.sportsRoutesPlanner;

import com.project.sportsRoutesPlanner.repository.*;
import com.project.sportsRoutesPlanner.service.JavaMailSenderService;
import org.apache.catalina.realm.X509UsernameRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportsRoutesPlannerApplication implements CommandLineRunner {

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

	public static void main(String[] args) { SpringApplication.run(SportsRoutesPlannerApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {

    }

}
