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
            System.out.println("This email is already taken.");
            return false;
        } else {
            students.put(student.hashCode(), student);
            System.out.println("The student has been added.");
            return true;
        }
    }

    private Student getStudentObject(String input) {
        String[] inputArray = input.split(" ");
        if (inputArray.length < 3) {
            System.out.println("Incorrect credentials");
            return null;
        }

        String firstName = inputArray[0];
        String lastName = getLastName(inputArray);
        String email = inputArray[inputArray.length - 1];
        Student student = new Student(firstName, lastName, email);

        if (validCredentials(student)) {
            return student;
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

    private boolean validCredentials(Student student) {
        if (!student.getFirstName().matches("([a-zA-Z][\\-']?)+[a-zA-Z]$")) {
            System.out.println("Incorrect first name");
            return false;
        }

        if (!student.getLastName().matches("([a-zA-Z][\\-' ]?)+[a-zA-Z]$")) {
            System.out.println("Incorrect last name");
            return false;
        }

        if (!student.getEmail().matches("[^@]+@[^@.]+\\.[^@.]+")) {
            System.out.println("Incorrect email");
            return false;
        }

        return true;
    }
}
