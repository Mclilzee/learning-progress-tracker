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
            switch (scanner.nextLine().toLowerCase()) {
                case "":
                    System.out.println("No input");
                    break;
                case "exit":
                    System.out.println("Bye!");
                    quit = true;
                    break;
                default:
                    System.out.println("Error: unknown command!");
            }
        }
    }
}