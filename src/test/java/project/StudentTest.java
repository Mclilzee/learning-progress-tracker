package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private static Student student;

    @BeforeEach
    void initInstance() {
        student = new Student("John", "Doe", "Johndoe@hotmail.com");
    }

    @Test
    void getFirstName() {
        assertEquals("John", student.getFirstName());
    }

    @Test
    void setFirstName() {
        student.setFirstName("Dombos");
        assertEquals("Dombos", student.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Doe", student.getLastName());
    }

    @Test
    void setLastName() {
        student.setLastName("LastDoe");
        assertEquals("LastDoe", student.getLastName());
    }

    @Test
    void getEmail() {
        assertEquals("Johndoe@hotmail.com", student.getEmail());
    }

    @Test
    void setEmail() {
        student.setEmail("John@gmail.com");
        assertEquals(student.getEmail(), "John@gmail.com");
    }

    @Test
    void objectsAreEqual() {
        Student student2 = new Student("John", "Doe", "Johndoe@hotmail.com");
        assertEquals(student, student2);
        assertEquals(student, student);
    }

    @Test
    @DisplayName("Objects are considered equal if they have the same email")
    void objectsAreEqualIfSameEmail() {
        Student student2 = new Student("Mark", "Zoigbreg", "Johndoe@hotmail.com");
        assertEquals(student, student2);
    }

    @Test
    @DisplayName("Objects are not equal if they have different email, not same class or null")
    void objectsAreNotEqual() {
        Student student2 = new Student("John", "Doe", "Jonathan@gmail.com");
        assertNotEquals(student, student2);
        assertNotEquals(student, null);
        assertNotEquals(student, new Object());
    }

    @Test
    @DisplayName("Objects have same hashcode if they have same email")
    void objectsHasSameHashcode() {
        Student student2 = new Student("Mark", "Zoigberg", "Johndoe@hotmail.com");
        assertEquals(student.hashCode(), student2.hashCode());
    }

    @Test
    @DisplayName("Student prints correct score format")
    void printScoreFormat() {
        int hash = student.hashCode();
        String expected = String.format("%d points: Java=0; DSA=0; Databases=0; Spring=0", hash);
        assertEquals(expected, student.toString());
    }
}