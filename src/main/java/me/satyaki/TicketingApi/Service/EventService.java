package me.satyaki.TicketingApi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.DTO.eventDTO;
import me.satyaki.TicketingApi.Mapper.eventDTOMapper;
import me.satyaki.TicketingApi.Model.Events;
import me.satyaki.TicketingApi.Repository.eventRepo;

@Service
public class EventService {

    @Autowired
    private eventRepo eventRepository;
    private final eventDTOMapper eventDTOMapper;

    @Autowired
    public EventService(eventDTOMapper eventDTOMapper) {
        this.eventDTOMapper = eventDTOMapper;
    }

    /**
     * Create a new event
     * 
     * @param event the event object to create
     * @return the created event
     */
    public Events createEvent(Events event) {
        return eventRepository.save(event);
    }

    /**
     * Get an event by ID
     * 
     * @param eventId the event ID
     * @return Optional containing the event if found
     */
    public Optional<eventDTO> getEventById(Long eventId) {
        return eventRepository.findById(eventId).map(eventDTOMapper);
    }

    /**
     * Get all events
     * 
     * @return list of all events
     */
    public List<eventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(eventDTOMapper).toList();
    }

    /**
     * Update an existing event
     * 
     * @param eventId      the event ID
     * @param eventDetails the updated event details
     * @return the updated event
     */
    public Optional<eventDTO> updateEvent(Long eventId, Events eventDetails) {
        return eventRepository.findById(eventId).map(event -> {
            if (eventDetails.getUserId() != null) {
                event.setUserId(eventDetails.getUserId());
            }
            if (eventDetails.getName() != null) {
                event.setName(eventDetails.getName());
            }
            if (eventDetails.getDate() != null) {
                event.setDate(eventDetails.getDate());
            }
            if (eventDetails.getPrice() != null) {
                event.setPrice(eventDetails.getPrice());
            }
            if (eventDetails.getTotalRows() != null) {
                event.setTotalRows(eventDetails.getTotalRows());
            }
            if (eventDetails.getTotalCols() != null) {
                event.setTotalCols(eventDetails.getTotalCols());
            }
            if (eventDetails.getAvailableSeats() != null) {
                event.setAvailableSeats(eventDetails.getAvailableSeats());
            }
            return eventRepository.save(event);
        }).map(eventDTOMapper);
    }

    /**
     * Delete an event by ID
     * 
     * @param eventId the event ID
     * @return true if deletion was successful
     */
    public boolean deleteEvent(Long eventId) {
        if (eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
            return true;
        }
        return false;
    }

    /**
     * Check if an event exists by ID
     * 
     * @param eventId the event ID
     * @return true if event exists
     */
    public boolean eventExists(Long eventId) {
        return eventRepository.existsById(eventId);
    }

}
