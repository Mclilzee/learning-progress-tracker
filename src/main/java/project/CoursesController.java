package project;

import java.util.*;
import java.util.stream.Collectors;

public class CoursesController {

    private final Set<Course> courses;

    public CoursesController() {
        courses = new LinkedHashSet<>();
        Arrays.stream(Courses.values()).forEach(course -> courses.add(course.getCourse()));
    }

    private void printStudentsScores() {
        for (Course course : courses) {
            printStudentScoresForCourse(course);
        }

    }

    private void printStudentScoresForCourse(Course course) {
        int score = course.getStudentScore(student);
    }


    public Set<Course> getMostPopularCourses() {
        int mostPopularCourseCount = courses.stream().mapToInt(course -> course.getStudents().size()).max().orElse(0);
        return courses.stream().filter(course -> course.getStudents().size() == mostPopularCourseCount).collect(Collectors.toSet());
    }

    public Set<Course> getLeastPopularCourses() {
        int leastPopularCount = coursesEnrollment.values().stream().min(Integer::compareTo).orElse(0);

        return getFilteredCoursesSet(coursesEnrollment, leastPopularCount);
    }

    public Set<Course> getHighestActivityCourses() {
        int highestActivity = coursesActivity.values().stream().max(Integer::compareTo).orElse(0);

        return getFilteredCoursesSet(coursesActivity, highestActivity);
    }

    public Set<Course> getLowestActivityCourses() {
        int lowestActivity = courseActivity.values().stream().min(Integer::compareTo).orElse(0);

        return getFilteredCoursesSet(courseActivity, lowestActivity);
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

        public Course getCourse() {
            return new Course(this.name, this.completionScore);
        }
    }
}


