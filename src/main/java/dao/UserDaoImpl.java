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

    final static private String SQL_INSERT_USER = "INSERT INTO User (id , name, login, password, role_id) " +
            "VALUES (?, ?, ?, ?, SELECT id from Role where name = ?)";

    final static private String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM User inner join (select * from Role) as Role " +
            "on User.role_id=Role.id where login = ?";

    final static private String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM User inner join (select * from Role) as Role " +
            "on User.role_id=Role.id where login = ? and password = ?";

    final static private String SQL_SELECT_ALL_USERS = "SELECT * FROM User " +
            "inner join (select * from Role) as Role on User.role_id=Role.id";

    final static private String SQL_UPDATE_USER = "UPDATE User SET name = ?, login = ?, password = ?, " +
            "role_id = (SELECT id from Role where name = ?) WHERE id = ?";

    final static private String SQL_DELETE_USER = "DELETE FROM User WHERE id = ?";

    private User assembleUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setRole(rs.getString(ROLE_COLUMN_ID));

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

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());

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
    public User getBy(Long id) {

        logger.debug("User getBy(Long id) started...");

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
            rs.next();

            user = assembleUser(rs);
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
            throw new DaoException("SQLexception in get method", e);
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
            throw new DaoException("SQLexception in get method", e);
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
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setLong(5, user.getId());

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
            throw new DaoException("SQLexception in delete method", e);
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
    }
}
