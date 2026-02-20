package me.satyaki.TicketingApi.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard error response DTO for all API errors
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String error;
    private String path;
    private LocalDateTime timestamp;
    private List<String> details;

    public ErrorResponse(int status, String message, String error, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.timestamp = timestamp;
    }
}
