package me.satyaki.TicketingApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.satyaki.TicketingApi.Model.Seat;

@Repository
public interface seatRepo extends JpaRepository<Seat, Long> {

}
