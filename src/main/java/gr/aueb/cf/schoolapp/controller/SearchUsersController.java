package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchUsersController", value = "/schoolapp/users-search")
public class SearchUsersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserDAO userDAO = new UserDAOImpl();
    IUserService userService = new UserServiceImpl(userDAO);

    /**
     * Handles GET requests to the user search page.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object used for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the "usersmenu.jsp" page to display the user search menu.
        request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to search for users by their username.
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

        // Retrieve the username from the request's parameters.
        String username = request.getParameter("username").trim();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        String message = "";

        try {
            // Search for users by their username using the user service.
            List<User> users = userService.getUsersByUsername(userDTO.getUsername());

            if (users.size() == 0) {
                // If no users are found, set the "usersNotFound" attribute and forward to the user search menu.
                request.setAttribute("usersNotFound", true);
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }

            // Set the "users" attribute and forward to the users page to display the search results.
            request.setAttribute("users", users);
            request.getRequestDispatcher("/schoolapp/static/templates/users.jsp").forward(request, response);
        } catch (UserDAOException e) {
            // If an exception occurs during the search, handle it by setting the error attributes and forwarding.
            message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("schoolapp/usersmenu.jsp")
                    .forward(request, response);
        }
    }
}