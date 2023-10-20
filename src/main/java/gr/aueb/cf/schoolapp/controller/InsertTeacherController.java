package gr.aueb.cf.schoolapp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.validation.Validator;

@WebServlet("/schoolapp/insert")
public class InsertTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	/**
	 * Handles POST requests to insert a teacher into the system.
	 *
	 * @param request  The HttpServletRequest object containing the client's request.
	 * @param response The HttpServletResponse object used for sending responses.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("error", "");
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setFirstname(firstname);
		teacherDTO.setLastname(lastname);
		request.setAttribute("insertedTeacher", teacherDTO);

		try {
			// Validate the teacher's data using a custom validator.
			String error = Validator.validate(teacherDTO);

			if (!error.isEmpty()) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
						.forward(request, response);
			}

			// Insert the teacher into the system using the teacher service.
			teacherServ.insertTeacher(teacherDTO);

			// Redirect to a success page when the teacher is inserted.
			request.getRequestDispatcher("/schoolapp/static/templates/teacherinserted.jsp")
					.forward(request, response);
		} catch (TeacherDAOException e) {
			// Handle any potential SQL-related errors by setting a flag.
			request.setAttribute("sqlError", true);
			request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
					.forward(request, response);
		}
	}
}