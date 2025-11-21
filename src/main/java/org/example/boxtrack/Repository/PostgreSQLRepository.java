package org.example.boxtrack.Repository;

import org.example.boxtrack.Model.Boxer;
import org.example.boxtrack.Model.Coach;
import org.example.boxtrack.Model.Event;
import org.example.boxtrack.Model.Match;

import java.sql.*;
import java.util.ArrayList;
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
                // Boxer table
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.boxer (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "weight_class VARCHAR(50) NOT NULL," +
                        "ranking INT" +
                        ");");
                // Coach table
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.coach (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "experience_years INT NOT NULL" +
                        ");");
                // Boxer_Coach join table
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.boxer_coach (" +
                        "boxer_id INT NOT NULL," +
                        "coach_id INT NOT NULL," +
                        "PRIMARY KEY (boxer_id, coach_id)," +
                        "CONSTRAINT fk_boxer FOREIGN KEY (boxer_id) REFERENCES boxtrack.boxer(id) ON DELETE CASCADE," +
                        "CONSTRAINT fk_coach FOREIGN KEY (coach_id) REFERENCES boxtrack.coach(id) ON DELETE CASCADE" +
                        ");");
                // Event table
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.event (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "location VARCHAR(100) NOT NULL," +
                        "event_date DATE NOT NULL" +
                        ");");
                // Match table
                stmt.execute("CREATE TABLE IF NOT EXISTS boxtrack.match (" +
                        "id SERIAL PRIMARY KEY," +
                        "boxer1_id INT NOT NULL," +
                        "boxer2_id INT NOT NULL," +
                        "event_id INT NOT NULL," +
                        "result VARCHAR(100)," +
                        "CONSTRAINT fk_boxer1 FOREIGN KEY (boxer1_id) REFERENCES boxtrack.boxer(id) ON DELETE CASCADE," +
                        "CONSTRAINT fk_boxer2 FOREIGN KEY (boxer2_id) REFERENCES boxtrack.boxer(id) ON DELETE CASCADE," +
                        "CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES boxtrack.event(id) ON DELETE CASCADE" +
                        ");");
                System.out.println("Successful creation of PostgreSQL tables.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methods
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
        List<Boxer> list = new ArrayList<>();
        String sql = "SELECT * FROM boxtrack.boxer";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Boxer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("weight_class"),
                        rs.getInt("ranking")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void saveBoxers(List<Boxer> boxers) {
        // clear the table, then re-insert all
        String sql = "DELETE FROM boxtrack.boxer";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        for(Boxer b : boxers) {
            createBoxer(b);
        }
    }

    @Override
    public void createCoach(Coach coach) {
        String sql = "INSERT INTO boxtrack.coach (name, experience_years) VALUES (?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, coach.getName());
            ps.setInt(2, coach.getExperienceYears());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Coach readCoach(int id) {
        String sql = "SELECT * FROM boxtrack.coach WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Coach(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("experience_years")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCoach(Coach coach) {
        String sql = "UPDATE boxtrack.coach SET name=?, experience_years=? WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, coach.getName());
            ps.setInt(2, coach.getExperienceYears());
            ps.setInt(3, coach.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCoach(int id) {
        String sql = "DELETE FROM boxtrack.coach WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Coach> loadCoaches() {
        List<Coach> list = new ArrayList<>();
        String sql = "SELECT * FROM boxtrack.coach";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Coach(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("experience_years")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void saveCoaches(List<Coach> coaches) {
        // clear the table, then re-insert all
        String sql = "DELETE FROM boxtrack.coach";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        for(Coach c : coaches) {
            createCoach(c);
        }
    }

    @Override
    public void createMatch(Match match) {
        String sql = "INSERT INTO boxtrack.match (boxer1_id, boxer2_id, event_id, result) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, match.getBoxer1Id());
            ps.setInt(2, match.getBoxer2Id());
            ps.setInt(3, match.getEventId());
            ps.setString(4, match.getResult());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Match readMatch(int id) {
        String sql = "SELECT * FROM boxtrack.match WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Match(
                        rs.getInt("id"),
                        rs.getInt("boxer1_id"),
                        rs.getInt("boxer2_id"),
                        rs.getInt("event_id"),
                        rs.getString("result")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateMatch(Match match) {
        String sql = "UPDATE boxtrack.match SET boxer1_id=?, boxer2_id=?, event_id=?, result=? WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, match.getBoxer1Id());
            ps.setInt(2, match.getBoxer2Id());
            ps.setInt(3, match.getEventId());
            ps.setString(4, match.getResult());
            ps.setInt(5, match.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMatch(int id) {
        String sql = "DELETE FROM boxtrack.match WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Match> loadMatches() {
        List<Match> list = new ArrayList<>();
        String sql = "SELECT * FROM boxtrack.match";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Match(
                        rs.getInt("id"),
                        rs.getInt("boxer1_id"),
                        rs.getInt("boxer2_id"),
                        rs.getInt("event_id"),
                        rs.getString("result")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void saveMatches(List<Match> matches) {
        String sql = "DELETE FROM boxtrack.match";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        for(Match m : matches) {
            createMatch(m);
        }
    }

    @Override
    public void createEvent(Event event) {
        String sql = "INSERT INTO boxtrack.event (name, location, event_date) VALUES (?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, event.getName());
            ps.setString(2, event.getLocation());
            ps.setDate(3, Date.valueOf(event.getEventDate()));
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Event readEvent(int id) {
        String sql = "SELECT * FROM boxtrack.event WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getDate("event_date").toLocalDate()
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEvent(Event event) {
        String sql = "UPDATE boxtrack.event SET name=?, location=?, event_date=? WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, event.getName());
            ps.setString(2, event.getLocation());
            ps.setDate(3, Date.valueOf(event.getEventDate()));
            ps.setInt(4, event.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(int id) {
        String sql = "DELETE FROM boxtrack.event WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> loadEvents() {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT * FROM boxtrack.event";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getDate("event_date").toLocalDate()
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void saveEvents(List<Event> events) {
        String sql = "DELETE FROM boxtrack.event";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        for(Event e : events) {
            createEvent(e);
        }
    }

    public void assignCoachToBoxer(int boxerId, int coachId) {
        String sql = "INSERT INTO boxtrack.boxer_coach(boxer_id, coach_id) VALUES (?, ?)";
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, boxerId);
                ps.setInt(2, coachId);
                ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
    }
}
