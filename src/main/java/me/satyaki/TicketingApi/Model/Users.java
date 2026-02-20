package me.satyaki.TicketingApi.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.satyaki.TicketingApi.DTO.userDTO;
import me.satyaki.TicketingApi.Mapper.userDTOMapper;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    String userId;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String password;

    @Getter
    @Setter
    String role;

    @Getter
    @Setter
    Date lastLogin;

    public Users() {
    }

    public userDTO map(userDTOMapper userMapper) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }

}
