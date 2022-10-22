package project;

public class StudentIDGenerator {
    private static int id = 1;

    public static int getID() {
        return id++;
    }
}
