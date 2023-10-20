package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Teacher;

/**
 * The **TeacherNotFoundException** class is an exception thrown when a requested Teacher entity is not found.
 * This exception can occur, for example, when attempting to retrieve or manipulate a Teacher that does not exist.
 */
public class TeacherNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    /**
     * Constructs a **TeacherNotFoundException** exception with a specific error message.
     *
     * @param message A custom error message to describe the exceptional condition.
     */
    public TeacherNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a **TeacherNotFoundException** exception based on a specific Teacher instance.
     * This version of the constructor generates an error message indicating that the Teacher with a
     * specific ID does not exist.
     *
     * @param teacher The Teacher entity for which the exception is raised.
     */
    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with ID " + teacher.getId() + " does not exist");
    }
}
