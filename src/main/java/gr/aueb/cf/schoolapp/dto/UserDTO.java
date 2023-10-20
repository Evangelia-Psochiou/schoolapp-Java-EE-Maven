package gr.aueb.cf.schoolapp.dto;

/**
 * The **UserDTO** class represents a Data Transfer Object (DTO) for a User entity.
 * It contains the attributes necessary for transferring user-related data between
 * different layers of the application, such as from the database to the user interface.
 */
public class UserDTO {
    private int id;             // The unique identifier of the user.
    private String username;    // The username of the user.
    private String password;    // The password of the user.

    /**
     * Default constructor for the **UserDTO** class.
     */
    public UserDTO() {}

    /**
     * Parameterized constructor for the **UserDTO** class.
     *
     * @param id The unique identifier of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public UserDTO(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The unique identifier of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The unique identifier of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
