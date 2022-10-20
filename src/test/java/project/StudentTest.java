package project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFirstName() {
        Student student = new Student("John", "Doe", "Johndoe@hotmail.com");
        assertEquals(student.getFirstName(), "John");
    }

    @Test
    void setFirstName() {
        Student student = new Student("John", "Doe", "Johndoe@hotmail.com");
        student.setFirstName("Dombos");
        assertEquals(student.getFirstName(), "Dombos");
    }

    @Test
    void getLastName() {
        Student student = new Student("John", "Doe", "Johndoe@hotmail.com");
        assertEquals(student.getLastName(), "Doe");
    }

    @Test
    void setLastName() {
        Student student = new Student("John", "Doe", "Johndoe@hotmail.com");
        student.setLastName("LastDoe");
        assertEquals(student.getLastName(), "LastDoe");
    }

    @Test
    void getEmail() {
        Student student = new Student("John", "Doe", "Johndoe@hotmail.com");
        assertEquals(student.getEmail(), "Johndoe@hotmail.com");
    }

    @Test
    void setEmail() {
        Student student = new Student("John", "Doe", "Johndoe@hotmail.com");
        student.setEmail("John@gmail.com");
        assertEquals(student.getEmail(), "John@gmail.com");
    }
}