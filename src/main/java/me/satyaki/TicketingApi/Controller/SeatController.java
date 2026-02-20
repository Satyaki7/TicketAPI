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

import me.satyaki.TicketingApi.Exception.InvalidRequestException;
import me.satyaki.TicketingApi.Exception.ResourceNotFoundException;
import me.satyaki.TicketingApi.Model.Seat;
import me.satyaki.TicketingApi.Service.SeatService;

/**
 * REST Controller for Seat management endpoints
 */
@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    /**
     * Create a new seat
     * POST /api/seats
     */
    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        if (seat == null || seat.getEventId() == null || seat.getRowNo() == null || seat.getColNo() == null) {
            throw new InvalidRequestException("Event ID, Row Number, and Column Number are required for seat creation");
        }
        Seat createdSeat = seatService.createSeat(seat);
        return new ResponseEntity<>(createdSeat, HttpStatus.CREATED);
    }

    /**
     * Get a seat by ID
     * GET /api/seats/{seatId}
     */
    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long seatId) {
        if (seatId == null || seatId <= 0) {
            throw new InvalidRequestException("Invalid seat ID");
        }
        Optional<Seat> seat = seatService.getSeatById(seatId);
        return seat.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with ID: " + seatId));
    }

    /**
     * Get all seats
     * GET /api/seats
     */
    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats() {
        List<Seat> seats = seatService.getAllSeats();
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    /**
     * Update an existing seat
     * PUT /api/seats/{seatId}
     */
    @PutMapping("/{seatId}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long seatId, @RequestBody Seat seatDetails) {
        if (seatId == null || seatId <= 0) {
            throw new InvalidRequestException("Invalid seat ID");
        }
        if (!seatService.seatExists(seatId)) {
            throw new ResourceNotFoundException("Seat not found with ID: " + seatId);
        }
        Optional<Seat> updatedSeat = seatService.updateSeat(seatId, seatDetails);
        return updatedSeat.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to update seat with ID: " + seatId));
    }

    /**
     * Delete a seat by ID
     * DELETE /api/seats/{seatId}
     */
    @DeleteMapping("/{seatId}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long seatId) {
        if (seatId == null || seatId <= 0) {
            throw new InvalidRequestException("Invalid seat ID");
        }
        if (!seatService.seatExists(seatId)) {
            throw new ResourceNotFoundException("Seat not found with ID: " + seatId);
        }
        boolean deleted = seatService.deleteSeat(seatId);
        if (deleted) {
            return new ResponseEntity<>("Seat deleted successfully", HttpStatus.OK);
        }
        throw new RuntimeException("Failed to delete seat with ID: " + seatId);
    }
}
