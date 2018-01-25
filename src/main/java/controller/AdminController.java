package controller;


import dao.UserDao;
import dao.UserDaoImpl;
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

@WebServlet(name = "AdminController")
public class AdminController extends HttpServlet {

    final static private Logger logger = LogManager.getLogger(AdminController.class);

    private static final Long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/app/addUser.jsp";
    private static String LIST_USER = "/app/listUser.jsp";
    private UserDao dao;

    public AdminController() {
        super();
        dao = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        String userId = request.getParameter("id");
        if(userId == null || userId.isEmpty())
        {
            dao.add(user);
        }
        else
        {
            user.setId(Long.parseLong(userId));
            dao.update(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAll());
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            Long userId = Long.parseLong(request.getParameter("id"));
            User user = dao.getBy(userId);
            dao.delete(user);
            forward = LIST_USER;
            request.setAttribute("rides", dao.getAll());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            Long userId = Long.parseLong(request.getParameter("id"));
            User user = dao.getBy(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUsers")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAll());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

}
