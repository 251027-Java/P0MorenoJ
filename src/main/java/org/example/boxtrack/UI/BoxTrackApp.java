package org.example.boxtrack.UI;

import org.example.boxtrack.Model.Boxer;
import org.example.boxtrack.Model.Coach;
import org.example.boxtrack.Model.Event;
import org.example.boxtrack.Model.Match;
import org.example.boxtrack.Repository.IRepository;
import org.example.boxtrack.Repository.PostgreSQLRepository;
import org.example.boxtrack.Service.BoxTrackService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BoxTrackApp {
//    public static void main(String[] args) {
//        System.out.println("Welcome to BoxTrack!");
//        System.out.println();
//
//        Boxer b1 = new Boxer();
//        b1.setId(1);
//        b1.setName("David Benavidez");
//        b1.setWeightClass("Light Heavyweight");
//        b1.setRanking(1);
//
//        Boxer b2 = new Boxer("Ryan Garcia", "Welterweight", 1);
//        b2.setId(2);
//
//        Boxer b3 = new Boxer(3, "Rolando Romero", "Welterweight", 2);
//
//        System.out.println(b1);
//        System.out.println(b2);
//        System.out.println(b3);
//        System.out.println();
//
//        Coach c1 = new Coach();
//        c1.setId(1);
//        c1.setName("Jose Benavidez Sr.");
//        c1.setExperienceYears(20);
//
//        Coach c2 = new Coach("Eddy Reynoso", 20);
//        c2.setId(2);
//
//        Coach c3 = new Coach(3, "Ismael Salas", 40);
//
//        System.out.println(c1);
//        System.out.println(c2);
//        System.out.println(c3);
//        System.out.println();
//
//        Match m1 = new Match();
//        m1.setId(1);
//        m1.setBoxer1Id(11);
//        m1.setBoxer2Id(12);
//        m1.setEventId(1);
//        m1.setResult("Boxer 11 won by UD.");
//        System.out.println(m1);
//
//        Match m2 = new Match(21, 22, 2, "Boxer 21 lost by UD.");
//        m2.setId(2);
//        System.out.println(m2);
//
//        Match m3 = new Match(2, 21, 22, 2, "Boxer 22 won by UD.");
//        System.out.println(m3);
//        System.out.println();
//
//        Event e1 = new Event();
//        e1.setId(1);
//        e1.setName("Benavidez vs. Morrell");
//        e1.setLocation("Las Vegas, Nevada");
//        e1.setEventDate(LocalDate.of(2025, 2, 1));
//
//        Event e2 = new Event("Garcia vs. Romero", "New York City, New York", LocalDate.of(2025, 5, 2));
//        b2.setId(2);
//
//        System.out.println(e1);
//        System.out.println(e2);
//        System.out.println();
//
//        IRepository repo =  new PostgreSQLRepository();

//        repo.createBoxer(b1);
//        Boxer newBoxer1 = repo.readBoxer(1);
//        System.out.println(newBoxer1);
//
//        repo.createBoxer(b2);
//        Boxer newBoxer2 = repo.readBoxer(2);
//        System.out.println(newBoxer2);
//
//        newBoxer1.setWeightClass("Super Middleweight");
//        repo.updateBoxer(newBoxer1);
//        System.out.println("Boxer updated successfully!");

//    }

    private static final BoxTrackService service = new BoxTrackService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to BoxTrack!");
        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBoxer();
                    break;
                case "2":
                    listBoxers();
                    break;
                case "3":
                    deleteBoxer();
                    break;
                case "4":
                    addCoach();
                    break;
                case "5":
                    listCoaches();
                    break;
                case "6":
                    deleteCoach();
                    break;
                case "7":
                    addEvent();
                    break;
                case "8":
                    listEvents();
                    break;
                case "9":
                    deleteEvent();
                    break;
                case "10":
                    addMatch();
                    break;
                case "11":
                    listMatches();
                    break;
                case "12":
                    deleteMatch();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Boxer");
        System.out.println("2. View Boxers");
        System.out.println("3. Delete Boxer");
        System.out.println("4. Add Coach");
        System.out.println("5. View Coaches");
        System.out.println("6. Delete Coach");
        System.out.println("7. Add Event");
        System.out.println("8. View Events");
        System.out.println("9. Delete Event");
        System.out.println("10. Add Match");
        System.out.println("11. View Matches");
        System.out.println("12. Delete Match");
        System.out.println("0. Exit");
    }

    // Boxer
    private static void addBoxer() {
        System.out.print("Enter boxer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter weight class: ");
        String weightClass = scanner.nextLine();
        System.out.print("Enter ranking: ");
        int ranking = Integer.parseInt(scanner.nextLine());

        try {
            service.addBoxer(new Boxer(name, weightClass, ranking));
            System.out.println("Boxer added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listBoxers() {
        List<Boxer> boxers = service.getAllBoxers();
        if (boxers.isEmpty()) {
            System.out.println("No boxers found.");
        } else {
            boxers.forEach(b -> System.out.println(b.getId() + ": " + b.getName() + " (" + b.getWeightClass() + ") Ranking: " + b.getRanking()));
        }
    }

    // Coach
    private static void addCoach() {
        System.out.print("Enter coach name: ");
        String name = scanner.nextLine();
        System.out.print("Enter experience years: ");
        int exp = Integer.parseInt(scanner.nextLine());

        try {
            service.addCoach(new Coach(name, exp));
            System.out.println("Coach added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listCoaches() {
        List<Coach> coaches = service.getAllCoaches();
        if (coaches.isEmpty()) {
            System.out.println("No coaches found.");
        } else {
            coaches.forEach(c -> System.out.println(c.getId() + ": " + c.getName() + " (" + c.getExperienceYears() + " years)"));
        }
    }

    // Event
    private static void addEvent() {
        System.out.print("Enter event name: ");
        String name = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter event date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        try {
            service.addEvent(new Event(name, location, date));
            System.out.println("Event added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listEvents() {
        List<Event> events = service.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            events.forEach(e -> System.out.println(e.getId() + ": " + e.getName() + " at " + e.getLocation() + " on " + e.getEventDate()));
        }
    }

    // Match
    private static void addMatch() {
        System.out.print("Enter boxer1 ID: ");
        int boxer1Id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter boxer2 ID: ");
        int boxer2Id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter event ID: ");
        int eventId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter result: ");
        String result = scanner.nextLine();

        try {
            service.addMatch(new Match(boxer1Id, boxer2Id, eventId, result));
            System.out.println("Match added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listMatches() {
        List<Match> matches = service.getAllMatches();
        if (matches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            matches.forEach(m -> System.out.println(m.getId() + ": Boxer1=" + m.getBoxer1Id() + ", Boxer2=" + m.getBoxer2Id() +
                    ", Event=" + m.getEventId() + ", Result=" + m.getResult()));
        }
    }

    private static void deleteBoxer() {
        System.out.print("Enter Boxer ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            service.deleteBoxer(id);
            System.out.println("Boxer deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteCoach() {
        System.out.print("Enter Coach ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            service.deleteCoach(id);
            System.out.println("Coach deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteEvent() {
        System.out.print("Enter Event ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            service.deleteEvent(id);
            System.out.println("Event deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteMatch() {
        System.out.print("Enter Match ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            service.deleteMatch(id);
            System.out.println("Match deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
