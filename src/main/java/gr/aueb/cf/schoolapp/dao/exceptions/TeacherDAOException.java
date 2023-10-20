package gr.aueb.cf.schoolapp.dao.exceptions;
/**
 * This is a custom exception class used for handling exceptions specific to the Teacher DAO (Data Access Object).
 * It extends the standard Exception class to provide custom error messages and handle exceptions that occur during
 * interactions with the Teacher DAO.
 */
public class TeacherDAOException extends Exception {

    /**
     * A constant field used to ensure class compatibility during serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new TeacherDAOException with the specified error message.
     *
     * @param message A string describing the exception and the error that occurred.
     */
    public TeacherDAOException(String message) {
        super(message);
    }
}
