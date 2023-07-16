package module6;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class Database {

    public static final String DB_URL = "jdbc:mariadb://localhost:3306/database_for_learning?allowMultiQueries=true";
    private static Database instance;
    private Connection connection;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(new FileReader("db.properties"));
                connection = DriverManager.getConnection(DB_URL, properties.getProperty("login"),
                        properties.getProperty("password"));
                log.info("Database connected!");
            } catch (SQLException | IOException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }
        return connection;
    }

}
