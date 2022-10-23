package project.statistics;

import project.IncorrectInput;

import java.util.Arrays;
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
            throw new IncorrectInput("No student is found for id=" + id);
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
        String[] input = arguments.split(" ");
        if (input.length != coursesController.getNumberOfCourses() + 1) {
            System.out.println("Incorrect points format");
            return;
        }

        Student student = getStudent(input[0]);
        try {
            coursesController.addPointsToStudent(student, Arrays.stream(input).skip(1).mapToInt(Integer::parseInt).toArray());
        } catch (NumberFormatException e) {
            throw new IncorrectInput("Incorrect points format");
        }
    }

    public Set<Course> getMostPopularCourses() {
        return coursesController.getMostPopularCourses();
    }

    public Set<Course> getLeastPopularCourses() {
        return coursesController.getLeastPopularCourses();
    }

    public Set<Course> getHighestActivityCourses() {
        return coursesController.getHighestActivityCourses();
    }

    public Set<Course> getLowestActivityCourses() {
        return coursesController.getLowestActivityCourses();
    }
}
