package controller;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorController", urlPatterns = "/errRedirect")
public class ErrorController extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger(SignInServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User.Role role = User.Role.valueOf(request.getParameter("login").toUpperCase());
            switch (role) {
                case ADMIN:
                    response.sendRedirect("/AdminController?action=listUsers");
                    break;
                case MANAGER:
                    response.sendRedirect("/ManagerController?action=");
                    break;
                case DRIVER:
                    response.sendRedirect("/DriverController?action=");
                    break;
                case CUSTOMER:
                    response.sendRedirect("/CustomerController?action=");
                    break;
                default:
                    response.sendRedirect("/CustomerController?action=");
                    break;
            }
            LOGGER.info("User redirected back from error page");
    }
}
