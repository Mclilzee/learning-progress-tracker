package project;

import org.junit.jupiter.api.BeforeEach;
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
}