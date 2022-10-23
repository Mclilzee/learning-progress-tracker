package project;

import project.statistics.Statistics;

import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Statistics statistics = new Statistics();

    public static void main(String[] args) {
        optionsMenu();
    }

    private static void optionsMenu() {
        System.out.println("Learning Progress Tracker");

        boolean quit = false;
        while (!quit) {
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "":
                    System.out.println("No input");
                    break;
                case "exit":
                    System.out.println("Bye!");
                    quit = true;
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program.");
                    break;
                case "add students":
                    handleStudentsAdding();
                    break;
                case "list":
                    handleStudentsIDPrinting();
                    break;
                case "add points":
                    addPointsToStudent();
                    break;
                case "find":
                    printStudentPointsInformation();
                    break;
                default:
                    System.out.println("unknown command!");
                    break;
            }
        }
    }

    private static void handleStudentsAdding() {
        System.out.println("Enter student credentials or 'back' to return");
        int count = 0;
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            try {
                statistics.addStudent(input);
                count++;
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.printf("Total %d student%s have been added.", count, count == 1 ? "" : "s");
    }

    private static void addPointsToStudent() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            try {
                statistics.addPointsToStudent(input);
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printStudentPointsInformation() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            try {
                statistics.printStudentPointsInformation(input);
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleStudentsIDPrinting() {
        Set<Integer> idSet = statistics.getStudentIDSet();
        if (idSet.isEmpty()) {
            System.out.println("No students found");
            return;
        }
        System.out.println("Students:");
        for (int id : idSet) {
            System.out.println(id);
        }
    }
}