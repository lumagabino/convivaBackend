package com.conviva.app.Repository;

import com.conviva.app.Model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventModel, Long> {

}