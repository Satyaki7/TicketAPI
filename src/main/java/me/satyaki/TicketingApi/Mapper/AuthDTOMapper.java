package me.satyaki.TicketingApi.Mapper;

import java.util.function.Function;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import me.satyaki.TicketingApi.DTO.AuthResponse;
import me.satyaki.TicketingApi.Model.Users;
import me.satyaki.TicketingApi.Security.JwtService;

@Component
@RequiredArgsConstructor
public class AuthDTOMapper implements Function<Users, AuthResponse> {

    private final JwtService jwtService;

    @Override
    public AuthResponse apply(Users user) {

        String jwt = jwtService.generateToken(
                user.getName(),
                user.getUserId().toString(),
                user.getRole());

        return new AuthResponse(
                user.getUserId(),
                jwt,
                user.getName(),
                user.getRole(),
                user.getLastLogin());
    }
}