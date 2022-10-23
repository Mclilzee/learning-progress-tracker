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
        int mostPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).max().orElse(-1);
        Set<Course> enrolled = Arrays.stream(courses).filter(course -> course.getStudents().size() == mostPopularCount).collect(Collectors.toSet());
        return enrolled.size() != 4 ? enrolled : Set.of();
    }

    Set<Course> getLeastPopularCourses() {
        int leastPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).min().orElse(-1);
        Set<Course> enrolled = Arrays.stream(courses).filter(course -> course.getStudents().size() == leastPopularCount).collect(Collectors.toSet());
        return enrolled.size() != 4 ? enrolled : Set.of();
    }

    Set<Course> getHighestActivityCourses() {
        int highestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).max().orElse(-1);
        Set<Course> activity = Arrays.stream(courses).filter(course -> course.getCompletedTasks() == highestActivityCount).collect(Collectors.toSet());
        return activity.size() != 4 ? activity : Set.of();
    }

    Set<Course> getLowestActivityCourses() {
        int lowestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).min().orElse(-1);
        Set<Course> activity = Arrays.stream(courses).filter(course -> course.getCompletedTasks() == lowestActivityCount).collect(Collectors.toSet());
        return activity.size() != 4 ? activity : Set.of();
    }

    Set<Course> getEasiestCourses() {
        return null;
    }

    Set<Course> getHardestCourses() {
        return null;
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


