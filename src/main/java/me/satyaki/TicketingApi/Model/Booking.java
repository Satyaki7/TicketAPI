package me.satyaki.TicketingApi.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    String bookingId;

    @Getter
    @Setter
    String userId;

    @Getter
    @Setter
    String seatId;

    @Getter
    @Setter
    Date bookingTime;

    @Getter
    @Setter
    String status; // e.g., "CONFIRMED", "CANCELLED", "PENDING"

}
