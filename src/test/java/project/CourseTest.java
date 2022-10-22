package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private static Course course;

    @BeforeEach
    void setUp() {
        course = new Course("Java");
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
}