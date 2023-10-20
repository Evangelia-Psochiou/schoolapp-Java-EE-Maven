package gr.aueb.cf.schoolapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

@WebServlet(name = "SearchTeachersController", value = "/schoolapp/search")
public class SearchTeachersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    /**
     * Handles GET requests to the search teachers page.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object used for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the "teachersmenu.jsp" page to display the search teachers menu.
        request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
                .forward(request, response);
    }

    /**
     * Handles POST requests to search for teachers by their last name.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object used for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the response content type to HTML with UTF-8 character encoding.
        response.setContentType("text/html; charset=UTF-8");

        // Retrieve the last name from the request's parameters.
        String lastname = request.getParameter("lastname").trim();
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setLastname(lastname);
        String message = "";

        try {
            // Search for teachers by their last name using the teacher service.
            List<Teacher> teachers = teacherService.getTeachersByLastname(teacherDTO.getLastname());

            if (teachers.size() == 0) {
                // If no teachers are found, set the "teachersNotFound" attribute and forward to the teachers menu.
                request.setAttribute("teachersNotFound", true);
                request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
                        .forward(request, response);
            }

            // Set the "teachers" attribute and forward to the teachers page to display the search results.
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/schoolapp/static/templates/teachers.jsp").forward(request, response);
        } catch (TeacherDAOException e) {
            // If an exception occurs during the search, handle it by setting the error attributes and forwarding.
            message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/schoolapp/teachersmenu.jsp").forward(request, response);
        }
    }
}
