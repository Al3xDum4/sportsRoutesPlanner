package com.project.sportsRoutesPlanner.service;

import com.project.sportsRoutesPlanner.model.*;
import com.project.sportsRoutesPlanner.repository.EventRepository;
import com.project.sportsRoutesPlanner.repository.RouteRepository;
import com.project.sportsRoutesPlanner.repository.UserRepository;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("IEventService")
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public Event findById(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        }
        return null;
    }

    public List<Event> findHikingEvents() {
        List<Event> events = eventRepository.findAll();
        List<Event> hikingEvents = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            if (((events.get(i).getEventCategory()).equals(EventCategory.HIKING))) {
                hikingEvents.add(events.get(i));
            }
        }
        return hikingEvents;
    }

    public Route findHikingRoute(Integer id){
       Route route= eventRepository.findById(id).get().getRoute();
        return route;
    }

    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }

    public Route findRouteByEvent(Integer id) {
        return eventRepository.findById(id).get().getRoute();
    }

    public List<User> findUsersByEvent(Integer id){
        return eventRepository.findById(id).get().getUsersList();
    }

    public User findGuideByEvent(Integer id){
          List<User> usersList = findUsersByEvent(id);
        for (int i = 0; i <usersList.size() ; i++) {
            if(usersList.get(i).getRole().equals(Role.GUIDE))
                return usersList.get(i);

        }
        return null;
    }
}
