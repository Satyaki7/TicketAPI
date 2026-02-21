package me.satyaki.TicketingApi.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.Model.Users;
import me.satyaki.TicketingApi.Repository.userRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final userRepo repo;

    public MyUserDetailsService(userRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new UserPrincipal(user);
    }
}