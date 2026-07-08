package ua.goit.database;

import org.flywaydb.core.Flyway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            // File-based H2 database to persist state across service runs.
            // DB_CLOSE_DELAY=-1 keeps the database alive until VM exits.
            String url = "jdbc:h2:./db/test;DB_CLOSE_DELAY=-1";
            String user = "sa";
            String password = "";

            // Initialize and execute Flyway migrations
            Flyway flyway = Flyway.configure()
                    .dataSource(url, user, password)
                    .locations("db/migration")
                    .baselineOnMigrate(true)
                    .load();
            flyway.migrate();

            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to H2 database", e);
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
