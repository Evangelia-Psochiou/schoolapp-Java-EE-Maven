package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;

/**
 * The **ITeacherDAO** interface provides a set of methods for performing
 * CRUD (Create, Read, Update, Delete) operations related to teacher entities
 * in the data source.
 */
public interface ITeacherDAO {

    /**
     * **Inserts** a new teacher record into the data source.
     *
     * @param teacher The **Teacher** object to be inserted.
     * @return The inserted **Teacher**.
     * @throws TeacherDAOException If an error occurs during insertion.
     */
    Teacher insert(Teacher teacher) throws TeacherDAOException;

    /**
     * **Updates** an existing teacher record in the data source.
     *
     * @param teacher The **Teacher** object to be updated.
     * @return The updated **Teacher**.
     * @throws TeacherDAOException If an error occurs during the update.
     */
    Teacher update(Teacher teacher) throws TeacherDAOException;

    /**
     * **Deletes** a teacher record from the data source using its unique identifier (ID).
     *
     * @param id The unique identifier of the teacher to be deleted.
     * @throws TeacherDAOException If an error occurs during deletion.
     */
    void delete(int id) throws TeacherDAOException;

    /**
     * Retrieves a list of **teachers with a specific last name** from the data source.
     *
     * @param lastname The last name of the teachers to be retrieved.
     * @return A list of matching **Teacher** objects.
     * @throws TeacherDAOException If an error occurs during retrieval.
     */
    List<Teacher> getByLastname(String lastname) throws TeacherDAOException;

    /**
     * Retrieves a teacher record from the data source by its unique identifier (ID).
     *
     * @param id The unique identifier of the teacher to be retrieved.
     * @return The matching **Teacher** object.
     * @throws TeacherDAOException If an error occurs during retrieval.
     */
    Teacher getById(int id) throws TeacherDAOException;
}

