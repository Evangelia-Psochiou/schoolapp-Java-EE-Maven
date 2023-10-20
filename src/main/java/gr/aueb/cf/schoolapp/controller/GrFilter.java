package gr.aueb.cf.schoolapp.controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebFilter(filterName = "GrFilter")
public class GrFilter implements Filter {
    /**
     * Initializes the filter.
     *
     * @param config Filter configuration data.
     * @throws ServletException if the filter encounters an error during initialization.
     */
    public void init(FilterConfig config) throws ServletException {
        // Initialization logic, if needed.
    }

    /**
     * Destroys the filter.
     */
    public void destroy() {
        // Cleanup logic, if needed.
    }

    /**
     * Filters a request and response.
     *
     * @param request  The ServletRequest object contains the client's request.
     * @param response The ServletResponse object used for sending responses.
     * @param chain    The FilterChain object for invoking the next filter or the resource.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Set the character encoding for the request to UTF-8.
        request.setCharacterEncoding("UTF-8");

        // Continue the filter chain to the next filter or the target servlet.
        chain.doFilter(request, response);
    }
}
