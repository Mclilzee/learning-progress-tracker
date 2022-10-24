package project.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private static Course course;
    private static Student john;
    private static Student mark;
    private static Student gly;

    @BeforeEach
    void setUp() {
        course = new Course("Java", 300);
        john = new Student("john", "mark", "doe@hotmail.com");
        mark = new Student("mark", "zergberg", "mark@gmail.com");
        gly = new Student("gly", "glyman", "gly@yahoo.de");
    }

    private void fillCourseWithStudents() {
        course.addScore(john, 5);
        course.addScore(john, 5);

        course.addScore(mark, 5);
        course.addScore(mark, 0);

        course.addScore(gly, 8);
        course.addScore(gly, -5);
    }

    @Test
    void getName() {
        assertEquals("Java", course.getName());
    }

    @Test
    @DisplayName("Completion score show correctly")
    void getCompletionScore() {
        assertEquals(300, course.getCompletionScore());
    }

    @Test
    @DisplayName("Completed tasks show correctly")
    void getCompletedTasks() {
        fillCourseWithStudents();
        assertEquals(4, course.getCompletedTasks());
    }

    @Test
    @DisplayName("Add correct number to student")
    void showCorrectScoreNumber() {
        fillCourseWithStudents();
        assertEquals(10, course.getStudentScore(john));
        assertEquals(5, course.getStudentScore(mark));
        assertEquals(8, course.getStudentScore(gly));
    }

    @Test
    @DisplayName("Show correct enrolled students")
    void showCorrectEnrolledStudents() {
        Set<Student> expected = Set.of(john, mark);
        course.addScore(john, 5);
        course.addScore(mark, 10);
        course.addScore(gly, 0);

        assertEquals(expected, course.getStudents());
    }

    @Test
    @DisplayName("Show correct average score")
    void averageScore() {
        fillCourseWithStudents();
        assertEquals(7.66, course.getAverageScores(), 0.01);
    }

    @Test
    @DisplayName("return correct Student statistic data")
    void studentStatistics() {
        fillCourseWithStudents();
        String[] statistics = new String[]{String.format("%d\t\t%d\t\t%.1f%%\n", john.hashCode(), 10, 3.300),
                String.format("%d\t\t%d\t\t%.1f%%\n", mark.hashCode(), 5, 1.700),
                String.format("%d\t\t%d\t\t%.1f%%\n", gly.hashCode(), 8, 2.700)};
        assertArrayEquals(statistics, course.getCourseStatistics());
    }

    @Test
    @DisplayName("Courses are equals if name is the same")
    void coursesAreEqual() {
        Course otherCourse = new Course("Java", 10000);
        assertEquals(otherCourse, course);
    }

    @Test
    @DisplayName("Courses are not equals if name is not the same")
    void coursesAreNotEqual() {
        Course otherCourse = new Course("DSA", 300);
        assertNotEquals(otherCourse, course);
    }

    @Test
    @DisplayName("Courses are not equal if other course is null")
    void coursesAreNotEqualIfNull() {
        assertNotEquals(course, null);
    }

    @Test
    @DisplayName("Courses has same hashcode")
    void coursesHashAreEqual() {
        Course otherCourse = new Course("Java", 2000);
        assertEquals(course.hashCode(), otherCourse.hashCode());
    }
}