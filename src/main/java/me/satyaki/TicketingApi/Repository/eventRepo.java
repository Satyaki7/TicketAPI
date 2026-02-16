package me.satyaki.TicketingApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.satyaki.TicketingApi.Model.Events;

@Repository
public interface eventRepo extends JpaRepository<Events, Long> {

}