package me.satyaki.TicketingApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.satyaki.TicketingApi.Model.Booking;

@Repository
public interface bookingRepo extends JpaRepository<Booking, Long> {
}
