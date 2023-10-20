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
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.validation.Validator;

@WebServlet("/schoolapp/update")
public class UpdateTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	/**
	 * Handles GET requests to the teacher update page.
	 *
	 * @param request  The HttpServletRequest object containing the client's request.
	 * @param response The HttpServletResponse object used for sending responses.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward the request to the "teacherupdate.jsp" page to display the teacher update form.
		request.getRequestDispatcher("/schoolapp/static/templates/teacherupdate.jsp")
				.forward(request, response);
	}

	/**
	 * Handles POST requests to update a teacher's information.
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

		// Retrieve teacher information from the request's parameters.
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		TeacherDTO newTeacherDTO = new TeacherDTO();
		newTeacherDTO.setId(id);
		newTeacherDTO.setFirstname(firstname);
		newTeacherDTO.setLastname(lastname);
		request.setAttribute("insertedTeacher", newTeacherDTO);

		try {
			// Validate the new teacher information and update the teacher using the teacher service.
			String error = Validator.validate(newTeacherDTO);
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
						.forward(request, response);
			}

			teacherServ.updateTeacher(newTeacherDTO);
			request.setAttribute("teacher", newTeacherDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/teacherupdated.jsp")
					.forward(request, response);
		} catch (TeacherNotFoundException | TeacherDAOException e) {
			// Handle exceptions by setting appropriate attributes and forwarding to the error page.
			String message = e.getMessage();
			request.setAttribute("isError", true);
			request.setAttribute("teacher", newTeacherDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/teacherupdated.jsp")
					.forward(request, response);
		}
	}
}
