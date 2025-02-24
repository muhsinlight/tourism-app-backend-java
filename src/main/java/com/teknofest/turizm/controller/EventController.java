package com.teknofest.turizm.controller;

import com.teknofest.turizm.model.Event;
import com.teknofest.turizm.response.ApiResponse;
import com.teknofest.turizm.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Event>> save(@RequestBody Event event) {
        var result = eventService.createEvent(event);
        var response = new ApiResponse.Builder<Event>().success(true).message("Kayıt başarılı").data(result).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventsById(@PathVariable("id") int id) {
        var result = eventService.getEventById(id);
        var response = new ApiResponse.Builder<Event>().success(true).message("Event getirildi").data(result).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(@PathVariable Integer id, @RequestBody Event updatedEvent) {
        var result = eventService.updateEvent(updatedEvent, id);
        var response = new ApiResponse.Builder<Event>().success(true).message("Event Güncellendi").data(result).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePlace(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        var response = new ApiResponse.Builder<Void>().success(true).message("Kayıt Silindi").build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/find/{place}")
    public ResponseEntity<ApiResponse<List<Event>>> findByPlaceId(@PathVariable Long place) {
        var result = eventService.findByPlaceId(place);
        var response = new ApiResponse.Builder<List<Event>>().success(true).message("W").data(result).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}