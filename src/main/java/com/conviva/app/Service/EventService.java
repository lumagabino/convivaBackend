package com.conviva.app.Service;

import com.conviva.app.Exceptions.InvalidInputException;
import com.conviva.app.Model.EventModel;
import com.conviva.app.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository; // connects to the repository

    // find all events of one adm
    public List<EventModel> findEventsFromAdm(String adm) {
        return eventRepository.findEventsWithAdm(adm);
    }

    // Find event through id
    public EventModel findSectorById(long id) {
        return eventRepository.getOne(id);
    }

    // Find all events
    public List<EventModel> listAllEvents() { return eventRepository.findAll(Sort.by("date")); }

    // Find events in defined region
    public List<EventModel> findEventByRegion(Double longitude, Double latitude, Double radius) {
        return eventRepository.findEventByRegion(longitude, latitude, radius);
    }

    // Create event
    @Transactional
    public void createEvent(EventModel event) {
        if(event.getId() != 0) { // // check if event already exists so the new one doesn't override it
            throw new InvalidInputException("Invalid input id!");
        }
        eventRepository.save(event);
    }

    // Delete event
    @Transactional
    public void deleteEventById(long id) {
        Optional<EventModel> eventOptional = eventRepository.findById(id);

        if(!eventOptional.isPresent()) { // check of event exists in repository
            throw new InvalidInputException("This id does not exist. It can not be deleted");
        }
        try {
            eventRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Event could not be deleted because it will affect Data Integrity in DB.");
        }
    }

    // Edit event
    public void editEvent(long id, EventModel event) {
        Optional<EventModel> eventOptional = eventRepository.findById(id);

        if(!eventOptional.isPresent()) { // check of event exists in repository
            throw new InvalidInputException("This id does not exist. It can not be edited");
        }
        event.setId(id); // set the id of the old event as the id of the edited event
        eventRepository.save(event);
    }
}
