package project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentsControllerTest {

    @Test
    @DisplayName("Add student with right amount of credentials")
    void addStudent() {
        StudentsController controller = new StudentsController();
        assertTrue(controller.addStudent("John doe doe@hotmail.com"));
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
    @DisplayName("Add student with more than 3 credentials should return false")
    void addStudentWithMoreCredentials() {
        StudentsController controller = new StudentsController();
        assertFalse(controller.addStudent("John Doe Mark mark@gmail.com"));
        assertFalse(controller.addStudent("John Doe Mark Man Michael michael@hotmail.com"));
    }

    @Test
    @DisplayName("Students has been added to students list")
    void studentsAddedToStudentsList() {
        StudentsController controller = new StudentsController();
        controller.addStudent("John Doe johndoe@hotmail.com");
        controller.addStudent("Mark Asmar mark.asmar@hotmail.com");
        assertEquals(controller.getStudents().size(), 2);
    }

    @Test
    @DisplayName("Student has not been added to students list")
    void studentsNotAddedToStudentsList() {
        StudentsController controller = new StudentsController();
        controller.addStudent("John doe");
        controller.addStudent("John");
        controller.addStudent("");
        assertEquals(controller.getStudents().size(), 0);
    }

}