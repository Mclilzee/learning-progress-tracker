package project;

import java.util.Arrays;
import java.util.Scanner;

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
                    statistics.printStudentsIDList();
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
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            statistics.addStudent(input);
        }

        statistics.printStudentsTotalAdded();

    }

    private static void addPointsToStudent() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            if (!input.matches("\\d+( \\d+){4}")) {
                System.out.println("Incorrect points format");
                continue;
            }

            int[] inputNumbers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

            Student student = studentsController.getStudent(inputNumbers[0]);
            if (student == null) {
                System.out.println("No student is found for id=" + inputNumbers[0]);
                continue;
            }

            student.addScores(Arrays.stream(inputNumbers).skip(1).toArray());
            System.out.println("Points updated.");
        }
    }

    private static void printStudentPointsInformation() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            if (!input.matches("\\d+")) {
                System.out.println("Incorrect student id format");
                continue;
            }

            Student student = studentsController.getStudent(Integer.parseInt(input));
            if (student == null) {
                System.out.println("No student is found for id=" + input);
            } else {
                System.out.println(student);
            }
        }
    }
}