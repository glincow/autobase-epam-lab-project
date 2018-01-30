package controller;

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

@WebServlet(name = "signOutServlet", urlPatterns = "/signOut")
public class SignOutServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger(SignOutServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        session.invalidate();

        session = request.getSession();
        session.setAttribute("language", language);
        response.sendRedirect("/signIn");
        if (user != null) {
            LOGGER.info("User " + user.getLogin() + " logged out");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
