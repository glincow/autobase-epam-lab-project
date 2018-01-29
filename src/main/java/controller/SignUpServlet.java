package controller;

import dao.EmptyResultDataAccessException;
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

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger(SignUpServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User.Role role = User.Role.CUSTOMER;

        UserDaoImpl dao = new UserDaoImpl();
        try {
            User user = dao.getBy(login);
        } catch (EmptyResultDataAccessException e) {
            User user = new User(1L, login, name, password, role);
            dao.add(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/CustomerController?action=");
            return;
        }
        request.getRequestDispatcher("/sign-up.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/sign-up.jsp").forward(request,response);
    }
}
