package project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentsControllerTest {

    @Test
    @DisplayName("Student with right amount of credentials should return true")
    void addStudent() {
        StudentsController controller = new StudentsController();
        assertTrue(controller.addStudent("John doe doe@hotmail.com"));
    }

    @Test
    @DisplayName("Student with invalid first name should return false")
    void addStudentWithInvalidFirsttName() {
        StudentsController controller = new StudentsController();
        assertFalse(controller.addStudent("J. Doe doe@hotmail.com"));
        assertFalse(controller.addStudent("J23en.2as Doe john@hotmail.com"));
    }

    @Test
    @DisplayName("Student with invalid last name should return false")
    void AddStudentWithInvalidLastName() {
        StudentsController controller = new StudentsController();
        assertFalse(controller.addStudent("Joe D. john@hotmail.com"));
        assertFalse(controller.addStudent("Joe D0domb21 john@hotmail.com"));
    }

    @Test
    @DisplayName("Add student with less than 3 credentials should return false")
    void addStudentWithLessCredentials() {
        StudentsController controller = new StudentsController();
        assertFalse(controller.addStudent("Joen Doe"));
        assertFalse(controller.addStudent("John"));
        assertFalse(controller.addStudent(""));
    }

    @Test
    @DisplayName("Student with wrong email format should return false")
    void addStudentWithIncorrectEmailFormat() {
        StudentsController controller = new StudentsController();
        assertFalse(controller.addStudent("John Doe markburg"));
        assertFalse(controller.addStudent("John doe mark@hotmal"));
        assertFalse(controller.addStudent("John Doe Mark @hotmail.com"));
    }

    @Test
    @DisplayName("Students has been added to students list")
    void studentsAddedToStudentsList() {
        StudentsController controller = new StudentsController();
        controller.addStudent("John Doe johndoe@hotmail.com");
        controller.addStudent("Mark Asmar Joe mark.asmar@hotmail.com");
        assertEquals(controller.getStudents().size(), 2);
    }

    @Test
    @DisplayName("Student has not been added to students list")
    void studentsNotAddedToStudentsList() {
        StudentsController controller = new StudentsController();
        controller.addStudent("John doe");
        controller.addStudent("John");
        controller.addStudent("");
        controller.addStudent("J. Doe doe@hotmail.com");
        controller.addStudent("J234.sdf Doe doe@hotmail.com");
        controller.addStudent("John D. doe@hotmail.com");
        controller.addStudent("John D23sdf2.,s doe@hotmail.com");
        controller.addStudent("John Doe doerogan");
        controller.addStudent("John doe mark@hotmal");
        controller.addStudent("John Doe Mark @hotmail.com");
        assertEquals(controller.getStudents().size(), 0);
    }
}