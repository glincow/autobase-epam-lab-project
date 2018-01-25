package controller;

import dao.TransportDao;
import dao.TransportDaoImpl;
import model.Ride;
import dao.RideDao;
import dao.RideDaoImpl;
import model.Transport;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DriverController")
public class DriverController extends HttpServlet {

    private static final Long serialVersionUID = 1L;
    private static String STATUS_EDIT = "app/TransportStatus.jsp";
    private static String LIST_RIDE = "app/Driver.jsp";
    private RideDao rideDao;
    private TransportDao transportDao;

    public DriverController() {
        super();
        rideDao = new RideDaoImpl();
        transportDao = new TransportDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Transport transport = transportDao.getBy(user);
        transport.setIsAutoWorks(Boolean.parseBoolean(request.getParameter("isAutoWorks")));
        transport.setIsAutoAvailable(Boolean.parseBoolean(request.getParameter("isAutoAvailable")));
        transportDao.update(transport);
        RequestDispatcher view = request.getRequestDispatcher(LIST_RIDE);
        request.setAttribute("transport", transport);
        request.setAttribute("rides", rideDao.getByExecutor(transport.getId()));
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Transport transport = transportDao.getBy(user);

        if (action.equalsIgnoreCase("finishRide")){
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = rideDao.getById(rideId);
            ride.setStatus("FINISHED");
            rideDao.update(ride);
            forward = LIST_RIDE;
            request.setAttribute("transport", transport);
            request.setAttribute("rides", rideDao.getByExecutor(transport.getId()));
        } else if (action.equalsIgnoreCase("statusEdit")) {
            forward = STATUS_EDIT;
            request.setAttribute("transport", transport);
        } else {
            forward = LIST_RIDE;
            request.setAttribute("transport", transport);
            request.setAttribute("rides", rideDao.getByExecutor(transport.getId()));
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
