package dao;

import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDao {
    private Connection connection;

    public RoleDao(){
        connection = util.DatabaseConnection.getConnection();
    }

    public void addRole(Role role){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Role(name) values (?)");
            preparedStatement.setString(1,"Driver");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
