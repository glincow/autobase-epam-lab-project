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

    final static Logger logger = LogManager.getLogger(TransportDaoImpl.class);

    @Override
    public void add (Transport transport) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Transport (id , max_mass, max_volume, isAuto_works, isAuto_available, driver_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
    public Transport getBy(User driver) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Transport WHERE driver_id = ?";
        Transport transport;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, driver.getId());
            rs = preparedStatement.executeQuery();
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
    public Transport getBy(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Transport WHERE id = ?";
        Transport transport;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
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
        return null;
    }

    @Override
    public List<Transport> getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Transport";
        List<Transport> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Transport transport = new Transport();
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE Transport SET max_mass = ?, max_volume = ?, isAuto_works = ?, " +
                "isAuto_available = ?, driver_id = ? WHERE id = ?";

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setFloat(1, transport.getMaxMass());
            preparedStatement.setFloat(2, transport.getMaxVolume());
            preparedStatement.setBoolean(3, transport.getIsAutoWorks());
            preparedStatement.setBoolean(4, transport.getIsAutoAvailable());
            preparedStatement.setLong(5, transport.getId());

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

    }

    private Transport assembleTransport (ResultSet rs) throws SQLException {
        Transport transport = new Transport();

        transport.setId(rs.getLong("id"));
        transport.setMaxMass(rs.getFloat("max_mass"));
        transport.setMaxVolume(rs.getFloat("max_volume"));
        transport.setIsAutoWorks(rs.getBoolean("isAuto_works"));
        transport.setIsAutoAvailable(rs.getBoolean("isAuto_available"));
        transport.setDriver(new UserDaoImpl().getBy(rs.getLong("driver_id")));

        return transport;
    }

}
