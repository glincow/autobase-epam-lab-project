package dao;

import model.Ride;
import model.Transport;
import model.User;

import java.util.List;

public interface TransportDao {

    //create
    boolean add (Transport transport);

    //read
    Transport getBy(User driver);

    List<Transport> getSuitable(Ride ride);

    List<Transport> getAll();

    //update
    boolean update(Transport transport);

    //delete
    boolean delete(Transport transport);

}
