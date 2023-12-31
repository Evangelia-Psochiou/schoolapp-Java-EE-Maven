package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/schoolapp/user-insert")
public class InsertUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    /**
     * Handles POST requests to insert a user into the system.
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
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        request.setAttribute("insertedUser", userDTO);

        try {
            // Validate the user's data using a custom validator.
            String error = Validator.validateUser(userDTO);

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
                return;
            }

            // Hash the user's password using BCrypt and update the DTO.
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            userDTO.setPassword(hashedPassword);

            // Insert the user into the system using the userService.
            userService.insertUser(userDTO);

            // Redirect to a success page when the user is inserted.
            request.getRequestDispatcher("/schoolapp/static/templates/userinserted.jsp")
                    .forward(request, response);
        } catch (UserDAOException e) {
            // Handle any potential errors by setting an error message.
            request.setAttribute("error", "An error occurred while inserting the user");
            request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                    .forward(request, response);
        }
    }
}
