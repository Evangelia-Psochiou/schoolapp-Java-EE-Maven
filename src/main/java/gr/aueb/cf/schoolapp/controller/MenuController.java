package gr.aueb.cf.schoolapp.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/schoolapp/menu")
public class MenuController extends HttpServlet {
    /**
     * Handles GET requests to the menu page.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object used for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the response content type to HTML with UTF-8 character encoding.
        response.setContentType("text/html; charset=UTF-8");

        // Forward the request to the "menu.jsp" page to display the application menu.
        request.getRequestDispatcher("/schoolapp/static/templates/menu.jsp")
                .forward(request, response);
    }
}

