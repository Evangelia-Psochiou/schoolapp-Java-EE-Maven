package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    /**
     * Handles GET requests to the login page.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object used for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login.jsp page for displaying the login form.
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to authenticate a user.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object used for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user's email and password from the request parameters.
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create a UserDTO object and set its username and password properties.
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(email);
        userDTO.setPassword(password);

        User user = null;
        // Authenticate the user using the AuthenticationProvider.
        user = AuthenticationProvider.authenticate(userDTO);

        // If the authentication fails, redirect the user back to the login page.
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // If the user is successfully authenticated:
        // 1. Create a session and store the user's username in it.
        HttpSession session = request.getSession(true);
        session.setAttribute("username", user.getUsername());

        // 2. Set the session's maximum inactive interval to 15 minutes (900 seconds).
        session.setMaxInactiveInterval(60 * 15);

        // 3. Create a JSESSIONID cookie and set its max age to the session's max inactive interval.
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(session.getMaxInactiveInterval());

        // 4. Add the JSESSIONID cookie to the response.
        response.addCookie(cookie);

        // 5. Redirect the user to the "/schoolapp/menu" page after successful authentication.
        response.sendRedirect(request.getContextPath() + "/schoolapp/menu");
    }
}
