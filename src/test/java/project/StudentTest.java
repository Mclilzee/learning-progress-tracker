package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private static Student student;

    @BeforeEach
    void initInstance() {
        student = new Student("John", "Doe", "Johndoe@hotmail.com");
    }

    @Test
    void getFirstName() {
        assertEquals(student.getFirstName(), "John");
    }

    @Test
    void setFirstName() {
        student.setFirstName("Dombos");
        assertEquals(student.getFirstName(), "Dombos");
    }

    @Test
    void getLastName() {
        assertEquals(student.getLastName(), "Doe");
    }

    @Test
    void setLastName() {
        student.setLastName("LastDoe");
        assertEquals(student.getLastName(), "LastDoe");
    }

    @Test
    void getEmail() {
        assertEquals(student.getEmail(), "Johndoe@hotmail.com");
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
}