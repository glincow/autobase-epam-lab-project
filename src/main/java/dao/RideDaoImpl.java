package dao;

import model.Ride;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.apache.commons.dbutils.DbUtils;
import util.DBConnectionPool;

public class RideDaoImpl implements RideDao {

    final static Logger logger = LogManager.getLogger(RideDaoImpl.class);

    private Ride assembleRide (ResultSet rs) throws SQLException {
        Ride ride = new Ride();

        ride.setId(rs.getLong("id"));
        ride.setName(rs.getString("name"));
        ride.setMass(rs.getFloat("mass"));
        ride.setVolume(rs.getFloat("volume"));
        ride.setStatus(rs.getString("status"));
//        ride.setExecutor(new TransportDaoImpl().getBy(rs.getLong("executor_id")));
//        ride.setManager(new UserDaoImpl().getBy(rs.getLong("manager_id")));

        return ride;
    }

    @Override
    public void add (Ride ride) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Ride (name, mass, volume, status) VALUES (?, ?, ?, ?)";
//        String sql = "INSERT INTO Ride (name, mass, volume, status, executor_id, manager_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

//            preparedStatement.setLong(1, ride.getId());
            preparedStatement.setString(1, ride.getName());
            preparedStatement.setFloat(2, ride.getMass());
            preparedStatement.setFloat(3, ride.getVolume());
            preparedStatement.setString(4, ride.getStatus());
//            preparedStatement.setLong(5, ride.getExecutor().getId());
//            preparedStatement.setLong(6, ride.getManager().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLexception in add method : " + e.getMessage());
            throw new DaoException("SQLexception in add method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public Ride getById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Ride WHERE id = ?";
        Ride ride;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();
            ride = assembleRide(rs);

        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return ride;
    }

    @Override
    public List<Ride> getAll() throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Ride";
        List<Ride> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();


            while (rs.next()) {
                Ride ride = new Ride();
                ride = assembleRide(rs);
                list.add(ride);
            }
        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return list;
    }

    @Override
    public void update(Ride ride) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE ride SET name = ?, " +
                "mass = ?, volume = ?, status = ?, executor_id = ?, " +
                "manager_id = ? WHERE id = ?";

        try {
            connection  = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ride.getName());
            preparedStatement.setFloat(2, ride.getMass());
            preparedStatement.setFloat(3, ride.getVolume());
            preparedStatement.setString(4, ride.getStatus());
            preparedStatement.setLong(5, ride.getExecutor().getId());
            preparedStatement.setLong(6, ride.getManager().getId());
            preparedStatement.setLong(7, ride.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLexception in update method : " + e.getMessage());
            throw new DaoException("SQLexception in update method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

    }

    @Override
    public void delete(Ride ride) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Ride WHERE id = ?";

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, ride.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLexception in delete method : " + e.getMessage());
            throw new DaoException("SQLexception in delete method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

    }

}
