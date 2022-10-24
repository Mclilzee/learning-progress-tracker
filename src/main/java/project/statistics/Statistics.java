package project.statistics;

import project.IncorrectInput;

import java.util.Arrays;
import java.util.List;
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
        String[] input = arguments.split(" ");
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
        List<Course> courses = coursesController.getMostPopularCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }

        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getLeastPopularCourses() {
        List<Course> courses = coursesController.getLeastPopularCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getHighestActivityCourses() {
        List<Course> courses = coursesController.getHighestActivityCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getLowestActivityCourses() {
        List<Course> courses = coursesController.getLowestActivityCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getEasiestCourses() {
        List<Course> courses = coursesController.getEasiestCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String getHardestCourses() {
        List<Course> courses = coursesController.getHardestCourses();
        if (courses.isEmpty()) {
            return "n/a";
        }
        return courses.stream().map(Course::getName).collect(Collectors.joining(", "));
    }

    public String[] getCourseStatistics(String input) throws IncorrectInput {
        String[] statistics = coursesController.getCourseStatistics(input);
        if (statistics.length <= 0) {
            throw IncorrectInput.noStudentsEnrolledInCourse(input);
        }

        return statistics;
    }
}
