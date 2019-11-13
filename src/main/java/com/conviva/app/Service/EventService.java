package com.conviva.app.Service;

import com.conviva.app.Model.EventModel;
import com.conviva.app.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;


//    public List<EventModel> listALllEvents() {
//        List<EventModel> patientList = eventRepository.findAll();
//        for(EventModel p : patientList)
//        {
//            List<Admission> admissionList = admissionRepository.findByPatient(p);
//            p.setAdmissionList(admissionList);
//        }
//        return patientList;
//    }

    @Transactional
    public void createEvent(EventModel event) {
        eventRepository.save(event);
    }
}
