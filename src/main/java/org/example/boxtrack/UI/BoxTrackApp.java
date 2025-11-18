package org.example.boxtrack.UI;

import org.example.boxtrack.Model.Boxer;

public class BoxTrackApp {
    public static void main(String[] args) {
        System.out.println("Welcome to BoxTrack!");

        Boxer b1 = new Boxer();
        b1.setId(1);
        b1.setName("David Benavidez");
        b1.setWeightClass("Light Heavyweight");
        b1.setRanking(1);

        Boxer b2 = new Boxer("Ryan Garcia", "Welterweight", 1);
        b2.setId(2);

        Boxer b3 = new Boxer(3, "Rolando Romero", "Welterweight", 2);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}
