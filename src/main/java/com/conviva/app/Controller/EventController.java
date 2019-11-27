package com.conviva.app.Controller;

import com.conviva.app.Model.EventModel;
import com.conviva.app.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;

    // GET all events created by the same adm
    @RequestMapping( path = "/from_adm",
            method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<EventModel> getEventsFromAdm(@RequestParam("adm") String adm) {
        return eventService.findEventsFromAdm(adm);
    }

    // GET all events
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<EventModel> getAllEvents() {
        return eventService.listAllEvents();
    }

    // GET event by id
    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public EventModel getEventById(@PathVariable("id") long id) {
        return eventService.findSectorById(id);
    }

//    // GET events by region WITHOUT parameters
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseStatus(code = HttpStatus.OK)
//    @ResponseBody
//    public List<EventModel> getEventsByFixedRegion() {
//        return eventService.findEventByRegion(30.0,20.0,500.0);
//    }

    // GET events by region
    @RequestMapping( path = {"/region"},
            method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<EventModel> getEventsByRegion(@RequestParam("longitude") String longitude,
                                              @RequestParam("latitude") String latitude,
                                              @RequestParam("radius") String radius) {

        System.out.println("Aqui");

        Double latitudeValue = Double.valueOf(latitude);
        Double longitudeValue = Double.valueOf(longitude);
        Double radiusValue = Double.valueOf(radius);

        return eventService.findEventByRegion(longitudeValue, latitudeValue, radiusValue);
    }

    // POST (create event)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK, reason = "Event created")
    public void createEvent(@Valid @RequestBody EventModel event) {
        eventService.createEvent(event);
    }

    // PUT (edit event)
    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK, reason = "Event edited")
    public void editEvent (@PathVariable("id") long id, @Valid @RequestBody EventModel event) {
        eventService.editEvent(id, event);
    }

    // DELETE event by id
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Event deleted")
    public void deleteEvent(@PathVariable("id") long id) {
        eventService.deleteEventById(id);
    }
}