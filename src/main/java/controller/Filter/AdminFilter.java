package controller.Filter;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "adminFilter", urlPatterns = "/app/Admin.jsp")
public class AdminFilter implements UserFilter {

    private final static Logger LOGGER = LogManager.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        checkRights(servletRequest, servletResponse, filterChain, LOGGER, User.Role.ADMIN);
    }

    @Override
    public void destroy() {

    }
}