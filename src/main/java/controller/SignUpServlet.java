package controller;

import dao.exceptions.EmptyResultDataAccessException;
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
import java.io.IOException;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger(SignUpServlet.class);
    private UserDao dao;

    public SignUpServlet() {
        super();
        dao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User.Role role = User.Role.CUSTOMER;

        dao = new UserDaoImpl();
        try {
            dao.getBy(login);
        } catch (EmptyResultDataAccessException e) {
            User user = new User(1L, login, name, password, role);
            dao.add(user);
            user = dao.getBy(login);
            user.setPassword("");
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/CustomerController?action=");
            LOGGER.info("User " + user.getLogin() + " registered");
            LOGGER.info("User " + user.getLogin() + " logged in as " + user.getRole());
            return;
        }
        request.getRequestDispatcher("/sign-up.jsp").forward(request, response);
        LOGGER.info("Login " + login + " already exists");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/sign-up.jsp").forward(request, response);
    }
}
