package me.satyaki.TicketingApi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.satyaki.TicketingApi.DTO.eventDTO;
import me.satyaki.TicketingApi.Exception.InvalidRequestException;
import me.satyaki.TicketingApi.Exception.ResourceNotFoundException;
import me.satyaki.TicketingApi.Model.Events;
import me.satyaki.TicketingApi.Service.EventService;

/**
 * REST Controller for Event management endpoints
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * Create a new event
     * POST /api/events
     */
    @PostMapping
    public ResponseEntity<Events> createEvent(@RequestBody Events event) {
        if (event == null || event.getName() == null || event.getDate() == null) {
            throw new InvalidRequestException("Event name and date are required");
        }
        Events createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    /**
     * Get an event by ID
     * GET /api/events/{eventId}
     */
    @GetMapping("/{eventId}")
    public ResponseEntity<eventDTO> getEventById(@PathVariable Long eventId) {
        if (eventId == null || eventId <= 0) {
            throw new InvalidRequestException("Invalid event ID");
        }
        Optional<eventDTO> event = eventService.getEventById(eventId);
        return event.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + eventId));
    }

    /**
     * Get all events
     * GET /api/events
     */
    @GetMapping
    public ResponseEntity<List<eventDTO>> getAllEvents() {
        List<eventDTO> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /**
     * Update an existing event
     * PUT /api/events/{eventId}
     */
    @PutMapping("/{eventId}")
    public ResponseEntity<eventDTO> updateEvent(@PathVariable Long eventId, @RequestBody Events eventDetails) {
        if (eventId == null || eventId <= 0) {
            throw new InvalidRequestException("Invalid event ID");
        }
        if (!eventService.eventExists(eventId)) {
            throw new ResourceNotFoundException("Event not found with ID: " + eventId);
        }
        Optional<eventDTO> updatedEvent = eventService.updateEvent(eventId, eventDetails);
        return updatedEvent.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to update event with ID: " + eventId));
    }

    /**
     * Delete an event by ID
     * DELETE /api/events/{eventId}
     */
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        if (eventId == null || eventId <= 0) {
            throw new InvalidRequestException("Invalid event ID");
        }
        if (!eventService.eventExists(eventId)) {
            throw new ResourceNotFoundException("Event not found with ID: " + eventId);
        }
        boolean deleted = eventService.deleteEvent(eventId);
        if (deleted) {
            return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
        }
        throw new RuntimeException("Failed to delete event with ID: " + eventId);
    }
}
