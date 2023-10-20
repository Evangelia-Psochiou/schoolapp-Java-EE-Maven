package gr.aueb.cf.schoolapp.service.exceptions;


import gr.aueb.cf.schoolapp.model.User;

/**
 * The **UserNotFoundException** class is an exception thrown when a requested User entity is not found.
 * This exception can occur, for example, when attempting to retrieve or manipulate a User that does not exist.
 */
public class UserNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    /**
     * Constructs a **UserNotFoundException** exception with a specific error message.
     *
     * @param message A custom error message to describe the exceptional condition.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a **UserNotFoundException** exception based on a specific User instance.
     * This version of the constructor generates an error message indicating that the User with a
     * specific ID does not exist.
     *
     * @param user The User entity for which the exception is raised.
     */
    public UserNotFoundException(User user) {
        super("User with ID " + user.getId() + " does not exist");
    }
}

