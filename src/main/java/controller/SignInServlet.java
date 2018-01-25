package controller;

import dao.EmptyResultDataAccessException;
import dao.UserDaoImpl;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.soap.SOAPBinding;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDaoImpl dao = new UserDaoImpl();
        User user;
        try {
            user = dao.getBy(login);
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect("sign-in.jsp");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {

            User.Role role = user.getRole();
            switch (role) {
                case ADMIN:
                    response.sendRedirect("app/Admin.jsp");
                    break;
                case MANAGER:
                    response.sendRedirect("app/Manager.jsp");
                    break;
                case DRIVER:
                    response.sendRedirect("app/Driver.jsp");
                    break;
                case CUSTOMER:
                    response.sendRedirect("app/Customer.jsp");
                    break;
                default:
                    response.sendRedirect("app/Customer.jsp");
                    break;
            }
            LOGGER.info("User " + user.getLogin() + " signed in as " + role);
        } else {
            session.setAttribute("user", null);
            response.sendRedirect("/sign-in.jsp");
        }
    }
}
