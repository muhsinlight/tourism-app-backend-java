package com.teknofest.turizm.service;

import com.teknofest.turizm.exception.ResourceNotFoundException;
import com.teknofest.turizm.model.Event;
import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.model.User;
import com.teknofest.turizm.repository.EventRepository;
import com.teknofest.turizm.repository.PlaceRepository;
import com.teknofest.turizm.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, PlaceRepository placeRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    public Event createEvent(Event event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı"));
        Long placeId = event.getPlace().getId();
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new ResourceNotFoundException("Yer bulunamadı"));
        event.setUser(user);
        event.setPlace(place);
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(int id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID:" + id + " ile eşleşen kayıt bulunamadı."));
    }

    @Override
    public Event updateEvent(Event updatedEvent, int id) {
        Event dbEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID:" + id + " ile eşleşen kayıt bulunamadı."));

        dbEvent.setDescription(updatedEvent.getDescription());
        dbEvent.setTitle(updatedEvent.getTitle());

        return eventRepository.save(dbEvent);
    }

    @Override
    public void deleteEvent(int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.delete(event.get());
        } else {
            throw new ResourceNotFoundException("ID:" + id + " ile eşleşen kayıt bulunamadı.");
        }
    }

    @Override
    public List<Event> findByPlaceId(Long place) {
        return eventRepository.findByPlaceId(place);
    }
}