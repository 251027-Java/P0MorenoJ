package org.example.boxtrack.Model;

import java.time.LocalDate;

public class Event {
    // Fields
    private int id;
    private String name;
    private String location;
    private LocalDate eventDate;

    // Constructor
    public Event() {
    }

    public Event(int id, String name, String location, LocalDate eventDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.eventDate = eventDate;
    }

    public Event(String name, String location, LocalDate eventDate) {
        this.name = name;
        this.location = location;
        this.eventDate = eventDate;
    }

    // Methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return String.format("Event{id=%d, name=%s, location=%s, eventDate=%s}", id, name, location, eventDate);
    }
}
