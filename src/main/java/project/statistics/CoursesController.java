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

    void addPointsToStudent(Student student, int[] scores) throws IncorrectInput {
        for (int i = 0; i < courses.length; i++) {
            courses[i].addScore(student, scores[i]);
        }
    }

    String[] getCourseStatistics(String courseName) throws IncorrectInput {
        for (Course course : courses) {
            if (courseName.equalsIgnoreCase(course.getName())) {
                return formatCourseStatistics(course);
            }
        }

        throw IncorrectInput.incorrectCourseName();
    }

    String[] formatCourseStatistics(Course course) {
        Set<StudentStatistics> statistics = course.getCourseStatistics();

        String[] formattedStatistics = new String[statistics.size() + 2];
        formattedStatistics[0] = course.getName();
        formattedStatistics[1] = "id\t\tpoints\t\tcompleted";

        int i = 2;
        for (StudentStatistics studentStatistics : statistics) {
            formattedStatistics[i] = studentStatistics.toString();
            i++;
        }

        return formattedStatistics;
    }

    Set<Course> getMostPopularCourses() {
        int mostPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).max().orElse(-1);
        if (mostPopularCount == 0) {
            return Set.of();
        }

        return Arrays.stream(courses).filter(course -> course.getStudents().size() == mostPopularCount).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    Set<Course> getLeastPopularCourses() {
        Set<Course> mostPopular = getMostPopularCourses();
        if (mostPopular.isEmpty()) {
            return Set.of();
        }

        int leastPopularCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).min().orElse(-1);
        return Arrays.stream(courses).filter(course -> course.getStudents().size() == leastPopularCount && !mostPopular.contains(course))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    Set<Course> getHighestActivityCourses() {
        int highestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).max().orElse(-1);
        if (highestActivityCount == 0) {
            return Set.of();
        }

        return Arrays.stream(courses).filter(course -> course.getCompletedTasks() == highestActivityCount)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    Set<Course> getLowestActivityCourses() {
        Set<Course> highestActivity = getHighestActivityCourses();
        if (highestActivity.isEmpty()) {
            return Set.of();
        }

        int lowestActivityCount = Arrays.stream(courses).mapToInt(Course::getCompletedTasks).min().orElse(-1);
        return Arrays.stream(courses).filter(course -> course.getCompletedTasks() == lowestActivityCount && !highestActivity.contains(course))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    Set<Course> getEasiestCourses() {
        double highestAverage = Arrays.stream(courses).mapToDouble(Course::getAverageScores).max().orElse(-1);
        if (highestAverage == 0) {
            return Set.of();
        }
        return Arrays.stream(courses).filter(course -> course.getAverageScores() == highestAverage)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    Set<Course> getHardestCourses() {
        Set<Course> easiestCourses = getEasiestCourses();
        if (easiestCourses.isEmpty()) {
            return Set.of();
        }

        double lowestAverage = Arrays.stream(courses).mapToDouble(Course::getAverageScores).min().orElse(-1);
        return Arrays.stream(courses).filter(course -> course.getAverageScores() == lowestAverage && !easiestCourses.contains(course))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    enum Courses {
        JAVA("Java", 600),
        DSA("DSA", 400),
        DATABASES("Databases", 480),
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