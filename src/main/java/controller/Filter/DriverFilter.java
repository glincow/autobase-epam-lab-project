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

@WebFilter(filterName = "driverFilter", urlPatterns = "/app/Driver.jsp")
public class DriverFilter implements UserFilter {

    private final static Logger LOGGER = LogManager.getLogger(DriverFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        checkRights(servletRequest, servletResponse, filterChain, LOGGER, User.Role.DRIVER);
    }

    @Override
    public void destroy() {

    }
}