package coreJavaVolume2AdvancedFeatures.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * @author zhangjinglong
 * @date 2020-03-24-9:47 上午
 * <p>
 * This program tests that the database and the JDBC Driver are correctly configured.
 */

public class TestDB {
    public static void main(String[] args) throws IOException {
        try {
            runTest();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }

    /**
     * Runs a test by creating a table ,adding a value, showing the table contents,and removing the table
     */
    public static void runTest() throws SQLException, IOException {
        try (Connection conn = getConnection(); Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE Greetings (MESSAGE CHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES('Hello,World!')");
            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings")) {
                if (result.next()) {
                    System.out.println(result.getString(1));
                }
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }

    }


    /**
     * Gets a connection from properties specified in the file database.properties.
     *
     * @return the database connection
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/coreJavaVolume2AdvancedFeatures/db/database.properties"))) {
            props.load(in);
        }
        String driver = props.getProperty("jdbc.driver");
        if (driver != null) System.setProperty("jdbc.drivers", driver);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);


    }
}
