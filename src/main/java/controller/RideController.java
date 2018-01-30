package controller;

import dao.RideDao;
import dao.RideDaoImpl;
import model.Ride;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RideController")
public class RideController extends HttpServlet {

    private static final Long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/ride.jsp";
    private static String LIST_RIDE = "/listRide.jsp";
    private RideDao dao;

    public RideController() {
        super();
        dao = new RideDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ride ride = new Ride();
        ride.setName(request.getParameter("name"));
        ride.setMass(Float.parseFloat(request.getParameter("mass")));
        ride.setVolume(Float.parseFloat(request.getParameter("volume")));
        ride.setStatus(Ride.Status.valueOf(request.getParameter("status").toUpperCase()));
        String rideId = request.getParameter("id");
        if (rideId == null || rideId.isEmpty()) {
            dao.add(ride);
        } else {
            ride.setId(Long.parseLong(rideId));
            dao.update(ride);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_RIDE);
        request.setAttribute("rides", dao.getAll());
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = dao.getById(rideId);
            dao.delete(ride);
            forward = LIST_RIDE;
            request.setAttribute("rides", dao.getAll());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = dao.getById(rideId);
            request.setAttribute("ride", ride);
        } else if (action.equalsIgnoreCase("listRides")) {
            forward = LIST_RIDE;
            request.setAttribute("rides", dao.getAll());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
