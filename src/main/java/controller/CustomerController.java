package controller;

import dao.RideDao;
import dao.RideDaoImpl;
import model.Ride;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerController", urlPatterns = "/CustomerController")
public class CustomerController extends HttpServlet {

    private static String INSERT_OR_EDIT = "app/Ride.jsp";
    private static String LIST_RIDE = "/app/Customer.jsp";
    private RideDao dao;

    public CustomerController() {
        super();
        dao = new RideDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User customer = (User) request.getSession().getAttribute("user");

        Ride ride = new Ride();
        ride.setName(request.getParameter("name"));
        ride.setMass(Float.parseFloat(request.getParameter("mass")));
        ride.setVolume(Float.parseFloat(request.getParameter("volume")));
        ride.setStatus(Ride.Status.valueOf(request.getParameter("status").toUpperCase()));
        ride.setCustomer(customer);
        String rideId = request.getParameter("id");
        if (rideId == null || rideId.isEmpty()) {
            dao.add(ride);
        } else {
            ride.setId(Long.parseLong(rideId));
            dao.update(ride);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_RIDE);
        request.setAttribute("ridesUnassigned", dao.getByCustomerAndStatus(customer, Ride.Status.UNASSIGNED));
        request.setAttribute("ridesInProcess", dao.getByCustomerAndStatus(customer, Ride.Status.IN_PROCESS));
        request.setAttribute("ridesFinished", dao.getByCustomerAndStatus(customer, Ride.Status.FINISHED));
        request.setAttribute("ridesCanceled", dao.getByCustomerAndStatus(customer, Ride.Status.CANCELED));
        //request.setAttribute("rides", dao.getByCustomer(customer));
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        User customer = (User) request.getSession().getAttribute("user");

        if ("cancel".equalsIgnoreCase(action)) {
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = dao.getById(rideId);
            ride.setStatus(Ride.Status.CANCELED);
            dao.update(ride);
            forward = LIST_RIDE;
            request.setAttribute("ridesUnassigned", dao.getByCustomerAndStatus(customer, Ride.Status.UNASSIGNED));
            request.setAttribute("ridesInProcess", dao.getByCustomerAndStatus(customer, Ride.Status.IN_PROCESS));
            request.setAttribute("ridesFinished", dao.getByCustomerAndStatus(customer, Ride.Status.FINISHED));
            request.setAttribute("ridesCanceled", dao.getByCustomerAndStatus(customer, Ride.Status.CANCELED));
            //request.setAttribute("rides", dao.getByCustomer(customer));
        } else if ("edit".equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT;
            Long rideId = Long.parseLong(request.getParameter("id"));
            Ride ride = dao.getById(rideId);
            request.setAttribute("ride", ride);
        } else if ("insert".equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT;
        } else {
            forward = LIST_RIDE;
            request.setAttribute("ridesUnassigned", dao.getByCustomerAndStatus(customer, Ride.Status.UNASSIGNED));
            request.setAttribute("ridesInProcess", dao.getByCustomerAndStatus(customer, Ride.Status.IN_PROCESS));
            request.setAttribute("ridesFinished", dao.getByCustomerAndStatus(customer, Ride.Status.FINISHED));
            request.setAttribute("ridesCanceled", dao.getByCustomerAndStatus(customer, Ride.Status.CANCELED));
            //request.setAttribute("rides", dao.getByCustomer(customer));
        }

        request.getRequestDispatcher(forward).forward(request, response);

    }
}
