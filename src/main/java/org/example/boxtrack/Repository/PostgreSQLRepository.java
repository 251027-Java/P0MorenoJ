package org.example.boxtrack.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgreSQLRepository<T> implements IRepository<T> {
    // Fields
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/boxtrack";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_PW = "mysecretpassword";
    private Connection connection;

    //Constructor
    public PostgreSQLRepository() {
        try {
            connection = DriverManager.getConnection(Postgre_URL,Postgre_User, Postgre_PW);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE SCHEMA IF NOT EXISTS boxtrack;");
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.boxer (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "weight_class VARCHAR(50)," +
                        "ranking INT" +
                        ");");
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.coach (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "experience_years INT" +
                        ");");
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.boxer_coach (" +
                        "boxer_id INT REFERENCES boxtrack.boxer(id) ON DELETE CASCADE," +
                        "coach_id INT REFERENCES boxtrack.coach(id) ON DELETE CASCADE," +
                        "PRIMARY KEY (boxer_id, coach_id)" +
                        ");");
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.event (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "location VARCHAR(100)," +
                        "event_date DATE" +
                        ");");
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.match (" +
                        "id SERIAL PRIMARY KEY," +
                        "event_id INT REFERENCES boxtrack.event(id) ON DELETE SET NULL," +
                        "boxer1_id INT REFERENCES boxtrack.boxer(id)," +
                        "boxer2_id INT REFERENCES boxtrack.boxer(id)," +
                        "match_date DATE" +
                        ");");
                System.out.println("Successful creation of PostgreSQL database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return List.of();
    }
}
