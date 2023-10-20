package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The **TeacherDAOImpl** class is an implementation of the **ITeacherDAO** interface
 * responsible for performing CRUD (Create, Read, Update, Delete) operations related to
 * teacher entities in the data source.
 */
public class TeacherDAOImpl implements ITeacherDAO {

    /**
     * Inserts a new teacher record into the data source.
     *
     * @param teacher The **Teacher** object to be inserted.
     * @return The inserted **Teacher**.
     * @throws TeacherDAOException If an error occurs during insertion.
     */
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            p.setString(1, firstname);
            p.setString(2, lastname);
            p.executeUpdate();
            return teacher;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace(); // logging
            throw new TeacherDAOException("SQL Error in Teacher " + teacher + " insertion");
        }
    }

    /**
     * Updates an existing teacher record in the data source.
     *
     * @param teacher The **Teacher** object to be updated.
     * @return The updated **Teacher**.
     * @throws TeacherDAOException If an error occurs during the update.
     */
    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            p.setString(1, firstname);
            p.setString(2, lastname);
            p.setInt(3, id);
            p.executeUpdate();
            return teacher;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher " + teacher.getLastname()
                    + " update");
        }
    }

    /**
     * Deletes a teacher record from the data source using its unique identifier (ID).
     *
     * @param id The unique identifier of the teacher to be deleted.
     * @throws TeacherDAOException If an error occurs during deletion.
     */
    @Override
    public void delete(int id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher with id = "
                    + id  + " deleted");
        }
    }

    /**
     * Retrieves a list of teachers with a specific last name from the data source.
     *
     * @param lastname The last name of the teachers to be retrieved.
     * @return A list of matching **Teacher** objects.
     * @throws TeacherDAOException If an error occurs during retrieval.
     */
    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        System.out.println("DAO " + lastname);
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
        ResultSet rs;
        List<Teacher> teachers = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, lastname + '%');
            rs = p.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getInt("ID"),rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher with lastname = " + lastname);
        }
    }

    /**
     * Retrieves a teacher record from the data source by its unique identifier (ID).
     *
     * @param id The unique identifier of the teacher to be retrieved.
     * @return The matching **Teacher** object.
     * @throws TeacherDAOException If an error occurs during retrieval.
     */
    @Override
    public Teacher getById(int id) throws TeacherDAOException {
        Teacher teacher = null;
        ResultSet rs;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, id);
            rs = p.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"),rs.getString("LASTNAME"));
            }
            return teacher;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher with id = " + id);
        }
    }
}
