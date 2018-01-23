package dao;

import model.Ride;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import util.DBConnectionPool;

public class RideDaoImpl implements RideDao {

    //private Connection connection = DBConnectionPool.getInstance().getConnection();

    @Override
    public void add (Ride ride) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Ride (id , name, mass, volume, status, executor_id, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, ride.getId());
            preparedStatement.setString(2, ride.getName());
            preparedStatement.setFloat(3, ride.getParamMass());
            preparedStatement.setFloat(4, ride.getParamVolume());
            preparedStatement.setString(5, ride.getStatus());
            preparedStatement.setLong(6, ride.getExecutor().getId());
            preparedStatement.setLong(7, ride.getManager().getId());

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
                throw new DaoException("SQLexception while closing connections error in add method", e);
            }
        }
    }

    @Override
    public List<Ride> getAll() {

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
                ride.setId(rs.getLong("id"));
                ride.setName(rs.getString("name"));
                ride.setMass(rs.getFloat("mass"));
                ride.setVolume(rs.getFloat("volume"));
                ride.setStatus(rs.getString("status"));
                ride.setExecutor(rs.getString("executor_id"));
                ride.setManager(rs.getString("manager_id"));
                list.add(ride);
            }
        } catch (SQLException e) {
            //TODO logging
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return list;
    }

    @Override
    public void update(Ride ride) {

        Connection connection = DBConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE ride SET name = ?, " +
                "mass = ?, volume = ?, status = ?, executor_id = ?, " +
                "manager_id = ? WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ride.getName());
            preparedStatement.setFloat(2, ride.getMass());
            preparedStatement.setFloat(3, ride.getVolume());
            preparedStatement.setString(4, ride.getStatus());
            preparedStatement.setString(5, ride.getExecutor());
            preparedStatement.setString(6, ride.getManager());
            preparedStatement.setLong(7, ride.getid());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //TODO logging
            throw new DaoException("SQLexception in update method", e);
        } finally {
            try {
                DbUtils.closeQuietly(preparedStatement);
                DbUtils.close(connection);
            } catch (SQLException e) {
                //TODO logging
                throw new DaoException("SQLException while closing connections in update method", e);
            }
        }

    }

    @Override
    public void delete(Ride ride) {

        Connection connection = DBConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Ride WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, ride.getId());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //TODO logging
            throw new DaoException("SQLexception in delete method", e);
        } finally {
            try {
                DbUtils.closeQuietly(preparedStatement);
                DbUtils.close(connection);
            } catch (SQLException e) {
                //TODO logging
                throw new DaoException("SQLException while closing connections in delete method", e);
            }
        }

    }

}
