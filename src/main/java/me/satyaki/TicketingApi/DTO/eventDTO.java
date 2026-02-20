package me.satyaki.TicketingApi.DTO;

public record eventDTO(
        String eventId,
        String name,
        String description,
        String location,
        String date,
        Integer price,
        Integer availableSeats) {

}
