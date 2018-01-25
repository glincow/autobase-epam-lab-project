package dao;

import model.Ride;

import java.util.List;

public interface RideDao {

    //create
    void add(Ride ride);

    //read
    Ride getById(Long id);

    Ride getByExecutor(Long executorId);

    List<Ride> getAll();

    List<Ride> getByStatus(Ride.Status status);



    //update
    void update(Ride ride);

    //delete
    void delete(Ride ride);

}
