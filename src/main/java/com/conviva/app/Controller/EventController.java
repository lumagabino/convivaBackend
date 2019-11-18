package com.conviva.app.Controller;

import com.conviva.app.Model.EventModel;
import com.conviva.app.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;

// Roteamento: ponte entre Services e protocolo REST
@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired // Manages Controller-Service-Repository relationship dependence
    EventService eventService;

    // GET (pull event(s) from  database) com  dois tipos de entradas possíveis
    // 1) Sem passar nenhum parâmetro pega todos os eventos
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<EventModel> getAllEvents() {
        return eventService.listAllEvents();
    }

    // 2) passando o id como parâmetro ele pega só o perfil com aquele id
    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public EventModel getEventById(@PathVariable("id") long id)
    {
        return eventService.findSectorById(id);
    }

    // POST (create event)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK, reason = "Event created")
    public void createEvent(@Valid @RequestBody EventModel event)
    {
        eventService.createEvent(event);
    }

    // PUT (edit event)
    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK, reason = "Event edited")
    public void editEvent (@PathVariable("id") long id, @Valid @RequestBody EventModel event) {
        eventService.editEvent(id, event);
    }

    // DELETE (by id)
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Event deleted")
    public void deleteEvent(@PathVariable("id") long id)
    {
        eventService.deleteEventById(id);
    }
}