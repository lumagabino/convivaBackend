package com.conviva.app.Repository;

import com.conviva.app.Model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Defines  database to be used
// Important to keep independent from Services and controller so that
// we don't have to change the code if we switch databases
public interface EventRepository extends JpaRepository<EventModel, Long> {

}