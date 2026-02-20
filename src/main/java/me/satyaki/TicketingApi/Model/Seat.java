package me.satyaki.TicketingApi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Seat entity representing a single seat in the Ticketing API system.
 * This class maps to the "seats" table in the database.
 * 
 * <p>
 * Note: Currently kept as an Entity but will be converted to @Embeddable in
 * future.
 * </p>
 * 
 * <p>
 * Fields:
 * </p>
 * <ul>
 * <li>{@link #seatId} - Unique identifier for the seat</li>
 * <li>{@link #eventId} - ID of the event this seat belongs to</li>
 * <li>{@link #rowNo} - Row number of the seat in the seating grid</li>
 * <li>{@link #colNo} - Column number of the seat in the seating grid</li>
 * <li>{@link #isBooked} - Flag indicating whether the seat has been booked</li>
 * <li>{@link #version} - Version field for optimistic locking in concurrent
 * bookings</li>
 * </ul>
 * 
 * @author Satyaki
 * @version 1.0
 * @since 1.0
 */
@Entity // keeping this as entity for now but will change it to embeddable in future.
@Table(name = "seats")
public class Seat {

    /**
     * Unique identifier for the seat.
     * This is the primary key for the Seats table.
     * Should be auto-generated or based on a unique combination.
     */
    @Id
    String seatId;

    /**
     * The ID of the event that this seat belongs to.
     * References the eventId from the Events table.
     * Cannot be null.
     * Used to link seats to their respective events.
     */
    @Getter
    @Setter
    String eventId;

    /**
     * The row number of this seat in the seating arrangement.
     * Represents the row position in the event's seating grid.
     * Must be a positive integer between 1 and totalRows.
     */
    @Getter
    @Setter
    Integer rowNo;

    /**
     * The column number of this seat in the seating arrangement.
     * Represents the column position in the event's seating grid.
     * Must be a positive integer between 1 and totalCols.
     */
    @Getter
    @Setter
    Integer colNo;

    /**
     * Flag indicating whether this seat has been booked.
     * True if the seat is booked, false if available.
     * Default value: false (seat is available).
     * Used to quickly check seat availability.
     */
    @Getter
    @Setter
    Boolean isBooked = false;

    /**
     * Version field used for optimistic locking.
     * Helps prevent race conditions during concurrent seat bookings.
     * Incremented each time the seat is updated.
     * Ensures data consistency when multiple users try to book the same seat
     * simultaneously.
     */
    @Getter
    @Setter
    String version;

    /**
     * Default constructor for the Seat entity.
     * Required by JPA for object instantiation during database operations.
     */
    public Seat() {
    }
}
