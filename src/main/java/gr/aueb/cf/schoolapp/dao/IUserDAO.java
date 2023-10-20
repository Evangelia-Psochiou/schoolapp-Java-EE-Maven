package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;

import java.util.List;

/**
 * The **IUserDAO** interface provides a set of methods for performing
 * CRUD (Create, Read, Update, Delete) operations related to user entities
 * in the data source.
 */
public interface IUserDAO {

    /**
     * **Inserts** a new user record into the data source.
     *
     * @param user The **User** object to be inserted.
     * @return The inserted **User**.
     * @throws UserDAOException If an error occurs during insertion.
     */
    User insert(User user) throws UserDAOException;

    /**
     * **Updates** an existing user record in the data source.
     *
     * @param user The **User** object to be updated.
     * @return The updated **User**.
     * @throws UserDAOException If an error occurs during the update.
     */
    User update(User user) throws UserDAOException;

    /**
     * **Deletes** a user record from the data source using its unique identifier (ID).
     *
     * @param id The unique identifier of the user to be deleted.
     * @throws UserDAOException If an error occurs during deletion.
     */
    void delete(int id) throws UserDAOException;

    /**
     * Retrieves a user record from the data source by its unique identifier (ID).
     *
     * @param id The unique identifier of the user to be retrieved.
     * @return The matching **User** object.
     * @throws UserDAOException If an error occurs during retrieval.
     */
    User getById(int id) throws UserDAOException;

    /**
     * Retrieves a list of **users with a specific username** from the data source.
     *
     * @param username The username of the users to be retrieved.
     * @return A list of matching **User** objects.
     * @throws UserDAOException If an error occurs during retrieval.
     */
    List<User> getByUsername(String username) throws UserDAOException;

    /**
     * Checks if a user with the given **username** and **password** exists and is valid.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return **true** if the user is valid, **false** otherwise.
     */
    boolean isUserValid(String username, String password);
}

