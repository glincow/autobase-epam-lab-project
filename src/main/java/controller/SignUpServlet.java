package controller;

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
import java.util.List;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger(SignUpServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = "Customer";

        UserDaoImpl dao = new UserDaoImpl();
        List<User> allUsers = dao.getAll();
        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                response.sendRedirect("/sign-up.jsp");
                return;
            }
        }

        User user = new User(1L, login, name, password, role);
        dao.add(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect("/app/Customer.jsp");

    }
}
