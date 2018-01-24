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

    final static private Logger logger = LogManager.getLogger(RideDaoImpl.class);

    final static private TransportDao transportDao = new TransportDaoImpl();

    final static private UserDao userDao = new UserDaoImpl();

    final static private String SQL_INSERT_RIDE = "INSERT INTO Ride (id , name, mass, volume, status, executor_id, manager_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    final static private String SQL_SELECT_RIDE_BY_ID = "SELECT * FROM Ride WHERE id = ?";

    final static private String SQL_SELECT_ALL_RIDES = "SELECT * FROM Ride";

    final static private String SQL_UPDATE_RIDE = "UPDATE ride SET name = ?, " +
            "mass = ?, volume = ?, status = ?, executor_id = ?, " +
            "manager_id = ? WHERE id = ?";

    final static private String SQL_DELETE_RIDE = "DELETE FROM Ride WHERE id = ?";

    private Ride assembleRide (ResultSet rs) throws SQLException {
        Ride ride = new Ride();

        ride.setId(rs.getLong("id"));
        ride.setName(rs.getString("name"));
        ride.setMass(rs.getFloat("mass"));
        ride.setVolume(rs.getFloat("volume"));
        ride.setStatus(rs.getString("status"));
        ride.setExecutor(transportDao.getBy(rs.getLong("executor_id")));
        ride.setManager(userDao.getBy(rs.getLong("manager_id")));

        return ride;
    }

    @Override
    public void add (Ride ride) throws DaoException {

        logger.debug("add(Ride ride) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = SQL_INSERT_RIDE;
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
            preparedStatement.setLong(1, ride.getId());
            preparedStatement.setString(2, ride.getName());
            preparedStatement.setFloat(3, ride.getMass());
            preparedStatement.setFloat(4, ride.getVolume());
            preparedStatement.setString(5, ride.getStatus());
            preparedStatement.setLong(6, ride.getExecutor().getId());
            preparedStatement.setLong(7, ride.getManager().getId());

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

        logger.debug("Ride getBy(Long id) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_RIDE_BY_ID;
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

        logger.debug("List<Ride> getAll started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_ALL_RIDES;
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

        logger.debug("update(Ride ride) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_UPDATE_RIDE;

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

        logger.debug("delete(Ride ride) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_DELETE_RIDE;

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
