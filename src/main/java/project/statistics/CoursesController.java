package project.statistics;

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

     Set<Course> getMostPopularCourses() {
        int mostPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).max().orElse(0);
        return Arrays.stream(courses).filter(course -> course.getStudents().size() == mostPopularCount).collect(Collectors.toSet());
    }

     Set<Course> getLeastPopularCourses() {
        int leastPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).min().orElse(0);
        return Arrays.stream(courses).filter(course -> course.getStudents().size() == leastPopularCount).collect(Collectors.toSet());
    }

     Set<Course> getHighestActivityCourses() {
        int highestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).max().orElse(0);
        return Arrays.stream(courses).filter(course -> course.getStudents().size() == highestActivityCount).collect(Collectors.toSet());
    }

     Set<Course> getLowestActivityCourses() {
        int lowestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).min().orElse(0);
        return Arrays.stream(courses).filter(course -> course.getStudents().size() == lowestActivityCount).collect(Collectors.toSet());
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

         Course getCourse() {
            return new Course(this.name, this.completionScore);
        }
    }
}

