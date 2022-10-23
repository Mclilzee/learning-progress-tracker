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
            throw new IncorrectInput("This email is already taken");
        }

        students.put(student.hashCode(), student);
    }

    private Student getStudentObject(String input) throws IncorrectInput {
        String[] inputArray = input.split(" ");
        if (inputArray.length < 3) {
            throw new IncorrectInput("Incorrect credentials");
        }

        String firstName = inputArray[0];
        String lastName = getLastName(inputArray);
        String email = inputArray[inputArray.length - 1];
        Student student = new Student(firstName, lastName, email);

        if (isValidCredentials(student)) {
            return student;
        }

        throw new IncorrectInput("Incorrect credentials");
    }

    private boolean isValidCredentials(Student student) throws IncorrectInput {
        if (!student.getFirstName().matches("([a-zA-Z][\\-']?)+[a-zA-Z]$")) {
            throw new IncorrectInput("Incorrect first name");
        }

        if (!student.getLastName().matches("([a-zA-Z][\\-' ]?)+[a-zA-Z]$")) {
            throw new IncorrectInput("Incorrect last name");
        }

        if (!student.getEmail().matches("[^@]+@[^@.]+\\.[^@.]+")) {
            throw new IncorrectInput("Incorrect email");
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
