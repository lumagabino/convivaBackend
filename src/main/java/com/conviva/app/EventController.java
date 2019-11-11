package com.conviva.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class EventController {
    @Autowired
    EventRepository eventRepository;

    @GetMapping(value = "/")
    public ResponseEntity index() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping(value = "/event")
    public ResponseEntity getBucket(@RequestParam(value="id") Long id) {
        Optional<EventModel> foundBucketList = eventRepository.findById(id);

        if(foundBucketList.isPresent()){
            return ResponseEntity.ok(foundBucketList.get());
        }else {
            return ResponseEntity.badRequest().body("No bucket with specified id " + id + " found");
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity addToBucketList(@RequestParam(value="name") String name, @RequestParam(value="description") String desc) {
        return ResponseEntity.ok(eventRepository.save(new EventModel(name, desc)));
    }

    @PutMapping(value = "/")
    public ResponseEntity updateBucketList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id, @RequestParam(value="description") String desc) {
        Optional<EventModel> optionalBucketList = eventRepository.findById(id);
        if(!optionalBucketList.isPresent()){
            return ResponseEntity.badRequest().body("No bucket with specified id " + id + " found");
        }

        EventModel foundBucketList = optionalBucketList.get();
        foundBucketList.setName(name);
        foundBucketList.setDescription(desc);

        return ResponseEntity.ok(eventRepository.save(foundBucketList));
    }

    @DeleteMapping(value = "/")
    public ResponseEntity removeBucketList(@RequestParam(value="id") Long id) {
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
