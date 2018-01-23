package dao;

import model.Ride;
import model.Transport;
import model.User;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportDaoImpl implements TransportDao {
    @Override
    public boolean add (Transport transport) {
        Connection connection = DBConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Transport (id , max_mass, max_volume, isAuto_works, isAuto_available, driver_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, transport.getId());
            preparedStatement.setFloat(3, transport.getMaxMass());
            preparedStatement.setFloat(4, transport.getMaxVolume());
            preparedStatement.setBoolean(5, transport.getIsAutoWorks());
            preparedStatement.setBoolean(6, transport.getIsAutoAvailable());
            preparedStatement.setLong(7, transport.getDriver().getId());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //TODO logging
            throw new DaoException("SQLexception in add method", e);
        } finally {
            try {
                DbUtils.closeQuietly(preparedStatement);
                DbUtils.close(connection);
            } catch (SQLException e) {
                //TODO logging
                throw new DaoException("closing connections error in insert method", e);
            }

        }
    }

    @Override
    public Transport getBy(User driver) {

    }

    @Override
    public List<Transport> getSuitable(Ride ride) {
        return null;
    }

    @Override
    public List<Transport> getAll() {
        Connection connection = DBConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Transport";
        List<Transport> list = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Transport transport = new Transport();
                transport.setId(rs.getLong("id "));
                transport.setName(rs.getString("name"));
                transport.setMass(rs.getFloat("mass"));
                transport.setVolume(rs.getFloat("volume"));
                transport.setStatus(rs.getString("status"));
                transport.setExecutor(rs.getString("executor_id"));
                transport.setManager(rs.getString("manager_id"));
                list.add(transport);
            }
        } catch (SQLException e) {
            //TODO logging
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }
    }

    @Override
    public boolean update(Transport transport) {
        return false;
    }

    @Override
    public boolean delete(Transport transport) {
        return false;
    }
}
