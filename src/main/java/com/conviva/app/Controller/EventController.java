package com.conviva.app.Controller;

import com.conviva.app.Repository.EventRepository;
import com.conviva.app.Model.EventModel;
import com.conviva.app.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;

// Roteamento
@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired // Manages Controller-Service-Repository relationship dependence
    EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<EventModel> getAllEvents() {
        return eventService.listAllEvents();
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public EventModel getEventById(@PathVariable("id") long id)
    {
        return eventService.findSectorById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK, reason = "Event created")
    public void createEvent(@Valid @RequestBody EventModel event)
    {
        eventService.createEvent(event);
    }


    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK, reason = "Event edited")
    public void editEvent (@PathVariable("id") long id, @Valid @RequestBody EventModel event) {
        eventService.editEvent(id, event);
    }

    @DeleteMapping
    public ResponseEntity removeEventOccurrence(@RequestParam(value = "id") Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Event deleted")
    public void deleteEvent(@PathVariable("id") long id)
    {
        eventService.deleteEventById(id);
    }
}