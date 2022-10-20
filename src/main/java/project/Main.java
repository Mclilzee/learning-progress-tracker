package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner;
    private static final List<Student> students;

    static {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

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
                    handleStudentsAdding();
                    break;
                default:
                    System.out.println("Error: unknown command!");
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

            if (addStudent(input)) {
                System.out.println("The student has been added.");
            } else {
                System.out.println("Incorrect credentials.");
            }
        }
    }

    public static boolean addStudent(String input) {
        String[] credentials = input.split(" ");
        if (validateStudentCredentials(credentials)) {
            students.add(new Student(credentials[0], credentials[1], credentials[2]));
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateStudentCredentials(String[] input) {
        if (input.length != 3) {
            return false;
        }

        return true;
    }
}