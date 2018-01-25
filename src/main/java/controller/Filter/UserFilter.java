package controller.Filter;

import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

public interface UserFilter extends Filter {

    default void checkRights(ServletRequest servletRequest, ServletResponse servletResponse,
                             FilterChain filterChain, Logger LOGGER, String role) throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) servletRequest);
        HttpServletResponse resp = ((HttpServletResponse) servletResponse);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            if (user.getRole().equals(role)) {
                LOGGER.info("User " + user + " entry to page:  " + req.getRequestURI());
                filterChain.doFilter(req, resp);
            } else {
                LOGGER.info("Illegal entry to page:  " + req.getRequestURI());
                //send to error page
                throw new RuntimeException();
            }
        } else {
            LOGGER.info("Illegal entry to page:  " + req.getRequestURI());
            //Send to signup page
        }
    }
}
