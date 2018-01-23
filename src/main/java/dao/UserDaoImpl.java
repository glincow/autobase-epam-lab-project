package dao;

import model.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final static int ROLE_COLUMN_ID = 7;

    private final static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public void add(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO User (id , name, login, password, role_id) " +
                "VALUES (?, ?, ?, ?, SELECT id from Role where name = ?)";

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQLexception in add method : " + e.getMessage());
            throw new DaoException("SQLexception in add method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

    }

    @Override
    public User getBy(long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlUser = "SELECT * FROM User where id = ?";
        String sqlRole = "SELECT name FROM Role where id = ?";
        ResultSet rs = null;
        User user = new User();

        try {
            connection = DBConnectionPool.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(sqlUser);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();

            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setId(id);
            user.setName(rs.getString("name"));
            long role = rs.getLong("role_id");


            preparedStatement = connection.prepareStatement(sqlRole);
            preparedStatement.setLong(1, role);
            rs = preparedStatement.executeQuery();
            rs.next();

            user.setRole(rs.getString("name"));

        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return user;
    }

    @Override
    public User getBy(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM User inner join (select * from Role) as Role " +
                "on User.role_id=Role.id where login = ?";
        ResultSet rs = null;
        User user = new User();

        try {
            connection = DBConnectionPool.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            rs.next();

            user.setLogin(login);
            user.setPassword(rs.getString("password"));
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getString(ROLE_COLUMN_ID));

        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DaoException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return user;
    }

    @Override
    public User getBy(String login, String password) {
        return null;
    }

    @Override
    public List<User> getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String User = "SELECT * FROM User inner join (select * from Role) as Role on User.role_id=Role.id";
        ResultSet rs = null;
        List<User> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(User);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString(ROLE_COLUMN_ID));
                list.add(user);
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
    public void update(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE User SET name = ?, login = ?, password = ?, " +
                "role_id = (SELECT id from Role where name = ?) WHERE id = ?";

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setLong(5, user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQLexception in update method : " + e.getMessage());
            throw new DaoException("SQLexception in update method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

    }

    @Override
    public void delete(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlUser = "DELETE FROM User WHERE id = ?";

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlUser);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQLexception in delete method : " + e.getMessage());
            throw new DaoException("SQLexception in delete method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

    }
}
