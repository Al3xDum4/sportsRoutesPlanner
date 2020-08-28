package com.project.sportsRoutesPlanner.service;

import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.model.Route;
import com.project.sportsRoutesPlanner.model.User;
import com.project.sportsRoutesPlanner.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("IEventService")
public class EventService {

    @Autowired
    private EventRepository eventRepository;

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

    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }

    public Route findRouteByEvent(Integer id) {
        return eventRepository.findById(id).get().getRoute();
    }

    public List<User> findUsersByEvent(Integer id){
        return eventRepository.findById(id).get().getUsersList();
    }
}
