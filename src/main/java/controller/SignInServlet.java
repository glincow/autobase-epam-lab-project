package controller;

import dao.EmptyResultDataAccessException;
import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger(SignInServlet.class);
    private UserDao dao;

    public SignInServlet() {
        super();
        dao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user;
        try {
            user = dao.getBy(login);
        } catch (EmptyResultDataAccessException e) {
            doGet(request, response);
            LOGGER.info("Wrong login or password");
            return;
        }

        if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
            user.setPassword("");
            request.getSession().setAttribute("user", user);
            User.Role role = user.getRole();
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
            LOGGER.info("User " + user.getLogin() + " logged in as " + role);
        } else {
            doGet(request, response);
            LOGGER.info("Wrong login or password");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/sign-in.jsp").forward(request, response);
    }
}
