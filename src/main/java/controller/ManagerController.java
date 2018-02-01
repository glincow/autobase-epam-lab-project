package controller;

import dao.RideDao;
import dao.RideDaoImpl;
import dao.TransportDao;
import dao.TransportDaoImpl;
import model.Ride;
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

@WebServlet(name = "ManagerController")
public class ManagerController extends HttpServlet {
    private static final Long serialVersionUID = 1L;
    private static String AVAILABLE_TRANSPORT = "app/TransportList.jsp";
    private static String LIST_RIDE = "app/Manager.jsp";
    private RideDao rideDao;
    private TransportDao transportDao;

    public ManagerController() {
        super();
        rideDao = new RideDaoImpl();
        transportDao = new TransportDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Transport transport = transportDao.getBy(Long.parseLong(request.getParameter("transpId")));
        Ride ride = rideDao.getById(Long.parseLong(request.getParameter("rideId")));
        ride.setStatus(Ride.Status.IN_PROCESS);
        ride.setExecutor(transport);
        ride.setManager(user);
        rideDao.update(ride);
        forward = LIST_RIDE;
        request.setAttribute("ridesUnassigned", rideDao.getByStatus(Ride.Status.UNASSIGNED));
        request.setAttribute("ridesInProcess", rideDao.getByManagerAndStatus(user, Ride.Status.IN_PROCESS));
        request.setAttribute("ridesFinished", rideDao.getByManagerAndStatus(user, Ride.Status.FINISHED));
        request.setAttribute("ridesCanceled", rideDao.getByStatus(Ride.Status.CANCELED));
//        request.setAttribute("rides", rideDao.getAll());

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if ("cancelRide".equalsIgnoreCase(action)){
            forward = AVAILABLE_TRANSPORT;
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = rideDao.getById(rideId);
            request.setAttribute("ride", ride);
        } else if ("transportList".equalsIgnoreCase(action)){
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = rideDao.getById(rideId);
            List<Transport> transportList = transportDao.getSuitable(ride);
            forward = AVAILABLE_TRANSPORT;
            request.setAttribute("transportList", transportList);
            request.setAttribute("ride", ride); //ride is needed here to recieve it in chooseTransport (next if)
        } else {
            forward = LIST_RIDE;
            request.setAttribute("ridesUnassigned", rideDao.getByStatus(Ride.Status.UNASSIGNED));
            request.setAttribute("ridesInProcess", rideDao.getByManagerAndStatus(user, Ride.Status.IN_PROCESS));
            request.setAttribute("ridesFinished", rideDao.getByManagerAndStatus(user, Ride.Status.FINISHED));
            request.setAttribute("ridesCanceled", rideDao.getByStatus(Ride.Status.CANCELED));
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
