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

// Lógica de negócio (o que a API vai fazer)
@Service
public class EventService {
    @Autowired
    EventRepository eventRepository; // Connects to the database (repository)
    // ARQUITETURA SPRINGBOOT: Controller <-> Service <-> Repository
    // SÓ o Services deve conectar com o repositório
    // O Controller só chama o Services, não mexe no Repository diretamente

    // Devolve o objeto EventModel (evento) a partir do seu id
    public EventModel findSectorById(long id) {
        return eventRepository.getOne(id);
    }

    // Find all events
    public List<EventModel> listAllEvents() {
        return eventRepository.findAll();
    }

    // O @Transaction controla a transação entre o local e o banco de dados
    // https://www.devmedia.com.br/conheca-o-spring-transactional-annotations/32472
    @Transactional
    public void createEvent(EventModel event) {
        // Se o id já existir, não podemos deixar gravar senão vai subescrever o método
        // Se o id encontrado for diferente de zero é porque ele já existe
        // então não devemos criar uma nova tupla para esse valor
        if(event.getId() != 0){
            throw new InvalidInputException("Invalid input id!");
        }
        eventRepository.save(event);
    }

    @Transactional
    public void deleteEventById(long id) {
        // Optional<T> is a container object which may or may not contain a non-null value.
        // If a value is present, isPresent() will return true and get() will return the value.
        // This is a value-based class; use of identity-sensitive operations
        // (including reference equality (==), identity hash code, or synchronization)
        // on instances of Optional may have unpredictable results and should be avoided.
        Optional<EventModel> eventOptional = eventRepository.findById(id);

        if(!eventOptional.isPresent()) {
            throw new InvalidInputException("This id does not exist. It can not be deleted");
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
            throw new InvalidInputException("This id does not exist. It can not be edited");
        }
        // set: muda valor da variável
        event.setId(id);
        eventRepository.save(event);
    }
}
