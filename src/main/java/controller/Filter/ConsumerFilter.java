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

@WebFilter(filterName = "consumerFilter", urlPatterns = "/app/Consumer.jsp")
public class ConsumerFilter implements UserFilter {

    private final static Logger LOGGER = LogManager.getLogger(ConsumerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        checkRights(servletRequest, servletResponse, filterChain, LOGGER, "Consumer");
    }

    @Override
    public void destroy() {

    }
}