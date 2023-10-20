package gr.aueb.cf.schoolapp.dto;

/**
 * The **TeacherDTO** class represents a Data Transfer Object (DTO) for a Teacher entity.
 * It contains the attributes necessary for transferring teacher-related data between
 * different layers of the application, such as from the database to the user interface.
 */
public class TeacherDTO {
    private int id;             // The unique identifier of the teacher.
    private String firstname;   // The first name of the teacher.
    private String lastname;    // The last name of the teacher.

    /**
     * Default constructor for the **TeacherDTO** class.
     */
    public TeacherDTO() {}

    /**
     * Parameterized constructor for the **TeacherDTO** class.
     *
     * @param id The unique identifier of the teacher.
     * @param firstname The first name of the teacher.
     * @param lastname The last name of the teacher.
     */
    public TeacherDTO(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Gets the unique identifier of the teacher.
     *
     * @return The unique identifier of the teacher.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the teacher.
     *
     * @param id The unique identifier of the teacher.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the teacher.
     *
     * @return The first name of the teacher.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the first name of the teacher.
     *
     * @param firstname The first name of the teacher.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the last name of the teacher.
     *
     * @return The last name of the teacher.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the last name of the teacher.
     *
     * @param lastname The last name of the teacher.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
