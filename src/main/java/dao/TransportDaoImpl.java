package dao;

import model.Ride;
import model.Transport;
import model.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportDaoImpl implements TransportDao {

    final static private Logger logger = LogManager.getLogger(TransportDaoImpl.class);

    final static private UserDao userDao = new UserDaoImpl();

    private Transport assembleTransport (ResultSet rs) throws SQLException {

        Transport transport = new Transport();

        transport.setId(rs.getLong("id"));
        transport.setMaxMass(rs.getFloat("max_mass"));
        transport.setMaxVolume(rs.getFloat("max_volume"));
        transport.setIsAutoWorks(rs.getBoolean("isAuto_works"));
        transport.setIsAutoAvailable(rs.getBoolean("isAuto_available"));
        transport.setDriver(userDao.getBy(rs.getLong("driver_id")));

        return transport;
    }

    final static private String SQL_INSERT_TRANSPORT = "INSERT INTO Transport (id , max_mass, max_volume, " +
            "isAuto_works, isAuto_available, driver_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

    final static private String SQL_SELECT_TRANSPORT_BY_ID = "SELECT * FROM Transport WHERE id = ?";

    final static private String SQL_SELECT_TRANSPORT_BY_DRIVER = "SELECT * FROM Transport WHERE driver_id = ?";

    final static private String SQL_SELECT_ALL_TRANSPORT = "SELECT * FROM Transport";

    final static private String SQL_SELECT_SUITABLE_FOR_RIDE_TRANSPORT = "SELECT * FROM Transport WHERE " +
            "max_mass > ? AND max_volume > ? AND isAuto_works = TRUE AND isAuto_available = TRUE";

    final static private String SQL_UPDATE_TRANSPORT = "UPDATE Transport SET max_mass = ?, max_volume = ?, isAuto_works = ?, " +
            "isAuto_available = ?, driver_id = ? WHERE id = ?";

    final static private String SQL_DELETE_TRANSPORT = "DELETE FROM Transport WHERE id = ?";

    @Override
    public void add (Transport transport) {

        logger.debug("add(Transport transport) started......");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = SQL_INSERT_TRANSPORT;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, transport.getId());
            preparedStatement.setFloat(3, transport.getMaxMass());
            preparedStatement.setFloat(4, transport.getMaxVolume());
            preparedStatement.setBoolean(5, transport.getIsAutoWorks());
            preparedStatement.setBoolean(6, transport.getIsAutoAvailable());
            preparedStatement.setLong(7, transport.getDriver().getId());

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
    public Transport getBy(Long id) {

        logger.debug("Transport getBy(Long id) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_TRANSPORT_BY_ID;
        Transport transport;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();
            transport = assembleTransport(rs);

        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return transport;

    }

    @Override
    public Transport getBy(User driver) {

        logger.debug("Transport getBy(User driver)) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_TRANSPORT_BY_DRIVER;
        Transport transport;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, driver.getId());
            rs = preparedStatement.executeQuery();
            rs.next();
            transport = assembleTransport(rs);
        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return transport;
    }

    @Override
    public List<Transport> getSuitable(Ride ride) {
        logger.debug("List<Transport> getSuitable(Ride ride) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Transport> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_SUITABLE_FOR_RIDE_TRANSPORT);
            preparedStatement.setFloat(1, ride.getMass());
            preparedStatement.setFloat(2, ride.getVolume());
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Transport transport;
                transport = assembleTransport(rs);
                list.add(transport);
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
    public List<Transport> getAll() {

        logger.debug("List<Transport> getAll() started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = SQL_SELECT_ALL_TRANSPORT;
        List<Transport> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Transport transport;
                transport = assembleTransport(rs);
                list.add(transport);
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
    public void update(Transport transport) {

        logger.debug("update(Transport transport) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_UPDATE_TRANSPORT;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setFloat(1, transport.getMaxMass());
            preparedStatement.setFloat(2, transport.getMaxVolume());
            preparedStatement.setBoolean(3, transport.getIsAutoWorks());
            preparedStatement.setBoolean(4, transport.getIsAutoAvailable());
            preparedStatement.setLong(5, transport.getDriver().getId());
            preparedStatement.setLong(6, transport.getId());

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
    public void delete(Transport transport) {

        logger.debug("delete(Transport (transport) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_DELETE_TRANSPORT;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, transport.getId());

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
