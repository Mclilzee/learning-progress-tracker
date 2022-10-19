package project;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        optionsMenu();
    }

    private static void optionsMenu() {
        String input = scanner.nextLine().toLowerCase();

        boolean quit = false;
        while (!quit) {
            switch (input) {
                case "":
                    System.out.println("No input");
                    break;
                case "exit":
                    System.out.println("Bye!");
                    quit = true;
                    break;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }
}