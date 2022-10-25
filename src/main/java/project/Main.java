package project;

import project.statistics.Statistics;

import java.util.Arrays;
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
            switch (scanner.nextLine().toLowerCase().strip()) {
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
                case "statistics":
                    printStatistics();
                    break;
                case "notify":
                    statistics.notifyStudents();
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
                System.out.println("The student has been added");
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.printf("Total %d student%s have been added.\n", count, count == 1 ? "" : "s");
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
                System.out.println("Points updated.");
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

    private static void printStatistics() {
        System.out.println("Type the name of a course to see details or 'back to quit:");
        printOverAllStatistics();
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            try {
                Arrays.stream(statistics.getCourseStatistics(input)).forEach(System.out::println);
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printOverAllStatistics() {
        System.out.printf("Most popular: %s\n", statistics.getMostPopularCourses());
        System.out.printf("Least popular:: %s\n", statistics.getLeastPopularCourses());
        System.out.printf("Highest activity: %s\n", statistics.getHighestActivityCourses());
        System.out.printf("Lowest activity: %s\n", statistics.getLowestActivityCourses());
        System.out.printf("Easiest course: %s\n", statistics.getEasiestCourses());
        System.out.printf("Hardest course: %s\n", statistics.getHardestCourses());
    }
}