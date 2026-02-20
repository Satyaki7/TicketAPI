package me.satyaki.TicketingApi.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    String eventId;

    @Getter
    @Setter
    String userId; // admin userId

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    Date date;

    @Getter
    @Setter
    String description;

    @Getter
    @Setter
    String location;

    @Getter
    @Setter
    Integer price;

    @Getter
    @Setter
    Integer totalRows;

    @Getter
    @Setter
    Integer totalCols;

    @Getter
    @Setter
    Integer availableSeats;

    @Getter
    @Setter
    Integer[] bookedSeats;

    public Events() {
    }

}
