package org.example.boxtrack.Model;

public class Match {
    // Fields
    private int id;
    private int boxer1Id;
    private int boxer2Id;
    private int eventId;
    private String result;

    // Constructor
    public Match() {
    }

    public Match(int id, int boxer1Id, int boxer2Id, int eventId, String result) {
        this.id = id;
        this.boxer1Id = boxer1Id;
        this.boxer2Id = boxer2Id;
        this.eventId = eventId;
        this.result = result;
    }

    public Match(int boxer1Id, int boxer2Id, int eventId, String result) {
        this.boxer1Id = boxer1Id;
        this.boxer2Id = boxer2Id;
        this.eventId = eventId;
        this.result = result;
    }

    // Methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoxer1Id() {
        return boxer1Id;
    }

    public void setBoxer1Id(int boxer1Id) {
        this.boxer1Id = boxer1Id;
    }

    public int getBoxer2Id() {
        return boxer2Id;
    }

    public void setBoxer2Id(int boxer2Id) {
        this.boxer2Id = boxer2Id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("Match{id=%d, boxer1Id=%d, boxer2Id=%d, eventId=%d, result=%s}", id, boxer1Id, boxer2Id, eventId, result);
    }
}
