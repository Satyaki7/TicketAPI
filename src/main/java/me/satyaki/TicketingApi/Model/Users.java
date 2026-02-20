package me.satyaki.TicketingApi.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Users entity representing a user in the Ticketing API system.
 * This class maps to the "users" table in the database.
 * 
 * <p>
 * Fields:
 * </p>
 * <ul>
 * <li>{@link #userId} - Unique identifier for the user (UUID)</li>
 * <li>{@link #name} - Full name of the user</li>
 * <li>{@link #email} - Email address of the user</li>
 * <li>{@link #password} - Encrypted password of the user</li>
 * <li>{@link #role} - Role of the user in the system (ADMIN, USER,
 * ORGANIZER)</li>
 * <li>{@link #lastLogin} - Timestamp of the user's last login</li>
 * </ul>
 * 
 * @author Satyaki
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "users")
public class Users {

    /**
     * Unique identifier for the user.
     * Generated automatically using UUID strategy.
     * This is the primary key for the Users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    String userId;

    /**
     * The full name of the user.
     * Represents the user's display name in the system.
     */
    @Getter
    @Setter
    String name;

    /**
     * The email address of the user.
     * Should be unique across the system.
     * Used for authentication and communication purposes.
     */
    @Getter
    @Setter
    String email;

    /**
     * The encrypted password of the user.
     * Should be securely hashed using a strong algorithm.
     * Never transmitted or logged in plain text.
     */
    @Getter
    @Setter
    String password;

    /**
     * The role of the user in the system.
     * Possible values: ADMIN, USER, ORGANIZER.
     * Used for authorization and access control.
     * Determines what operations the user can perform in the system.
     */
    @Getter
    @Setter
    String role;

    /**
     * The timestamp of the user's last login.
     * Updated whenever the user logs into the system.
     * Can be null if the user has never logged in.
     * Useful for tracking user activity and security audits.
     */
    @Getter
    @Setter
    Date lastLogin;

    /**
     * Default constructor for the Users entity.
     * Required by JPA for object instantiation during database operations.
     */
    public Users() {
    }
}
