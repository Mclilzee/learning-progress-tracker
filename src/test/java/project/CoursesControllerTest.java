package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CoursesControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void printStudentPoints() {
    }

    @Test
    void addPointsToStudent() {
    }

    @Test
    void getMostPopularCourses() {
    }

    @Test
    void getLeastPopularCourses() {
    }

    @Test
    void getHighestActivityCourses() {
    }

    @Test
    void getLowestActivityCourses() {
    }

//    @Test
//    @DisplayName("Show correct most popular courses")
//    void mostPopularCourse() {
//        Set<Course> mostPopular = Set.of(CoursesController.Courses.JAVA.getCourse(), CoursesController.Courses.SPRING.getCourse());
//        fillStudents();
//
//        assertEquals(mostPopular, studentController.getMostPopularCourses());
//    }
//
//    @Test
//    @DisplayName("Show correct least popular courses")
//    void leastPopularCourses() {
//        Set<Course> leastPopular = Set.of(CoursesController.Courses.DATABASES.getCourse());
//        fillStudents();
//
//        assertEquals(leastPopular, studentController.getLeastPopularCourses());
//    }
//
//    @Test
//    @DisplayName("Show correct highest activity set")
//    void highestActivityCourses() {
//        Set<Course> highestActivity = Set.of(CoursesController.Courses.JAVA.getCourse(), CoursesController.Courses.DSA.getCourse());
//        fillStudents();
//
//        assertEquals(highestActivity, studentController.getHighestActivityCourses());
//    }
//
//    @Test
//    @DisplayName("Show correct lowest activity set")
//    void lowestActivityCourses() {
//        Set<Course> lowestActivity = Set.of(CoursesController.Courses.DATABASES.getCourse());
//        fillStudents();
//
//        assertEquals(lowestActivity, studentController.getLowestActivityCourses());
//    }
//
//    private void fillStudents() {
//        studentController.addStudent("john doe john@hotmail.com");
//        studentController.addStudent("Khalil Markman khalil@gmail.com");
//        studentController.addStudent("Mark zergberg mark@hotmail.com");
//        studentController.addStudent("Glycen Glylo gly@gmail.com");
//
//        Student john = studentController.getStudent(getEmailHash("john@hotmail.com"));
//        john.addScores(new int[]{0, 5, 0, 20});
//        john.addScores(new int[]{0, 50, 0, 0});
//        john.addScores(new int[]{10, 5, 0, 0});
//
//        Student khalil = studentController.getStudent(getEmailHash("khalil@gmail.com"));
//        khalil.addScores(new int[]{5, 0, 6, 0});
//        khalil.addScores(new int[]{8, 0, 4, 0});
//        khalil.addScores(new int[]{7, 0, 5, 8});
//
//        Student mark = studentController.getStudent(getEmailHash("mark@hotmail.com"));
//        mark.addScores(new int[]{5, 5, 0, 10});
//        mark.addScores(new int[]{0, 5, 0, 0});
//        mark.addScores(new int[]{0, 5, 0, 0});
//
//        Student gly = studentController.getStudent(getEmailHash("gly@gmail.com"));
//        gly.addScores(new int[]{0, 0, 0, 7});
//        gly.addScores(new int[]{20, 0, 0, 6});
//        gly.addScores(new int[]{0, 0, 0, 0});
//    }
}