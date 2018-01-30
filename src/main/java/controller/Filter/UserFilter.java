package controller.Filter;

import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

public interface UserFilter extends Filter {

    default void checkRights(ServletRequest servletRequest, ServletResponse servletResponse,
                             FilterChain filterChain, Logger LOGGER, User.Role role) throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) servletRequest);
        HttpServletResponse resp = ((HttpServletResponse) servletResponse);
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            if (user.getRole().equals(role)) {
                LOGGER.info("User " + user.getLogin() + " entry to page:  " + req.getRequestURI());
                resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                resp.setDateHeader("Expires", 0); // Proxies.
                filterChain.doFilter(req, resp);
            } else {
                LOGGER.info("Illegal entry to page:  " + req.getRequestURI());
                resp.sendRedirect("/err.jsp");
            }
        } else {
            LOGGER.info("Illegal entry to page:  " + req.getRequestURI());
            resp.sendRedirect("/signIn");
        }
    }
}
