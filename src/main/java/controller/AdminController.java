package controller;

import dao.TransportDao;
import dao.TransportDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import model.Transport;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminController")
public class AdminController extends HttpServlet {

    final static private Logger logger = LogManager.getLogger(AdminController.class);

    private static final Long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "app/addUser.jsp";
    private static String INSERT_OR_EDIT_DRIVER = "app/addDriver.jsp";
    private static String LIST_USER = "app/Admin.jsp";
    private UserDao dao;
    private TransportDao transportDao;

    public AdminController() {
        super();
        dao = new UserDaoImpl();
        transportDao = new TransportDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setRole(User.Role.valueOf(request.getParameter("role")).getId());
        String userId = request.getParameter("id");

        if (userId == null || userId.isEmpty()) {
            dao.add(user);
        } else {
            user.setId(Long.parseLong(userId));
            dao.update(user);
        }

        if (user.getRole() == User.Role.DRIVER) {
            Transport transport = new Transport();
            transport.setMaxMass(Float.parseFloat(request.getParameter("maxMass")));
            transport.setMaxVolume(Float.parseFloat(request.getParameter("maxVolume")));
            transport.setIsAutoWorks(Boolean.parseBoolean(request.getParameter("isAutoWorks")));
            transport.setIsAutoAvailable(Boolean.parseBoolean(request.getParameter("isAutoAvailable")));
            transport.setDriver(dao.getBy(user.getLogin())); //getting user with id from db
            String transportId = request.getParameter("transportId");

            if (transportId == null || transportId.isEmpty()) {
                transportDao.add(transport);
            } else {
                transport.setId(Long.parseLong(transportId));
                transportDao.update(transport);
            }
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAll());
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if ("edit".equalsIgnoreCase(action)) {
            Long userId = Long.parseLong(request.getParameter("id"));
            User user = dao.getBy(userId);
            if (user.getRole() == User.Role.DRIVER) { //if role == driver, then attribute transport is needed
                forward = INSERT_OR_EDIT_DRIVER;
                Transport transport = transportDao.getBy(user);
                request.setAttribute("transport", transport);
            } else {
                forward = INSERT_OR_EDIT;
            }
            request.setAttribute("jspUser", user); //attribute user is needed for any role
        } else if ("insertManager".equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT;
        } else if ("insertDriver".equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT_DRIVER;
        } else {
            forward = LIST_USER;
            request.setAttribute("users", dao.getAll());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

}
