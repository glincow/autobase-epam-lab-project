package dao;

import model.Ride;
import java.util.List;

public interface RideDao {

    //create
    void add(Ride ride);

    //read
    //Ride getById(long id);

    List<Ride> getAll();

    //List<Ride> getByStatus(String status);

    //update
    void update(Ride ride);

    //delete
    void delete(Ride ride);

}
