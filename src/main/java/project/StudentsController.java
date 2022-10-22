package project;

import java.util.*;

public class StudentsController {

    private final Map<Integer, Student> students;

    public StudentsController() {
        this.students = new LinkedHashMap<>();
    }

    public int getStudentsNumber() {
        return students.size();
    }

    public Set<Integer> getStudentsIDSet() {
        return this.students.keySet();
    }

    public Student getStudent(int id) {
        return students.get(id);
    }

    public boolean addStudent(String input) {
        Student student = getStudentObject(input);
        if (student == null) {
            return false;
        }

        if (students.containsKey(student.hashCode())) {
            System.out.println("email is already taken.");
            return false;
        } else {
            students.put(student.hashCode(), student);
            System.out.println("The student has been added.");
            return true;
        }
    }

    private Student getStudentObject(String input) {
        String[] credentials = getStudentCredential(input);

        if (credentials == null) {
            return null;
        } else {
            return new Student(credentials[0], credentials[1], credentials[2]);
        }
    }

    private String[] getStudentCredential(String input) {
        String[] inputArray = input.split(" ");
        if (inputArray.length < 3) {
            System.out.println("Incorrect credentials");
            return null;
        }

        String[] credentials = new String[3];
        credentials[0] = inputArray[0];
        credentials[1] = getLastName(inputArray);
        credentials[2] = inputArray[inputArray.length - 1];

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

    private boolean validCredentials(String[] credentials) {
        if (!credentials[0].matches("([a-zA-Z][\\-']?)+[a-zA-Z]$")) {
            System.out.println("Incorrect first name");
            return false;
        }

        if (!credentials[1].matches("([a-zA-Z][\\-' ]?)+[a-zA-Z]$")) {
            System.out.println("Incorrect last name");
            return false;
        }

        if (!credentials[2].matches("[^@]+@[^@.]+\\.[^@.]+")) {
            System.out.println("Incorrect email");
            return false;
        }

        return true;
    }
}
