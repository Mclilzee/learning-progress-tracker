package project;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

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
                case "add students":
                    addStudents();
                    break;
                default:
                    System.out.println("Error: unknown command!");
            }
        }
    }

    private static void addStudents() {
        System.out.println("Enter student credentials or 'back' to return");
        while (true) {
            String input = scanner.nextLine();
            if ("back".equalsIgnoreCase(input)) {
                break;
            }

            addStudent(input);
        }
    }

    public static boolean addStudent(String input) {
        return false;
    }

    private static String[] getStudentFromInput(String input) {
        return null;
    }
}