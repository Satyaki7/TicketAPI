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
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    String eventId;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    Date date;

    @Getter
    @Setter
    Integer price;

    @Getter
    @Setter
    Integer rows;

    @Getter
    @Setter
    Integer cols;

    @Getter
    @Setter
    Integer availableSeats1 = rows * cols;

    @Getter
    @Setter
    Integer[] bookedSeats;

}
