package me.satyaki.TicketingApi.Mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.DTO.userDTO;
import me.satyaki.TicketingApi.Model.Users;

@Service
public class userDTOMapper implements Function<Users, userDTO> {

    @Override
    public userDTO apply(Users user) {

        return new userDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getLastLogin(),
                user.getRole());
    }

}
