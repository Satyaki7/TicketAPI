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
import me.satyaki.TicketingApi.Model.Booking;
import me.satyaki.TicketingApi.Service.BookingService;

/**
 * REST Controller for Booking management endpoints
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Create a new booking
     * POST /api/bookings
     */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        if (booking == null || booking.getUserId() == null || booking.getSeatId() == null) {
            throw new InvalidRequestException("User ID and Seat ID are required for booking");
        }
        Booking createdBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    /**
     * Get a booking by ID
     * GET /api/bookings/{bookingId}
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        if (bookingId == null || bookingId <= 0) {
            throw new InvalidRequestException("Invalid booking ID");
        }
        Optional<Booking> booking = bookingService.getBookingById(bookingId);
        return booking.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + bookingId));
    }

    /**
     * Get all bookings
     * GET /api/bookings
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Update an existing booking
     * PUT /api/bookings/{bookingId}
     */
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody Booking bookingDetails) {
        if (bookingId == null || bookingId <= 0) {
            throw new InvalidRequestException("Invalid booking ID");
        }
        if (!bookingService.bookingExists(bookingId)) {
            throw new ResourceNotFoundException("Booking not found with ID: " + bookingId);
        }
        Optional<Booking> updatedBooking = bookingService.updateBooking(bookingId, bookingDetails);
        return updatedBooking.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to update booking with ID: " + bookingId));
    }

    /**
     * Delete a booking by ID
     * DELETE /api/bookings/{bookingId}
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long bookingId) {
        if (bookingId == null || bookingId <= 0) {
            throw new InvalidRequestException("Invalid booking ID");
        }
        if (!bookingService.bookingExists(bookingId)) {
            throw new ResourceNotFoundException("Booking not found with ID: " + bookingId);
        }
        boolean deleted = bookingService.deleteBooking(bookingId);
        if (deleted) {
            return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
        }
        throw new RuntimeException("Failed to delete booking with ID: " + bookingId);
    }
}
