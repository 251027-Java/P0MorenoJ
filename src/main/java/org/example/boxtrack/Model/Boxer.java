package org.example.boxtrack.Model;

public class Boxer {
    // Fields
    private int id;
    private String name;
    private String weightClass;
    private int ranking;

    // Constructors
    public Boxer() {
    }

    public Boxer(int id, String name, String weightClass, int ranking) {
        this.id = id;
        this.name = name;
        this.weightClass = weightClass;
        this.ranking = ranking;
    }

    public Boxer(String name, String weightClass, int ranking) {
        this.name = name;
        this.weightClass = weightClass;
        this.ranking = ranking;
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

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return String.format("Boxer{id=%d, name=%s, weightClass=%s, ranking=%d}", id, name, weightClass, ranking);
    }
}
