package me.satyaki.TicketingApi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.satyaki.TicketingApi.DTO.userDTO;
import me.satyaki.TicketingApi.Exception.InvalidRequestException;
import me.satyaki.TicketingApi.Exception.ResourceNotFoundException;
import me.satyaki.TicketingApi.Model.Users;
import me.satyaki.TicketingApi.Service.UserService;

/**
 * REST Controller for User management endpoints
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create a new user
     * POST /api/users
     */

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        if (user == null || user.getName() == null || user.getEmail() == null) {
            throw new InvalidRequestException("User name and email are required");
        }
        Users createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Get a user by ID
     * GET /api/users/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<userDTO> getUserById(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            throw new InvalidRequestException("Invalid user ID");
        }
        Optional<userDTO> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    /**
     * Get all users
     * GET /api/users
     */
    @GetMapping
    public ResponseEntity<List<userDTO>> getAllUsers() {
        List<userDTO> users = userService.getAlluserDTO();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Update an existing user
     * PUT /api/users/{userId}
     */
    @PutMapping("/{userId}")
    public ResponseEntity<userDTO> updateUser(@PathVariable Long userId, @RequestBody Users userDetails) {
        if (userId == null || userId <= 0) {
            throw new InvalidRequestException("Invalid user ID");
        }
        if (!userService.userExists(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        Optional<userDTO> updatedUser = userService.updateUser(userId, userDetails);
        return updatedUser.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to update user with ID: " + userId));
    }

    /**
     * Delete a user by ID
     * DELETE /api/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            throw new InvalidRequestException("Invalid user ID");
        }
        if (!userService.userExists(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        throw new RuntimeException("Failed to delete user with ID: " + userId);
    }
}
