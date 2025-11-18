package org.example.boxtrack.Model;

public class Coach {
    // Fields
    private int id;
    private String name;
    private int experienceYears;

    // Constructor
    public Coach() {
    }

    public Coach(int id, String name, int experienceYears) {
        this.id = id;
        this.name = name;
        this.experienceYears = experienceYears;
    }

    public Coach(String name, int experienceYears) {
        this.name = name;
        this.experienceYears = experienceYears;
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

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return String.format("Coach{id=%d, name=%s, experienceYears=%d}", id, name, experienceYears);
    }
}
