package me.satyaki.TicketingApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.DTO.userDTO;
import me.satyaki.TicketingApi.Mapper.userDTOMapper;
import me.satyaki.TicketingApi.Model.Users;
import me.satyaki.TicketingApi.Repository.userRepo;

@Service
public class UserService {

    @Autowired
    private userRepo userRepository;
    private final userDTOMapper userMapper;

    @Autowired
    public UserService(userDTOMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Create a new user
     * 
     * @param user the user object to create
     * @return the created user
     */
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    /**
     * Get a user by ID
     * 
     * @param userId the user ID
     * @return Optional containing the user if found
     */
    public Optional<userDTO> getUserById(Long userId) {
        return userRepository.findById(userId).map(user -> new userDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getLastLogin(),
                user.getRole()));
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
