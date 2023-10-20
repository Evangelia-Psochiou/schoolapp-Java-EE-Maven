package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;


import java.util.ArrayList;
import java.util.List;

/**
 * The **UserServiceImpl** class provides services for managing User entities.
 * It serves as an intermediary between the data access layer (DAO) and the rest of the application.
 * This class contains methods for inserting, updating, and deleting users, as well as retrieving users by their username.
 */
public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    /**
     * Constructs a new **UserServiceImpl** instance with the specified User Data Access Object (DAO).
     *
     * @param userDAO The User Data Access Object used for interacting with the database.
     */
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Inserts a new user into the database based on the provided UserDTO.
     *
     * @param userToInsert The UserDTO representing the user to be inserted.
     * @return The inserted User entity.
     * @throws UserDAOException If there is an issue with the data access layer during insertion.
     */
    @Override
    public User insertUser(UserDTO userToInsert) throws UserDAOException {
        if (userToInsert == null || userToInsert.getUsername().isEmpty() || userToInsert.getPassword().isEmpty()) {
            throw new UserDAOException("Required user fields have not been completed.");
        }

        try {
            User user = mapUser(userToInsert);
            return userDAO.insert(user);
        } catch (UserDAOException e) {
            throw e;
        }
    }

    /**
     * Updates an existing user's information in the database based on the provided UserDTO.
     *
     * @param userToUpdate The UserDTO representing the user to be updated.
     * @return The updated User entity.
     * @throws UserDAOException If there is an issue with the data access layer during update.
     * @throws UserNotFoundException If the specified user is not found.
     */
    @Override
    public User updateUser(UserDTO userToUpdate) throws UserDAOException, UserNotFoundException {
        if (userToUpdate == null) return null;

        try {
            if (userDAO.getById(userToUpdate.getId()) == null) {
                throw new UserNotFoundException("User with id " + userToUpdate.getId() + " was not found");
            }
            User user = mapUser(userToUpdate);

            return userDAO.update(user);
        } catch (UserDAOException | UserNotFoundException e) {
            throw e;
        }
    }

    /**
     * Deletes a user from the database based on the user's ID.
     *
     * @param id The ID of the user to be deleted.
     * @throws UserDAOException If there is an issue with the data access layer during deletion.
     * @throws UserNotFoundException If the specified user is not found.
     */
    @Override
    public void deleteUser(int id) throws UserDAOException, UserNotFoundException {
        try {
            if (userDAO.getById(id) == null) {
                throw new UserNotFoundException("User with id " + id + " was not found");
            }
            userDAO.delete(id);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Retrieves a list of users with a given username from the database.
     *
     * @param username The username of the users to retrieve.
     * @return A list of User entities matching the specified username.
     * @throws UserDAOException If there is an issue with the data access layer during retrieval.
     */
    @Override
    public List<User> getUsersByUsername(String username) throws UserDAOException {
        List<User> users = new ArrayList<>();
        if (username == null) return users;

        try {
            users = userDAO.getByUsername(username);
            return users;
        } catch (UserDAOException e) {
            throw e;
        }
    }

    // Private method for mapping a UserDTO to a User entity
    private User mapUser(UserDTO dto) {
        return new User(dto.getId(),dto.getUsername(),dto.getPassword());
    }
}
