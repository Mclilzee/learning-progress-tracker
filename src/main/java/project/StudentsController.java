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

    public Set<Course> getMostPopularCourses() {
        Map<Course, Integer> coursesEnrollment = getMostPopularCourseEnrollment();
        Set<Course> courses = new LinkedHashSet<>();
        int mostPopularCourseEnrollmentNumber = coursesEnrollment.values().stream().max(Integer::compareTo).orElse(0);
        for (var entrySet : coursesEnrollment.entrySet()) {
            if (entrySet.getValue() == mostPopularCourseEnrollmentNumber) {
                courses.add(entrySet.getKey());
            }
        }

        return courses;
    }

    private Map<Course, Integer> getMostPopularCourseEnrollment() {
        Map<Course, Integer> courses = new HashMap<>();
        for (Student student : students.values()) {
            addEachCourseEnrollment(student, courses);
        }

        return courses;
    }

    private void addEachCourseEnrollment(Student student, Map<Course, Integer> courses) {
        for (Course course : student.getEnrolledCourseSet()) {
            courses.put(course, courses.getOrDefault(course, 0) + 1);
        }
    }

    public void addStudent(String input) throws IllegalArgumentException {
        Student student = getStudentObject(input);

        if (students.containsKey(student.hashCode())) {
            throw new IllegalArgumentException("This email is already taken");
        }

        students.put(student.hashCode(), student);
    }

    private Student getStudentObject(String input) {
        String[] inputArray = input.split(" ");
        if (inputArray.length < 3) {
            throw new IllegalArgumentException("Incorrect credentials");
        }

        String firstName = inputArray[0];
        String lastName = getLastName(inputArray);
        String email = inputArray[inputArray.length - 1];
        Student student = new Student(firstName, lastName, email);

        if (isValidCredentials(student)) {
            return student;
        }

        throw new IllegalStateException();
    }

    private boolean isValidCredentials(Student student) {
        if (!student.getFirstName().matches("([a-zA-Z][\\-']?)+[a-zA-Z]$")) {
            throw new IllegalArgumentException("Incorrect first name");
        }

        if (!student.getLastName().matches("([a-zA-Z][\\-' ]?)+[a-zA-Z]$")) {
            throw new IllegalArgumentException("Incorrect last name");
        }

        if (!student.getEmail().matches("[^@]+@[^@.]+\\.[^@.]+")) {
            throw new IllegalArgumentException("Incorrect email");
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
