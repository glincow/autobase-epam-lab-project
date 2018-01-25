package config;

import util.DBConnectionPool;
import util.ScriptRunner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class Config implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DBConnectionPool cp = DBConnectionPool.getInstance();
        try (Connection connection = cp.getConnection()){
            ScriptRunner scriptRunner = new ScriptRunner(connection,
                    false, true);
            BufferedReader reader = new BufferedReader(new FileReader(
                    "D:\\JavaProjects\\autobase-epam-lab-project\\src\\main\\resources\\createTables.sql"));
            scriptRunner.runScript(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cp.dispose();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}