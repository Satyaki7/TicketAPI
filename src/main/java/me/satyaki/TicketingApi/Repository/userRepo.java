package me.satyaki.TicketingApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.satyaki.TicketingApi.Model.Users;

@Repository
public interface userRepo extends JpaRepository<Users, Long> {

}
