package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.validation.Validator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/schoolapp/update-user")
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    /**
     * Handles GET requests to the user update page.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object used for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the "userupdate.jsp" page to display the user update form.
        request.getRequestDispatcher("/schoolapp/static/templates/userupdate.jsp")
                .forward(request, response);
    }

    /**
     * Handles POST requests to update a user's information.
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

        // Retrieve user information from the request's parameters.
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.setId(id);
        newUserDTO.setUsername(username);
        newUserDTO.setPassword(password);
        request.setAttribute("insertedUser", newUserDTO);

        try {
            // Validate the new user information and update the user using the user service.
            String error = Validator.validateUser(newUserDTO);
            if (!error.equals("")) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }

            userService.updateUser(newUserDTO);
            request.setAttribute("user", newUserDTO);
            request.getRequestDispatcher("/schoolapp/static/templates/userupdated.jsp")
                    .forward(request, response);
        } catch (UserNotFoundException | UserDAOException e) {
            // Handle exceptions by setting appropriate attributes and forwarding to the error page.
            String message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("user", newUserDTO);
            request.getRequestDispatcher("schoolapp/static/templates/userupdated.jsp")
                    .forward(request, response);
        }
    }
}
