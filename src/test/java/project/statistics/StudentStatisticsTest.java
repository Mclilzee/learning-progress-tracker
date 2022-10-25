package project.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentStatisticsTest {

    StudentStatistics firstStudent;
    StudentStatistics secondStudent;
    StudentStatistics thirdStudent;
    StudentStatistics fourthStudent;

    @BeforeEach
    void setUp() {
        firstStudent = new StudentStatistics(10, 40, 3.500);
        secondStudent = new StudentStatistics(11, 60, 8.0040);
        thirdStudent = new StudentStatistics(12, 40, 5.7001);
        fourthStudent = new StudentStatistics(12, 40, 6.82);
    }

    @Test
    void testToString() {
        assertEquals("10\t\t40\t\t3.5%", firstStudent.toString());
        assertEquals("11\t\t60\t\t8.0%", secondStudent.toString());
        assertEquals("12\t\t40\t\t5.7%", thirdStudent.toString());
        assertEquals("12\t\t40\t\t6.8%", fourthStudent.toString());
    }

    @Test
    void compareTo() {
        assertTrue(firstStudent.compareTo(secondStudent) > 0);
        assertTrue(firstStudent.compareTo(thirdStudent) < 0);
        assertTrue(secondStudent.compareTo(thirdStudent) < 0);
        assertEquals(0, thirdStudent.compareTo(fourthStudent));
    }

    @Test
    @DisplayName("Student Statistics hash is same as student id")
    void hashTest() {
        assertEquals(10, firstStudent.hashCode());
    }

    @Test
    @DisplayName("Students statistics are equal if they have same id")
    void sameIdEquals() {
        assertEquals(thirdStudent, fourthStudent);
    }

    @Test
    @DisplayName("Students not equal if their id is different")
    void differentIdNotEqual() {
        assertNotEquals(firstStudent, secondStudent);
    }
}