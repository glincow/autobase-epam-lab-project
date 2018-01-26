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
        /*Ride ride = new Ride();
        ride.setName(request.getParameter("name"));
        ride.setMass(Float.parseFloat(request.getParameter("mass")));
        ride.setVolume(Float.parseFloat(request.getParameter("volume")));
        ride.setStatus(Ride.Status.valueOf(request.getParameter("status").toUpperCase()));
        String rideId = request.getParameter("id");
        if(rideId == null || rideId.isEmpty())
        {
            rideDao.add(ride);
        }
        else
        {
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

        if (action.equalsIgnoreCase("cancelRide")){ //TODO
            forward = AVAILABLE_TRANSPORT;
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = rideDao.getById(rideId);
            request.setAttribute("ride", ride);
        } else if (action.equalsIgnoreCase("transportList")){
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = rideDao.getById(rideId);
            List<Transport> transportList = transportDao.getSuitable(ride);
            forward = AVAILABLE_TRANSPORT;
            request.setAttribute("transportList", transportList);
            request.setAttribute("ride", ride); //ride is needed here to recieve it in chooseTransport (next if)
        } else if(action.equalsIgnoreCase("chooseTransport")) {
            Transport transport = transportDao.getBy(Long.parseLong(request.getParameter("id")));
            Ride ride = rideDao.getById(Long.parseLong(request.getParameter("rideId")));
            ride.setStatus(Ride.Status.IN_PROCESS);
            ride.setExecutor(transport);
            ride.setManager(user);
            rideDao.update(ride);
            forward = LIST_RIDE;
            request.setAttribute("rides", rideDao.getAll());
        } else {
            forward = LIST_RIDE;
//            request.setAttribute("rides", rideDao.getByStatus(Ride.Status.UNASSIGNED));
            request.setAttribute("rides", rideDao.getAll());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
