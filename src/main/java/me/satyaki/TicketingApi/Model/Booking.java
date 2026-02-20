package me.satyaki.TicketingApi.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Booking entity representing a ticket booking in the Ticketing API system.
 * This class maps to the "bookings" table in the database.
 * 
 * <p>
 * Fields:
 * </p>
 * <ul>
 * <li>{@link #bookingId} - Unique identifier for the booking</li>
 * <li>{@link #userId} - ID of the user who made the booking</li>
 * <li>{@link #seatId} - ID of the seat that was booked</li>
 * <li>{@link #bookingTime} - Timestamp of when the booking was made</li>
 * <li>{@link #status} - Status of the booking (CONFIRMED, CANCELLED,
 * PENDING)</li>
 * </ul>
 * 
 * @author Satyaki
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "bookings")
public class Booking {

    /**
     * Unique identifier for the booking.
     * This is the primary key for the Bookings table.
     * Should be auto-generated or UUID.
     */
    @Id
    String bookingId;

    /**
     * The ID of the user who made this booking.
     * References the userId from the Users table.
     * Cannot be null.
     */
    @Getter
    @Setter
    String userId;

    /**
     * The ID of the seat that was booked.
     * References the seatId from the Seats table.
     * Cannot be null.
     */
    @Getter
    @Setter
    String seatId;

    /**
     * The timestamp of when the booking was created.
     * Recorded at the moment of booking confirmation.
     * Format: SQL Date.
     */
    @Getter
    @Setter
    Date bookingTime;

    /**
     * The current status of the booking.
     * Possible values: "CONFIRMED", "CANCELLED", "PENDING".
     * <ul>
     * <li>CONFIRMED: The booking is confirmed and the ticket is valid</li>
     * <li>CANCELLED: The booking has been cancelled</li>
     * <li>PENDING: The booking is awaiting confirmation</li>
     * </ul>
     */
    @Getter
    @Setter
    String status; // e.g., "CONFIRMED", "CANCELLED", "PENDING"

    /**
     * Default constructor for the Booking entity.
     * Required by JPA for object instantiation during database operations.
     */
    public Booking() {
    }

}
