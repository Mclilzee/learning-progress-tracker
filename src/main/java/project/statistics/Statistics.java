package project.statistics;

import project.IncorrectInput;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Statistics {

    private final StudentsController studentsController;
    private final CoursesController coursesController;

    public Statistics() {
        this.studentsController = new StudentsController();
        this.coursesController = new CoursesController();
    }

    public int getTotalStudents() {
        return studentsController.getStudents().size();
    }

    public Student getStudent(String id) throws IncorrectInput {
        if (!id.matches("\\d+") || !studentsController.getStudentsIDSet().contains(Integer.parseInt(id))) {
            throw IncorrectInput.incorrectStudentID(id);
        }

        return studentsController.getStudent(Integer.parseInt(id));
    }

    public void addStudent(String input) throws IncorrectInput {
        studentsController.addStudent(input);
    }

    public Set<Integer> getStudentIDSet() {
        return studentsController.getStudentsIDSet();
    }

    public void printStudentPointsInformation(String input) throws IncorrectInput {
        Student student = getStudent(input);
        coursesController.printStudentPoints(student);
    }

    public void addPointsToStudent(String arguments) throws IncorrectInput {
        String[] input = arguments.split("\\s");
        if (input.length != coursesController.getNumberOfCourses() + 1) {
            throw IncorrectInput.incorrectPointsFormat();
        }

        Student student = getStudent(input[0]);
        try {
            coursesController.addPointsToStudent(student, Arrays.stream(input).skip(1).mapToInt(Integer::parseInt).toArray());
        } catch (NumberFormatException e) {
            throw IncorrectInput.incorrectPointsFormat();
        }
    }

    public String getMostPopularCourses() {
        Set<Course> courses = coursesController.getMostPopularCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }

        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getLeastPopularCourses() {
        Set<Course> courses = coursesController.getLeastPopularCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getHighestActivityCourses() {
        Set<Course> courses = coursesController.getHighestActivityCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getLowestActivityCourses() {
        Set<Course> courses = coursesController.getLowestActivityCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getEasiestCourses() {
        Set<Course> courses = coursesController.getEasiestCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getHardestCourses() {
        Set<Course> courses = coursesController.getHardestCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String[] getCourseStatistics(String input) throws IncorrectInput {
        return coursesController.getCourseStatistics(input);
    }

    public void notifyStudents() {
        Map<Student, Set<Course>> students = coursesController.getStudentsCompletedCourses();
        for (Student student : students.keySet()) {
            notifyStudentAboutCourseCompletion(student, students.get(student));
        }

        System.out.printf("Total %d student%s have been notified", students.size(), students.size() == 1 ? "" : "s");
    }

    private void notifyStudentAboutCourseCompletion(Student student, Set<Course> courses) {
        for (Course course : courses) {
            System.out.println("To: " + student.getEmail());
            System.out.println("Re: Your Learning Progress");
            System.out.printf("Hello, %s! You have accomplished our %s course!%n", student, course.getName());
        }
    }
}
