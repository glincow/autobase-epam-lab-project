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
import java.util.List;

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
        String action = request.getParameter("postAction");
        String forward="";
        Transport transport = transportDao.getBy(user);

        if ("finishRide".equalsIgnoreCase(action)){
            Long rideId = Long.parseLong(request.getParameter("rideId"));
            Ride ride = rideDao.getById(rideId);
            ride.setStatus(Ride.Status.FINISHED);
            rideDao.update(ride);
            forward = LIST_RIDE;
            request.setAttribute("transport", transport);
            request.setAttribute("rides", rideDao.getByExecutor(transport));
            request.setAttribute("activeRidesCount", rideDao.getByExecutorAndStatus(transport, Ride.Status.IN_PROCESS).size());
        } else {
            transport.setIsAutoWorks(Boolean.parseBoolean(request.getParameter("isAutoWorks")));
            transport.setIsAutoAvailable(Boolean.parseBoolean(request.getParameter("isAutoAvailable")));
            transportDao.update(transport);
            forward = LIST_RIDE;
            request.setAttribute("transport", transport);
            request.setAttribute("rides", rideDao.getByExecutor(transport));
            List<Ride> activeRides = rideDao.getByExecutorAndStatus(transport, Ride.Status.IN_PROCESS);
            if (!activeRides.isEmpty()) {
                request.setAttribute("activeRide", rideDao.getByExecutorAndStatus(transport, Ride.Status.IN_PROCESS).get(0));
            }
            request.setAttribute("activeRidesCount", activeRides.size());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Transport transport = transportDao.getBy(user);

        if ("statusEdit".equalsIgnoreCase(action)) {
            forward = STATUS_EDIT;
            request.setAttribute("transport", transport);
        } else {
            forward = LIST_RIDE;
            request.setAttribute("transport", transport);
            request.setAttribute("rides", rideDao.getByExecutor(transport));
            List<Ride> activeRides = rideDao.getByExecutorAndStatus(transport, Ride.Status.IN_PROCESS);
            if(!activeRides.isEmpty()){
                request.setAttribute("activeRide", rideDao.getByExecutorAndStatus(transport, Ride.Status.IN_PROCESS).get(0));
            }
            request.setAttribute("activeRidesCount", activeRides.size());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
