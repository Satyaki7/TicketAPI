package me.satyaki.TicketingApi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.Model.Booking;
import me.satyaki.TicketingApi.Repository.bookingRepo;

@Service
public class BookingService {

    @Autowired
    private bookingRepo bookingRepository;

    /**
     * Create a new booking
     * 
     * @param booking the booking object to create
     * @return the created booking
     */
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Get a booking by ID
     * 
     * @param bookingId the booking ID
     * @return Optional containing the booking if found
     */
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    /**
     * Get all bookings
     * 
     * @return list of all bookings
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Update an existing booking
     * 
     * @param bookingId      the booking ID
     * @param bookingDetails the updated booking details
     * @return the updated booking
     */
    public Optional<Booking> updateBooking(Long bookingId, Booking bookingDetails) {
        return bookingRepository.findById(bookingId).map(booking -> {
            if (bookingDetails.getUserId() != null) {
                booking.setUserId(bookingDetails.getUserId());
            }
            if (bookingDetails.getSeatId() != null) {
                booking.setSeatId(bookingDetails.getSeatId());
            }
            if (bookingDetails.getBookingTime() != null) {
                booking.setBookingTime(bookingDetails.getBookingTime());
            }
            if (bookingDetails.getStatus() != null) {
                booking.setStatus(bookingDetails.getStatus());
            }
            return bookingRepository.save(booking);
        });
    }

    /**
     * Delete a booking by ID
     * 
     * @param bookingId the booking ID
     * @return true if deletion was successful
     */
    public boolean deleteBooking(Long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }

    /**
     * Check if a booking exists by ID
     * 
     * @param bookingId the booking ID
     * @return true if booking exists
     */
    public boolean bookingExists(Long bookingId) {
        return bookingRepository.existsById(bookingId);
    }

}
