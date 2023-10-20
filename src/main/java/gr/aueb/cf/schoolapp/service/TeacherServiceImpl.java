package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * The **TeacherServiceImpl** class provides services for managing Teacher entities.
 * It acts as an intermediary between the data access layer (DAO) and the rest of the application.
 * This class contains methods for inserting, updating, and deleting teachers, as well as retrieving teachers by their last name.
 */
public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherDAO teacherDAO;

    /**
     * Constructs a new **TeacherServiceImpl** instance with the specified Teacher Data Access Object (DAO).
     *
     * @param teacherDAO The Teacher Data Access Object used for interacting with the database.
     */
    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    /**
     * Inserts a new teacher into the database based on the provided TeacherDTO.
     *
     * @param teacherToInsert The TeacherDTO representing the teacher to be inserted.
     * @return The inserted Teacher entity.
     * @throws TeacherDAOException If there is an issue with the data access layer during insertion.
     */
    @Override
    public Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDAOException {
        if (teacherToInsert == null || teacherToInsert.getFirstname().isEmpty() || teacherToInsert.getLastname().isEmpty()) {
            throw new TeacherDAOException("Required teacher fields have not been completed.");
        }

        try {
            Teacher teacher = mapTeacher(teacherToInsert);
            return teacherDAO.insert(teacher);

        } catch (TeacherDAOException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    /**
     * Updates an existing teacher's information in the database based on the provided TeacherDTO.
     *
     * @param teacherToUpdate The TeacherDTO representing the teacher to be updated.
     * @return The updated Teacher entity.
     * @throws TeacherDAOException If there is an issue with the data access layer during update.
     * @throws TeacherNotFoundException If the specified teacher is not found.
     */
    @Override
    public Teacher updateTeacher(TeacherDTO teacherToUpdate)
            throws TeacherDAOException, TeacherNotFoundException {

        if (teacherToUpdate == null) return null;

        try {
            if (teacherDAO.getById(teacherToUpdate.getId()) == null) {
                throw new TeacherNotFoundException("Teacher with id " + teacherToUpdate.getId()
                        + " was not found");
            }

            Teacher teacher = mapTeacher(teacherToUpdate);
            return teacherDAO.update(teacher);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    /**
     * Deletes a teacher from the database based on the teacher's ID.
     *
     * @param id The ID of the teacher to be deleted.
     * @throws TeacherDAOException If there is an issue with the data access layer during deletion.
     * @throws TeacherNotFoundException If the specified teacher is not found.
     */
    @Override
    public void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException {
        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }
            teacherDAO.delete(id);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    /**
     * Retrieves a list of teachers with a given last name from the database.
     *
     * @param lastname The last name of the teachers to retrieve.
     * @return A list of Teacher entities matching the specified last name.
     * @throws TeacherDAOException If there is an issue with the data access layer during retrieval.
     */
    @Override
    public List<Teacher> getTeachersByLastname(String lastname)
            throws TeacherDAOException {
        List<Teacher> teachers = new ArrayList<>();
        if (lastname == null) return teachers;

        try {
            teachers = teacherDAO.getByLastname(lastname);
            return teachers;
        } catch (TeacherDAOException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    // Private method for mapping a TeacherDTO to a Teacher entity
    private Teacher mapTeacher(TeacherDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}
