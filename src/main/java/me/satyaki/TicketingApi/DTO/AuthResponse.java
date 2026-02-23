package me.satyaki.TicketingApi.DTO;

import java.sql.Date;

public record AuthResponse(
                String userId,
                String jwt,
                String name,
                String role,
                Date lastLogin

) {
}
