package com.conviva.app.Controller;

import com.conviva.app.Repository.EventRepository;
import com.conviva.app.Model.EventModel;
import com.conviva.app.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    EventService eventService;

    @GetMapping
    public ResponseEntity getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getEvent(@PathVariable(value = "id") Long id) {
        Optional<EventModel> foundEvent = eventRepository.findById(id);

        if(foundEvent.isPresent()) {
            return ResponseEntity.ok(foundEvent.get());
        } else {
            return ResponseEntity.badRequest().body("No events occurrence with specified id " + id + " found");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK, reason = "Event created")
    public void createEvent(@Valid @RequestBody EventModel event)
    {
        eventService.createEvent(event);
    }



    @PutMapping
    public ResponseEntity updateEventOccurrence(@RequestParam(value = "eventName") String eventName, @RequestParam(value="id") Long id, @RequestParam(value="eventDesc") String eventDesc) {
        Optional<EventModel> optionalEventOccurrence = eventRepository.findById(id);

        if(!optionalEventOccurrence.isPresent()) {
            return ResponseEntity.badRequest().body("No disease occurrence with specified id " + id + " found");
        }

        EventModel foundEventOccurrence = optionalEventOccurrence.get();
        foundEventOccurrence.setName(eventName);
        foundEventOccurrence.setDescription(eventDesc);

        return ResponseEntity.ok(foundEventOccurrence);
    }

    @DeleteMapping
    public ResponseEntity removeEventOccurrence(@RequestParam(value = "id") Long id) {
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}