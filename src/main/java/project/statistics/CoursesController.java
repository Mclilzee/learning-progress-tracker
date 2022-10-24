package project.statistics;

import project.IncorrectInput;

import java.util.*;
import java.util.stream.Collectors;

class CoursesController {

    private final Course[] courses;

    CoursesController() {
        courses = new Course[Courses.values().length];
        int i = 0;
        for (Courses course : Courses.values()) {
            courses[i] = course.getCourse();
            i++;
        }
    }

    int getNumberOfCourses() {
        return this.courses.length;
    }

    void printStudentPoints(Student student) {
        StringBuilder builder = new StringBuilder().append(student.hashCode()).append(" points:");
        for (int i = 0; i < courses.length; i++) {
            builder.append(" ").append(courses[i].getName()).append("=").append(courses[i].getStudentScore(student));

            if (i != courses.length - 1) {
                builder.append(";");
            }
        }
        System.out.println(builder);
    }

    void addPointsToStudent(Student student, int[] scores) {
        for (int i = 0; i < courses.length; i++) {
            courses[i].addScore(student, scores[i]);
        }
    }

    String[] getCourseStatistics(String courseName) throws IncorrectInput {
        for (Course course : courses) {
            if (courseName.equalsIgnoreCase(course.getName())) {
                return course.getCourseStatistics();
            }
        }

        throw IncorrectInput.incorrectCourseName(courseName);
    }

    List<Course> getMostPopularCourses() {
        int mostPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).max().orElse(-1);
        List<Course> enrolled = Arrays.stream(courses).filter(course -> course.getStudents().size() == mostPopularCount).collect(Collectors.toList());
        return enrolled.size() != Courses.values().length ? enrolled : List.of();
    }

    List<Course> getLeastPopularCourses() {
        int leastPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).min().orElse(-1);
        List<Course> enrolled = Arrays.stream(courses).filter(course -> course.getStudents().size() == leastPopularCount).collect(Collectors.toList());
        return enrolled.size() != Courses.values().length ? enrolled : List.of();
    }

    List<Course> getHighestActivityCourses() {
        int highestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).max().orElse(-1);
        List<Course> activity = Arrays.stream(courses).filter(course -> course.getCompletedTasks() == highestActivityCount).collect(Collectors.toList());
        return activity.size() != Courses.values().length ? activity : List.of();
    }

    List<Course> getLowestActivityCourses() {
        int lowestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).min().orElse(-1);
        List<Course> activity = Arrays.stream(courses).filter(course -> course.getCompletedTasks() == lowestActivityCount).collect(Collectors.toList());
        return activity.size() != Courses.values().length ? activity : List.of();
    }

    List<Course> getEasiestCourses() {
        double highestAverage = Arrays.stream(courses).mapToDouble(Course::getAverageScores).max().orElse(-1);
        List<Course> average = Arrays.stream(courses).filter(course -> course.getAverageScores() == highestAverage).collect(Collectors.toList());
        return average.size() != Courses.values().length ? average : List.of();
    }

    List<Course> getHardestCourses() {
        double lowestAverage = Arrays.stream(courses).mapToDouble(Course::getAverageScores).min().orElse(-1);
        List<Course> average = Arrays.stream(courses).filter(course -> course.getAverageScores() == lowestAverage).collect(Collectors.toList());
        return average.size() != Courses.values().length ? average : List.of();
    }

    enum Courses {
        JAVA("Java", 600),
        DSA("DSA", 400),
        DATABASES("Databases", 550),
        SPRING("Spring", 550);

        private final int completionScore;
        private final String name;

        Courses(String name, int completionScore) {
            this.name = name;
            this.completionScore = completionScore;
        }

        String getName() {
            return this.name;
        }

        Course getCourse() {
            return new Course(this.name, this.completionScore);
        }
    }
}


