package org.example.boxtrack.UI;

import org.example.boxtrack.Model.Boxer;
import org.example.boxtrack.Model.Coach;
import org.example.boxtrack.Model.Event;
import org.example.boxtrack.Model.Match;

import java.time.LocalDate;

public class BoxTrackApp {
    public static void main(String[] args) {
        System.out.println("Welcome to BoxTrack!");
        System.out.println();

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
        System.out.println();

        Coach c1 = new Coach();
        c1.setId(1);
        c1.setName("Jose Benavidez Sr.");
        c1.setExperienceYears(20);

        Coach c2 = new Coach("Eddy Reynoso", 20);
        c2.setId(2);

        Coach c3 = new Coach(3, "Ismael Salas", 40);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println();

        Match m1 = new Match();
        m1.setId(1);
        m1.setBoxer1Id(11);
        m1.setBoxer2Id(12);
        m1.setEventId(1);
        m1.setResult("Boxer 11 won by UD.");
        System.out.println(m1);

        Match m2 = new Match(21, 22, 2, "Boxer 21 lost by UD.");
        m2.setId(2);
        System.out.println(m2);

        Match m3 = new Match(2, 21, 22, 2, "Boxer 22 won by UD.");
        System.out.println(m3);
        System.out.println();

        Event e1 = new Event();
        e1.setId(1);
        e1.setName("Benavidez vs. Morrell");
        e1.setLocation("Las Vegas, Nevada");
        e1.setEventDate(LocalDate.of(2025, 2, 1));

        Event e2 = new Event("Garcia vs. Romero", "New York City, New York", LocalDate.of(2025, 5, 2));
        b2.setId(2);

        System.out.println(e1);
        System.out.println(e2);
    }
}
