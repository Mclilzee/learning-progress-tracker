package project;

import java.util.*;
import java.util.stream.Collectors;

public class CoursesController {

    private final Course[] courses;

    public CoursesController() {
        courses = new Course[Courses.values().length];
        int i = 0;
        for (Courses course : Courses.values()) {
            courses[i] = course.getCourse();
            i++;
        }
    }

    private void printStudentScores(List<Student> students) {
        for (Student student : students) {
        }
    }

    public void addPointsToStudent(Student student, int[] scores) {

    }

    public Set<Course> getMostPopularCourses() {
        int mostPopularCourseCount = Arrays.stream(courses).mapToInt(course -> course.getStudents().size()).max().orElse(0);
        return Arrays.stream(courses).filter(course -> course.getStudents().size() == mostPopularCourseCount).collect(Collectors.toSet());
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


