package dao;

import model.Ride;
import java.util.List;

public interface RideDao {

    //create
    boolean insert(Ride ride);

    //read
    Ride getById(long id);

    List<Ride> getAll();

    List<Ride> getByStatus(String status);

    //update
    boolean update(Ride ride);

    //delete
    boolean delete(Ride ride);

}
