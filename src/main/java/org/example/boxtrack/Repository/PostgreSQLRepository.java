package org.example.boxtrack.Repository;

import org.example.boxtrack.Model.Boxer;
import org.example.boxtrack.Model.Coach;
import org.example.boxtrack.Model.Event;
import org.example.boxtrack.Model.Match;

import java.sql.*;
import java.util.List;

public class PostgreSQLRepository implements IRepository {
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
    public void createBoxer(Boxer boxer) {
        String sql = "INSERT INTO boxtrack.boxer (name, weight_class, ranking) VALUES (?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, boxer.getName());
            ps.setString(2, boxer.getWeightClass());
            ps.setInt(3, boxer.getRanking());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boxer readBoxer(int id) {
        String sql = "SELECT * FROM boxtrack.boxer WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Boxer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("weight_class"),
                        rs.getInt("ranking")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBoxer(Boxer boxer) {
        String sql = "UPDATE boxtrack.boxer SET name=?, weight_class=?, ranking=? WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, boxer.getName());
            ps.setString(2, boxer.getWeightClass());
            ps.setInt(3, boxer.getRanking());
            ps.setInt(4, boxer.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBoxer(int id) {
        String sql = "DELETE FROM boxtrack.boxer WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Boxer> loadBoxers() {
        return List.of();
    }

    @Override
    public void saveBoxers(List<Boxer> boxers) {

    }

    @Override
    public void createCoach(Coach coach) {

    }

    @Override
    public Coach readCoach(int id) {
        return null;
    }

    @Override
    public void updateCoach(Coach coach) {

    }

    @Override
    public void deleteCoach(int id) {

    }

    @Override
    public List<Coach> loadCoaches() {
        return List.of();
    }

    @Override
    public void saveCoaches(List<Coach> coaches) {

    }

    @Override
    public void createMatch(Match match) {

    }

    @Override
    public Match readMatch(int id) {
        return null;
    }

    @Override
    public void updateMatch(Match match) {

    }

    @Override
    public void deleteMatch(int id) {

    }

    @Override
    public List<Match> loadMatches() {
        return List.of();
    }

    @Override
    public void saveMatches(List<Match> matches) {

    }

    @Override
    public void createEvent(Event event) {

    }

    @Override
    public Event readEvent(int id) {
        return null;
    }

    @Override
    public void updateEvent(Event event) {

    }

    @Override
    public void deleteEvent(int id) {

    }

    @Override
    public List<Event> loadEvents() {
        return List.of();
    }

    @Override
    public void saveEvents(List<Event> events) {

    }
}
