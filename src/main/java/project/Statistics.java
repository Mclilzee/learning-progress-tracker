package project;

import java.util.Arrays;

public class Statistics {

    private final StudentsController studentsController;
    private final CoursesController coursesController;

    public Statistics() {
        this.studentsController = new StudentsController();
        this.coursesController = new CoursesController();
    }

    public void addStudent(String input) {
        try {
            studentsController.addStudent(input);
            System.out.println("The student has been added");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printStudentsTotalAdded() {
        int totalSize = studentsController.getStudents().size();
        System.out.printf("Total %d student%s have been added.\n", totalSize, totalSize == 1 ? "" : "s");
    }

    public void printStudentsIDList() {
        if (studentsController.getStudents().size() == 0) {
            System.out.println("No students found");
            return;
        }

        System.out.println("Students:");
        for (int id : studentsController.getStudentsIDSet()) {
            System.out.println(id);
        }
    }

    public void printStudentPointsInformation(String input) {
        try {
            Student student = studentsController.getStudent(input);
            coursesController.printStudentPoints(student);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addPointsToStudent(String arguments) {
        String[] input = arguments.split(" ");
        if (input.length != 5) {
            System.out.println("Incorrect points format");
            return;
        }

        try {
            Student student = studentsController.getStudent(input[0]);
            coursesController.addPointsToStudent(student, Arrays.stream(input).skip(1).mapToInt(Integer::parseInt).toArray());
            System.out.println("Points updated.");
        } catch (NumberFormatException e) {
            System.out.println("Incorrect points format");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
