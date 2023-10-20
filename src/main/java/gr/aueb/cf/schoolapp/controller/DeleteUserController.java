package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/schoolapp/user-delete")
public class DeleteUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserDAO userDAO = new UserDAOImpl();
    IUserService userService = new UserServiceImpl(userDAO);

    /**
     * Handles HTTP GET requests to delete a user.
     *
     * @param request  HttpServletRequest containing request parameters.
     * @param response HttpServletResponse for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the response content type to HTML with UTF-8 encoding.
        response.setContentType("text/html; charset=UTF-8");

        // Retrieve the 'id,' 'username,' and 'password' parameters from the GET request.
        int id = Integer.parseInt(request.getParameter("id").trim());
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        // Create a UserDTO to store user information.
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        try {
            // Attempt to delete the user using the user service.
            userService.deleteUser(id);

            // If successful, set the 'user' attribute and forward to a confirmation page.
            request.setAttribute("user",userDTO);
            request.getRequestDispatcher("/schoolapp/static/templates/userdeleted.jsp")
                    .forward(request, response);
        } catch (UserNotFoundException | UserDAOException e) {
            // If an exception occurs during deletion, set an error attribute and forward to an error page.
            request.setAttribute("deleteAPIError", true);
            request.getRequestDispatcher("/schoolapp/static/templates/users.jsp")
                    .forward(request, response);
        }
    }
}
