package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;


/**
 * A utility class for authenticating users based on their credentials.
 */
public class AuthenticationProvider {
    private static final IUserDAO userDao = new UserDAOImpl(); // Dependency on the UserDAOImpl implementation
    private AuthenticationProvider() {}

    /**
     * Authenticates a user with the given credentials.
     *
     * @param userDTO The UserDTO object containing the user's credentials (username and password).
     * @return The authenticated User object or null if authentication fails.
     */
    public static User authenticate(UserDTO userDTO) {
        if (!userDao.isUserValid(userDTO.getUsername(), userDTO.getPassword())) {
            return null; // Authentication failed
        }
        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword()); // Authentication successful
    }
}
