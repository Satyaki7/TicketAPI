package me.satyaki.TicketingApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.DTO.AuthResponse;
import me.satyaki.TicketingApi.DTO.userDTO;
import me.satyaki.TicketingApi.Mapper.AuthDTOMapper;
import me.satyaki.TicketingApi.Mapper.userDTOMapper;
import me.satyaki.TicketingApi.Model.Users;
import me.satyaki.TicketingApi.Repository.userRepo;

@Service
public class UserService {

    private final userRepo userRepository;
    private final userDTOMapper userMapper;
    private final AuthDTOMapper authMapper;
    private final BCryptPasswordEncoder encoder;

    public UserService(userRepo userRepository,
            userDTOMapper userMapper,
            AuthDTOMapper authMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authMapper = authMapper;
        this.encoder = new BCryptPasswordEncoder(12);
    }

    /**
     * Create a new user
     * 
     * @param user the user object to create
     * @return the created user
     */
    public AuthResponse createUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        return authMapper.apply(savedUser);
    }

    /**
     * Get a user by ID
     * 
     * @param userId the user ID
     * @return Optional containing the user if found
     */
    public Optional<userDTO> getUserById(Long userId) {
        return userRepository.findById(userId).map(userMapper);
    }

    /**
     * Get all users
     * 
     * @return list of all users
     */
    public List<userDTO> getAlluserDTO() {
        return userRepository.findAll().stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }

    /**
     * Update an existing user
     * 
     * @param userId      the user ID
     * @param userDetails the updated user details
     * @return the updated user
     */
    public Optional<userDTO> updateUser(Long userId, Users userDetails) {
        return userRepository.findById(userId).map(user -> {
            if (userDetails.getName() != null) {
                user.setName(userDetails.getName());
            }
            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null) {
                user.setPassword(userDetails.getPassword());
            }
            if (userDetails.getRole() != null) {
                user.setRole(userDetails.getRole());
            }
            if (userDetails.getLastLogin() != null) {
                user.setLastLogin(userDetails.getLastLogin());
            }
            return userRepository.save(user);
        }).map(userMapper);
    }

    /**
     * Delete a user by ID
     * 
     * @param userId the user ID
     * @return true if deletion was successful
     */
    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    /**
     * Check if a user exists by ID
     * 
     * @param userId the user ID
     * @return true if user exists
     */
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

}
