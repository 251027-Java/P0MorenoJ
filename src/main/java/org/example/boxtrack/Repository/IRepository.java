package org.example.boxtrack.Repository;

import org.example.boxtrack.Model.Boxer;
import org.example.boxtrack.Model.Coach;
import org.example.boxtrack.Model.Event;
import org.example.boxtrack.Model.Match;

import java.util.List;

public interface IRepository {
    // Boxer
    void createBoxer(Boxer boxer);
    Boxer readBoxer(int id);
    void updateBoxer(Boxer boxer);
    void deleteBoxer(int id);
    List<Boxer> loadBoxers();
    void saveBoxers(List<Boxer> boxers);

    // Coach
    void createCoach(Coach coach);
    Coach readCoach(int id);
    void updateCoach(Coach coach);
    void deleteCoach(int id);
    List<Coach> loadCoaches();
    void saveCoaches(List<Coach> coaches);

    // Match
    void createMatch(Match match);
    Match readMatch(int id);
    void updateMatch(Match match);
    void deleteMatch(int id);
    List<Match> loadMatches();
    void saveMatches(List<Match> matches);

    // Event
    void createEvent(Event event);
    Event readEvent(int id);
    void updateEvent(Event event);
    void deleteEvent(int id);
    List<Event> loadEvents();
    void saveEvents(List<Event> events);
}
