package dao;

import model.Ride;
import model.Transport;
import model.User;

import java.util.List;

//TODO why name methods getBy and use overloading when you can name it getByUser for example?
//TODO also you can distinguish methods which return List<Object> vs Object by naming them like getAll...
public interface TransportDao {

    //create
    void add (Transport transport);

    //read
    Transport getBy(User driver);

    Transport getBy(Long id);

    List<Transport> getSuitable(Ride ride);

    List<Transport> getAll();

    //update
    void update(Transport transport);

    //delete
    void delete(Transport transport);

}
