package gr.aueb.cf.schoolapp.validation;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;


/**
 * The **Validator** class provides methods for validating data input for different entities within the application.
 * It includes validation methods for TeacherDTO and UserDTO objects.
 */
public class Validator {

    /**
     * Constructs a new Validator instance.
     */
    public Validator() {
    }

    /**
     * Validates a TeacherDTO object.
     *
     * @param dto The TeacherDTO object to validate.
     * @return An error message if validation fails, or an empty string if validation succeeds.
     */
    public static String validate(TeacherDTO dto) {
        if (dto.getFirstname().equals("")) {
            return "Firstname: Empty";
        }

        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32) {
            return "Firstname: Length";
        }

        if (dto.getLastname().equals("")) {
            return "Lastname: Empty";
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32) {
            return "Lastname: Length";
        }


        return "";
    }

    /**
     * Validates a UserDTO object.
     *
     * @param userDTO The UserDTO object to validate.
     * @return An error message if validation fails, or an empty string if validation succeeds.
     */
    public static String validateUser(UserDTO userDTO) {
        IUserDAO userDAO = new UserDAOImpl();
        UserServiceImpl userService = new UserServiceImpl(userDAO);
        if (userDTO.getUsername().equals("")) {
            return "Username is required";
        }

        if (userDTO.getPassword().length() < 3 || userDTO.getPassword().length() > 10) {
            return "Password must be between 3 and 10 characters.";
        }

        return "";
    }
}
