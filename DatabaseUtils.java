package util;

import java.sql.Connection;
import java.sql.DriverManager;

import config.DatabaseConfig;

public class DatabaseUtils {
    private static DatabaseConfig config = new DatabaseConfig();

    public static Connection getConnection() {
        try {
            Class.forName(config.getDriver());
            return DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
