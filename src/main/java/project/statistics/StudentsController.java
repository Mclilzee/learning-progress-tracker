package project.statistics;

import project.IncorrectInput;

import java.util.*;

final class StudentsController {

    private final Map<Integer, Student> students;

    StudentsController() {
        this.students = new LinkedHashMap<>();
    }

    Set<Integer> getStudentsIDSet() {
        return this.students.keySet();
    }

    List<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    Student getStudent(int id) {
        return students.get(id);
    }

    void addStudent(String input) throws IllegalArgumentException, IncorrectInput {
        Student student = getStudentObject(input);

        if (students.containsKey(student.hashCode())) {
            throw IncorrectInput.emailTaken();
        }

        students.put(student.hashCode(), student);
    }

    private Student getStudentObject(String input) throws IncorrectInput {
        String[] inputArray = input.split(" ");
        if (inputArray.length < 3) {
            throw IncorrectInput.incorrectCredentials();
        }

        String firstName = inputArray[0];
        String lastName = getLastName(inputArray);
        String email = inputArray[inputArray.length - 1];
        Student student = new Student(firstName, lastName, email);

        if (isValidCredentials(student)) {
            return student;
        }

        throw IncorrectInput.incorrectCredentials();
    }

    private boolean isValidCredentials(Student student) throws IncorrectInput {
        if (!student.getFirstName().matches("([a-zA-Z][\\-']?)+[a-zA-Z]$")) {
            throw IncorrectInput.incorrectFirstName();
        }

        if (!student.getLastName().matches("([a-zA-Z][\\-' ]?)+[a-zA-Z]$")) {
            throw IncorrectInput.incorrectLastName();
        }

        if (!student.getEmail().matches("[^@]+@[^@.]+\\.[^@.]+")) {
            throw IncorrectInput.incorrectEmail();
        }

        return true;
    }

    private String getLastName(String[] inputArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < inputArray.length - 1; i++) {
            builder.append(inputArray[i]).append(" ");
        }

        return builder.toString().trim();
    }
}
