package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {
        /**
         * Inserts a {@link User} based on the data carried by the
         * {@link UserDTO}
         *
         * @param userToInsert
         * 			DTO object that contains the data.
         * @return
         * 			The inserted user instance
         * @throws UserDAOException
         * 			if any DAO exception happens.
         */
        User insertUser(UserDTO userToInsert) throws UserDAOException;

        /**
         * Updates {@link User} based on the data carried by the
         * {@link UserDTO}.
         *
         * @param userToUpdate
         * 			DTO object that contains the data
         * 			of the new {@link User}
         * @return
         * 			the update instance of the {@link User}.
         * @throws UserDAOException
         * 			if no User identified by their id
         * 			was found.
         * @throws UserNotFoundException
         * 			if any DAO exception happens.
         */
        User updateUser(UserDTO userToUpdate) throws UserDAOException, UserNotFoundException;

        /**
         * Deletes a {@link User} based on the data carried by the
         * {@link UserDTO}
         *
         * @param id
         * 			the id of the user to be deleted.
         * @throws UserDAOException
         * 			if any User needed to be deleted
         * 			was not found.
         * @throws UserNotFoundException
         * 			if any error happens between the driver
         * 			and the server at the DAO level.
         */
        void deleteUser(int id) throws UserDAOException, UserNotFoundException;

        /**
         * Searches and gets back to the caller a list
         * of the {@link User} objects identified
         * by their username or the username's initial letters.
         *
         * @param username
         * 				a String object that contains the
         * 				username or the letters that the username
         * 				starts with, of the {@link User}
         * 				objects we are looking for.
         * @return
         * 			a List that contains the results of
         * 			the search, that is a list of {@list Users}.
         *
         * @throws UserDAOException
         * 			if any error happens between the driver
         * 			and the server.
         */
        List<User> getUsersByUsername(String username) throws UserDAOException;

}
