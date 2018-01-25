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

    final static private String SQL_INSERT_RIDE = "INSERT INTO Ride (name, mass, volume, status) " +
            "VALUES (?, ?, ?, ?)";

    final static private String SQL_SELECT_RIDE_BY_ID = "SELECT * FROM Ride WHERE id = ?";

    final static private String SQL_SELECT_RIDE_BY_EXECUTOR = "SELECT * FROM Ride WHERE executor_id = ?";

    final static private String SQL_SELECT_RIDE_BY_STATUS = "SELECT * FROM Ride WHERE status = ?";

    final static private String SQL_SELECT_ALL_RIDES = "SELECT * FROM Ride";

    final static private String SQL_UPDATE_RIDE = "UPDATE ride SET name = ?, " +
            "mass = ?, volume = ?, status = ? WHERE id = ?";

    final static private String SQL_DELETE_RIDE = "DELETE FROM Ride WHERE id = ?";

    private Ride assembleRide (ResultSet rs) throws SQLException {
        Ride ride = new Ride();

        ride.setId(rs.getLong("id"));
        ride.setName(rs.getString("name"));
        ride.setMass(rs.getFloat("mass"));
        ride.setVolume(rs.getFloat("volume"));
        ride.setStatus(rs.getString("status"));
        return ride;
    }

    @Override
    public void add (Ride ride) throws DataAccessException {

        logger.debug("add(Ride ride) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = SQL_INSERT_RIDE;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ride.getName());
            preparedStatement.setFloat(2, ride.getMass());
            preparedStatement.setFloat(3, ride.getVolume());
            preparedStatement.setString(4, ride.getStatus().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLexception in add method : " + e.getMessage());
            throw new DataAccessException("SQLexception in add method", e);
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
            throw new DataAccessException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return ride;
    }

    @Override
    public List<Ride> getByExecutor(Long executorId) {
        logger.debug("Ride getBy(Long executorId) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_RIDE_BY_EXECUTOR;
        List<Ride> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, executorId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ride ride = assembleRide(rs);
                list.add(ride);
            }
        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DataAccessException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return list;
    }

    @Override
    public List<Ride> getByStatus(Ride.Status status) {
        logger.debug("List<Ride> getBy(Status status) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_RIDE_BY_STATUS;
        List<Ride> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status.name());
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ride ride;
                ride = assembleRide(rs);
                list.add(ride);
            }

        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DataAccessException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return list;
    }

    @Override
    public List<Ride> getAll() throws DataAccessException {

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
            throw new DataAccessException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return list;
    }

    @Override
    public void update(Ride ride) throws DataAccessException {

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
            preparedStatement.setString(4, ride.getStatus().name());
            preparedStatement.setLong(5, ride.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLexception in update method : " + e.getMessage());
            throw new DataAccessException("SQLexception in update method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

    }

    @Override
    public void delete(Ride ride) throws DataAccessException {

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
            throw new DataAccessException("SQLexception in delete method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

    }

}
