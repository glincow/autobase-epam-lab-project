package controller.Filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "customerFilter", urlPatterns = "/app/Customer.jsp")
public class CustomerFilter implements UserFilter {

    private final static Logger LOGGER = LogManager.getLogger(CustomerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        checkRights(servletRequest, servletResponse, filterChain, LOGGER, "Customer");
    }

    @Override
    public void destroy() {

    }
}