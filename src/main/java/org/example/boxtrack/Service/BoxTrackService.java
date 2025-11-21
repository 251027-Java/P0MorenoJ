package org.example.boxtrack.Service;

import org.example.boxtrack.Model.Boxer;
import org.example.boxtrack.Model.Coach;
import org.example.boxtrack.Model.Event;
import org.example.boxtrack.Model.Match;
import org.example.boxtrack.Repository.IRepository;
import org.example.boxtrack.Repository.PostgreSQLRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BoxTrackService {
    // Fields
    private final IRepository repo;

    // Constructors
    public BoxTrackService() {
        this.repo = new PostgreSQLRepository();
    }

    // Methods
    // Boxer
    public void addBoxer(Boxer boxer) {
        if(boxer == null) {
            throw new IllegalArgumentException("Boxer cannot be null.");
        }
        if(boxer.getName() == null || boxer.getName().isBlank()) {
            throw new IllegalArgumentException("Boxer name cannot be empty.");
        }
        if(boxer.getWeightClass() == null || boxer.getWeightClass().isBlank()) {
            throw new IllegalArgumentException("Boxer weight class cannot be empty.");
        }

        boolean boxerAlreadyExists = repo.loadBoxers().stream().anyMatch(b -> b.getName().equalsIgnoreCase(boxer.getName()));
        if(boxerAlreadyExists) {
            throw new IllegalArgumentException("Boxer with this name already exists.");
        }

        repo.createBoxer(boxer);
    }

    public Boxer getBoxer(int id) {
        Boxer boxer = repo.readBoxer(id);
        if(boxer == null) {
            throw new IllegalArgumentException("Boxer not found with id: " + id);
        }
        return boxer;
    }

    public void updateBoxer(Boxer boxer) {
        if(repo.readBoxer(boxer.getId()) == null) {
            throw new IllegalArgumentException("Boxer not found with id: " + boxer.getId());
        }
        repo.updateBoxer(boxer);
    }

    public void deleteBoxer(int id) {
        repo.deleteBoxer(id);
    }

    public List<Boxer> getAllBoxers() {
        return repo.loadBoxers();
    }

    // Coach
    public void addCoach(Coach coach) {
        if(coach == null) {
            throw new IllegalArgumentException("Coach cannot be null.");
        }
        if(coach.getName() == null || coach.getName().isBlank()) {
            throw new IllegalArgumentException("Coach name cannot be empty.");
        }
        if(coach.getExperienceYears() < 0) {
            throw new IllegalArgumentException("Coach experience cannot be negative.");
        }

        boolean coachAlreadyExists = repo.loadCoaches().stream().anyMatch(c -> c.getName().equalsIgnoreCase(coach.getName()));
        if(coachAlreadyExists) {
            throw new IllegalArgumentException("Coach with this name already exists.");
        }

        repo.createCoach(coach);
    }

    public Coach getCoach(int id) {
        Coach coach = repo.readCoach(id);
        if(coach == null) {
            throw new IllegalArgumentException("Coach not found with id: " + id);
        }
        return coach;
    }

    public void updateCoach(Coach coach) {
        if(repo.readCoach(coach.getId()) == null) {
            throw new IllegalArgumentException("Coach not found with id: " + coach.getId());
        }
        repo.updateCoach(coach);
    }

    public void deleteCoach(int id) {
        repo.deleteCoach(id);
    }

    public List<Coach> getAllCoaches() {
        return repo.loadCoaches();
    }

    // Event
    public void addEvent(Event event) {
        if(event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        if(event.getName() == null || event.getName().isBlank()) {
            throw new IllegalArgumentException("Event name cannot be empty.");
        }
        if(event.getEventDate() == null) {
            throw new IllegalArgumentException("Event date cannot be null.");
        }

        repo.createEvent(event);
    }

    public Event getEvent(int id) {
        Event event = repo.readEvent(id);
        if(event == null) {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
        return event;
    }

    public void updateEvent(Event event) {
        if(repo.readEvent(event.getId()) == null) {
            throw new IllegalArgumentException("Event not found with id: " + event.getId());
        }
        repo.updateEvent(event);
    }

    public void deleteEvent(int id) {
        repo.deleteEvent(id);
    }

    public List<Event> getAllEvents() {
        return repo.loadEvents();
    }

    // Match
    public void addMatch(Match match) {
        if(match == null) {
            throw new IllegalArgumentException("Match cannot be null.");
        }
        if(repo.readBoxer(match.getBoxer1Id()) == null) {
            throw new IllegalArgumentException("Boxer1 does not exist with id: " + match.getBoxer1Id());
        }
        if(repo.readBoxer(match.getBoxer2Id()) == null) {
            throw new IllegalArgumentException("Boxer2 does not exist with id: " + match.getBoxer2Id());
        }
        if(repo.readEvent(match.getEventId()) == null) {
            throw new IllegalArgumentException("Event does not exist with id: " + match.getEventId());
        }

        repo.createMatch(match);
    }

    public Match getMatch(int id) {
        Match match = repo.readMatch(id);
        if(match == null) {
            throw new IllegalArgumentException("Match not found with id: " + id);
        }
        return match;
    }

    public void updateMatch(Match match) {
        if(repo.readMatch(match.getId()) == null) {
            throw new IllegalArgumentException("Match not found with id: " + match.getId());
        }
        repo.updateMatch(match);
    }

    public void deleteMatch(int id) {
        repo.deleteMatch(id);
    }

    public List<Match> getAllMatches() {
        return repo.loadMatches();
    }
}
