package me.satyaki.TicketingApi.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Events entity representing an event in the Ticketing API system.
 * This class maps to the "events" table in the database.
 * 
 * <p>
 * Fields:
 * </p>
 * <ul>
 * <li>{@link #eventId} - Unique identifier for the event (UUID)</li>
 * <li>{@link #userId} - ID of the admin/organizer who created the event</li>
 * <li>{@link #name} - Name or title of the event</li>
 * <li>{@link #date} - Date when the event will occur</li>
 * <li>{@link #description} - Detailed description of the event</li>
 * <li>{@link #location} - Venue or location where the event will be held</li>
 * <li>{@link #price} - Price per ticket for the event</li>
 * <li>{@link #totalRows} - Total number of rows in the seating arrangement</li>
 * <li>{@link #totalCols} - Total number of columns in the seating
 * arrangement</li>
 * <li>{@link #availableSeats} - Number of seats still available for
 * booking</li>
 * <li>{@link #bookedSeats} - Array of seat IDs that have been booked</li>
 * </ul>
 * 
 * @author Satyaki
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "events")
public class Events {

    /**
     * Unique identifier for the event.
     * Generated automatically using UUID strategy.
     * This is the primary key for the Events table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    String eventId;

    /**
     * The ID of the admin or organizer who created this event.
     * References the userId from the Users table.
     * Used to track event ownership and permissions.
     */
    @Getter
    @Setter
    String userId; // admin userId

    /**
     * The name or title of the event.
     * Should be descriptive and meaningful.
     * Cannot be null.
     */
    @Getter
    @Setter
    String name;

    /**
     * The date when the event will occur.
     * Format: SQL Date (yyyy-MM-dd).
     * Cannot be null.
     */
    @Getter
    @Setter
    Date date;

    /**
     * A detailed description of the event.
     * Provides additional information about the event to users.
     * Can include event highlights, speaker information, etc.
     */
    @Getter
    @Setter
    String description;

    /**
     * The venue or location where the event will be held.
     * Can be a venue name, address, or descriptive location.
     * Cannot be null.
     */
    @Getter
    @Setter
    String location;

    /**
     * The price per ticket for this event.
     * Must be a positive integer value.
     * Cannot be null.
     */
    @Getter
    @Setter
    Integer price;

    /**
     * Total number of rows in the seating arrangement for this event.
     * Defines the seating grid dimensions (rows).
     * Cannot be null.
     */
    @Getter
    @Setter
    Integer totalRows;

    /**
     * Total number of columns in the seating arrangement for this event.
     * Defines the seating grid dimensions (columns).
     * Cannot be null.
     */
    @Getter
    @Setter
    Integer totalCols;

    /**
     * Number of seats still available for booking.
     * Decreases as users book seats.
     * Should be equal to (totalRows * totalCols) - length of bookedSeats.
     */
    @Getter
    @Setter
    Integer availableSeats;

    /**
     * Array of seat IDs that have been booked for this event.
     * Contains the IDs of all seats with confirmed bookings.
     * Used to quickly identify which seats are no longer available.
     */
    @Getter
    @Setter
    Integer[] bookedSeats;

    /**
     * Default constructor for the Events entity.
     * Required by JPA for object instantiation during database operations.
     */
    public Events() {
    }

}
