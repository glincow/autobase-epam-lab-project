package dao;

import dao.exceptions.DataAccessException;
import dao.exceptions.EmptyResultDataAccessException;
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

    final static private String SQL_INSERT_USER = "INSERT INTO User (name, login, password, role_id) " +
            "VALUES (?, ?, ?, ?)";

    final static private String SQL_SELECT_USER_BY_ID = "SELECT * FROM User WHERE id = ?";

    final static private String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM User WHERE login = ?";

    final static private String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM User WHERE login = ? AND password = ?";

    final static private String SQL_SELECT_ALL_USERS = "SELECT * FROM User";

    final static private String SQL_UPDATE_USER = "UPDATE User SET name = ?, login = ?, password = ?, " +
            "role_id = ? WHERE id = ?";

    final static private String SQL_DELETE_USER = "DELETE FROM User WHERE id = ?";

    private User assembleUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setRole(rs.getLong("role_id"));

        return user;
    }

    @Override
    public void add(User user) {

        logger.debug("add(User user) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_INSERT_USER;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getRole().getId());

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
    public User getBy(Long id) {

        logger.debug("User getBy(Long id) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        User user = new User();

        try {
            connection = DBConnectionPool.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();

            user = assembleUser(rs);
        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DataAccessException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return user;
    }

    @Override
    public User getBy(String login) {

        logger.debug("User getBy(String login) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_SELECT_USER_BY_LOGIN;
        ResultSet rs = null;
        User user;

        try {
            connection = DBConnectionPool.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = assembleUser(rs);
            } else {
                throw new EmptyResultDataAccessException("No user with this login found");
            }
        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DataAccessException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return user;
    }

    @Override
    public User getBy(String login, String password) {

        logger.debug("User getBy(String login, String password) started...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD;
        ResultSet rs = null;
        User user;

        try {
            connection = DBConnectionPool.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            rs.next();

            user = assembleUser(rs);
        } catch (SQLException e) {
            logger.error("SQLexception in get method : " + e.getMessage());
            throw new DataAccessException("SQLexception in get method", e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, rs);
        }

        return user;
    }

    @Override
    public List<User> getAll() {

        logger.debug("List<User> getAll() started...");
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_SELECT_ALL_USERS;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = assembleUser(rs);
                list.add(user);
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
    public void update(User user) {

        logger.debug("update(User user) started...");
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = SQL_UPDATE_USER;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getRole().getId());
            preparedStatement.setLong(5, user.getId());

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
    public void delete(User user) {

        logger.debug("delete (User user) started...");
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqlUser = SQL_DELETE_USER;

        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlUser);
            preparedStatement.setLong(1, user.getId());
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
