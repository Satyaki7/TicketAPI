package me.satyaki.TicketingApi.Mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.DTO.eventDTO;
import me.satyaki.TicketingApi.Model.Events;

@Service
public class eventDTOMapper implements Function<Events, eventDTO> {

    @Override
    public eventDTO apply(Events event) {
        return new eventDTO(
                event.getEventId(),
                event.getName(),
                event.getDescription(),
                event.getLocation(),
                event.getDate().toString(),
                event.getPrice(),
                event.getAvailableSeats());
    }

}
