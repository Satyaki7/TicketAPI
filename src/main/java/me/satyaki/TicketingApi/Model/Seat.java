package me.satyaki.TicketingApi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // keeping this as enetity for now but will change it to embedebble in future.
@Table(name = "seats")
public class Seat {
    @Id
    String seatId;

    @Getter
    @Setter
    String eventId;

    @Getter
    @Setter
    Integer rowNo;

    @Getter
    @Setter
    Integer colNo;

    @Getter
    @Setter
    Boolean isBooked = false;

    @Getter
    @Setter
    String version;
}
