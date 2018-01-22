package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.h2.jdbcx.JdbcConnectionPool;

public final class DBConnectionPool {

    private String driver;
    private String url;
    private String user;
    private String password;
    private int poolSize;
    private JdbcConnectionPool cp;
    private static DBConnectionPool instance;

    private DBConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("db");

        driver = bundle.getString("driver");
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
        poolSize = Integer.parseInt(bundle.getString("poolSize"));

        cp = JdbcConnectionPool.create(url, user, password);
        cp.setMaxConnections(poolSize);
    }

    public static DBConnectionPool getInstance() {
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public void dispose() {
        cp.dispose();
        instance = null;
    }

    public Connection getConnection() throws SQLException {
        return cp.getConnection();
    }
}
