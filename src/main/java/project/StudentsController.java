package project;

import java.util.*;

public class StudentsController {

    private final List<Student> students;

    public StudentsController() {
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean addStudent(String input) {
        Map<String, String> credentials = getStudentCredential(input);
        if (credentials == null) {
            return false;
        }

        boolean added = this.students.add(new Student(credentials.get("firstName"), credentials.get("lastName"), credentials.get("email")));
        if (added) {
            System.out.println("The student has been added.");
            return true;
        } else {
            System.out.println("Failed to add new student");
            return false;
        }
    }

    private Map<String, String> getStudentCredential(String input) {
        String[] inputArray = input.split(" ");
        if (inputArray.length < 3) {
            System.out.println("Incorrect credentials");
            return null;
        }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("firstName", inputArray[0]);
        credentials.put("lastName", getLastName(inputArray));
        credentials.put("email", inputArray[inputArray.length - 1]);

        if (validCredentials(credentials)) {
            return credentials;
        } else {
            return null;
        }
    }

    private String getLastName(String[] inputArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < inputArray.length - 1; i++) {
            builder.append(inputArray[i]).append(" ");
        }

        return builder.toString().trim();
    }

    private boolean validCredentials(Map<String, String> credentials) {
        if (!credentials.get("firstName").matches("([a-zA-Z][\\-']?)+[a-zA-Z]$")) {
            System.out.println("Incorrect first name");
            return false;
        }

        if (!credentials.get("lastName").matches("([a-zA-Z][\\-' ]?)+[a-zA-Z]$")) {
            System.out.println("Incorrect last name");
            return false;
        }

        if (!credentials.get("email").matches("[^@]+@[^@.]+\\.[^@.]+")) {
            System.out.println("Incorrect email");
            return false;
        }

        return true;
    }
}
