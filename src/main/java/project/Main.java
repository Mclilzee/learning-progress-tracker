package project;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentsController studentsController = new StudentsController();

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
                default:
                    System.out.println("Error: unknown command!");
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

            studentsController.addStudent(input);
        }

        int totalSize = studentsController.getStudentsNumber();
        System.out.printf("Total %d student%s have been added.\n", totalSize, totalSize == 1 ? "" : "s");
    }
}