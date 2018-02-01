package dao;

import dao.exceptions.DataAccessException;
import model.Ride;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Transport;
import model.User;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.commons.dbutils.DbUtils;
import util.DBConnectionPool;

public class RideDaoImpl implements RideDao {

    final static private Logger logger = LogManager.getLogger(RideDaoImpl.class);

    final static private TransportDao transportDao = new TransportDaoImpl();

    final static private UserDao userDao = new UserDaoImpl();

    final static private String SQL_INSERT_RIDE = "INSERT INTO Ride (name, mass, volume, status, customer_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    final static private String SQL_SELECT_RIDE_BY_ID = "SELECT * FROM Ride WHERE id = ?";

    final static private String SQL_SELECT_RIDE_BY_EXECUTOR = "SELECT * FROM Ride WHERE executor_id = ?";

    final static private String SQL_SELECT_RIDE_BY_EXECUTOR_AND_STATUS = "SELECT * FROM Ride WHERE executor_id = ? AND status = ?";

    final static private String SQL_SELECT_RIDE_BY_MANAGER_AND_STATUS = "SELECT * FROM Ride WHERE manager_id = ? AND status = ?";

    final static private String SQL_SELECT_RIDE_BY_CUSTOMER = "SELECT * FROM Ride WHERE customer_id = ? ORDER BY status DESC";

    final static private String SQL_SELECT_RIDE_BY_STATUS = "SELECT * FROM Ride WHERE status = ?";

    final static private String SQL_SELECT_ALL_RIDES = "SELECT * FROM Ride";

    final static private String SQL_UPDATE_RIDE = "UPDATE ride SET name = ?, " +
            "mass = ?, volume = ?, status = ?, executor_id = ?, manager_id = ? WHERE id = ?";

    final static private String SQL_UPDATE_UNASSIGNED_RIDE = "UPDATE ride SET name = ?, " +
            "mass = ?, volume = ?, status = ? WHERE id = ?";

    final static private String SQL_DELETE_RIDE = "DELETE FROM Ride WHERE id = ?";

    private Ride assembleRide(ResultSet rs) throws SQLException {

        Long id = rs.getLong("id");
        String name = rs.getString("name");
        float mass = rs.getFloat("mass");
        float volume = rs.getFloat("volume");
        Ride.Status status = Ride.Status.valueOf(rs.getString("status").toUpperCase());
        Long executor_id = rs.getLong("executor_id");
        Long manager_id = rs.getLong("manager_id");
        Long customer_id = rs.getLong("customer_id");
        User customer = userDao.getBy(customer_id);
        if (status.equals(Ride.Status.UNASSIGNED) || status.equals(Ride.Status.CANCELED)) {
            return new Ride(id, name, mass, volume, status, customer);
        } else {
            Transport executor = transportDao.getBy(executor_id);
            User manager = userDao.getBy(manager_id);
            return new Ride(id, name, mass, volume, status, executor, manager, customer);
        }
    }

    @Override
    public void add(Ride ride) throws DataAccessException {

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
            preparedStatement.setLong(5, ride.getCustomer().getId());

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
    public List<Ride> getByExecutor(Transport transport) {
        logger.debug("Ride getBy(Long executorId) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_RIDE_BY_EXECUTOR;
        List<Ride> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, transport.getId());
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
    public List<Ride> getByExecutorAndStatus(Transport transport, Ride.Status status) {
        logger.debug("Ride get by executor and status started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_RIDE_BY_EXECUTOR_AND_STATUS;
        List<Ride> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, transport.getId());
            preparedStatement.setString(2, status.name());
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
    public List<Ride> getByManagerAndStatus(User manager, Ride.Status status) {
        logger.debug("Ride get by manager and status started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_RIDE_BY_MANAGER_AND_STATUS;
        List<Ride> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, manager.getId());
            preparedStatement.setString(2, status.name());
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
    public List<Ride> getByCustomer(User customer) {
        logger.debug("Ride getBy(User customer) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_RIDE_BY_CUSTOMER;
        List<Ride> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customer.getId());
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
                //Ride ride = new Ride();
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
    public void update(Ride ride) throws DataAccessException {

        logger.debug("update(Ride ride) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_UPDATE_RIDE;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            switch (ride.getStatus()) {
                case UNASSIGNED:
                case CANCELED:
                    preparedStatement = connection.prepareStatement(SQL_UPDATE_UNASSIGNED_RIDE);
                    preparedStatement.setString(1, ride.getName());
                    preparedStatement.setFloat(2, ride.getMass());
                    preparedStatement.setFloat(3, ride.getVolume());
                    preparedStatement.setString(4, ride.getStatus().name());
                    preparedStatement.setLong(5, ride.getId());
                    break;
                default:
                    preparedStatement = connection.prepareStatement(SQL_UPDATE_RIDE);
                    preparedStatement.setString(1, ride.getName());
                    preparedStatement.setFloat(2, ride.getMass());
                    preparedStatement.setFloat(3, ride.getVolume());
                    preparedStatement.setString(4, ride.getStatus().name());
                    preparedStatement.setLong(5, ride.getExecutor().getId());
                    preparedStatement.setLong(6, ride.getManager().getId());
                    preparedStatement.setLong(7, ride.getId());
            }
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
