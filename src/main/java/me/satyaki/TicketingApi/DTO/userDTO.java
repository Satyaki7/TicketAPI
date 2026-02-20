package me.satyaki.TicketingApi.DTO;

import java.sql.Date;

public record userDTO(
        String userId,
        String name,
        String email,
        Date lastLogin,
        String role) {

}
