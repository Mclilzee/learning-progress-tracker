package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private static Course course;

    @BeforeEach
    void setUp() {
        course = new Course("Java", 300);
    }

    @Test
    void getName() {
        assertEquals("Java", course.getName());
    }

    @Test
    void getScore() {
        assertEquals(0, course.getScore());
    }

    @Test
    @DisplayName("Does not add negative numbers")
    void addScore() {
        course.addScore(5);
        assertEquals(5, course.getScore());
        course.addScore(-1);
        assertEquals(5, course.getScore());
    }

    @Test
    @DisplayName("Completion score show correctly")
    void getCompletionScore() {
        assertEquals(300, course.getCompletionScore());
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