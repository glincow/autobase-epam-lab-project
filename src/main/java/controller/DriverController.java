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
    private static String STATUS_EDIT = "/DriverStatus.jsp";
    private static String LIST_RIDE = "app/Driver.jsp";
    private RideDao rideDao;
    private TransportDao transportDao;

    public DriverController() {
        super();
        rideDao = new RideDaoImpl();
        transportDao = new TransportDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Ride ride = new Ride();
        ride.setName(request.getParameter("name"));
        ride.setMass(Float.parseFloat(request.getParameter("mass")));
        ride.setVolume(Float.parseFloat(request.getParameter("volume")));
        ride.setStatus(request.getParameter("status"));
        String rideId = request.getParameter("id");
        if (rideId == null || rideId.isEmpty()) {
            rideDao.add(ride);
        } else {
            ride.setId(Long.parseLong(rideId));
            rideDao.update(ride);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_RIDE);
        request.setAttribute("rides", rideDao.getAll());
        view.forward(request, response);*/
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
            request.setAttribute("rides", rideDao.getByExecutor(transport.getId()));
        } else if (action.equalsIgnoreCase("statusEdit")){ //TODO: must work properly
            forward = STATUS_EDIT;
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = rideDao.getById(rideId);
            request.setAttribute("ride", ride);
        } else {
            forward = LIST_RIDE;
            request.setAttribute("transport", transport);
            request.setAttribute("rides", rideDao.getByExecutor(transport.getId()));
//            request.setAttribute("rides", rideDao.getAll());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
