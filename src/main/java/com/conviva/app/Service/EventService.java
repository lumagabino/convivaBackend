package com.conviva.app.Service;

import com.conviva.app.Exceptions.InvalidInputException;
import com.conviva.app.Model.EventModel;
import com.conviva.app.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public EventModel findSectorById(long id)
{
    return eventRepository.getOne(id);
}

    public List<EventModel> listAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public void createEvent(EventModel event) {
        if(event.getId() != 0){
            throw new InvalidInputException("Invalid input id!");
        }
        eventRepository.save(event);
    }

    @Transactional
    public void deleteEventById(long id) {
        Optional<EventModel> eventOptional = eventRepository.findById(id);
        if(!eventOptional.isPresent()) {
            throw new InvalidInputException("This id do not exist, it can not be deleted");
        }
        try {
            eventRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Event could not be deleted because it will affect Data Integrity in DB.");
        }
    }

    public void editEvent(long id, EventModel event) {
        Optional<EventModel> eventOptional = eventRepository.findById(id);

        if(!eventOptional.isPresent()) {
            throw new InvalidInputException("This id do not exist, it can not be edited");
        }
        event.setId(id);
        eventRepository.save(event);
    }
}
