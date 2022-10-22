package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StudentsControllerTest {

    private static StudentsController studentController;

    @BeforeEach
    void initInstance() {
        studentController = new StudentsController();
    }

    @ParameterizedTest(name = "Student with right amount of credentials and format should return true")
    @ValueSource(strings = {"Jo-hn doe doe@hotmail.com", "Mark suzan james mark.suzan@hotmail.com", "Omar khalil ahm'ed khalil khalil@gmail.de"})
    void addStudentWithCorrectAmountOfCredentials(String credentials) {
        assertTrue(studentController.addStudent(credentials));
    }

    @ParameterizedTest(name = "Add student with less than 3 credentials should return false")
    @ValueSource(strings = {"Joen Doe", "John", ""})
    void addStudentWithLessCredentials(String credentials) {
        assertFalse(studentController.addStudent(credentials));
    }

    @ParameterizedTest(name = "Student with invalid first name should return false")
    @ValueSource(strings = {"J Doe doe@hotmail.com", "J23en.2as Doe john@gmail.de", "'John Doe john@hotmail.com", "John- Doe john@hotmail.com"})
    void addStudentWithInvalidFirstName(String credentials) {
        assertFalse(studentController.addStudent(credentials));
    }

    @ParameterizedTest(name = "Student with invalid last name should return false")
    @ValueSource(strings = {"Joe D john@hotmail.com", "Joe D0domb21 das john@hotmail.com", "Joe -Doeh john@hotmail.com", "Joe Doeh de- john@hotmail.com"})
    void AddStudentWithInvalidLastName(String credentials) {
        assertFalse(studentController.addStudent(credentials));
    }

    @ParameterizedTest(name = "Student with wrong email format should return false")
    @ValueSource(strings = {"John Doe markburg", "John doe mark@hotmal", "John Doe Mark @hotmail.com",
            "John Doe Mark -johen@hotmail.com", "John Doe Mark johen@hotmail.com`", "John Doe Mark johen@e@hotmail.com",
            "John Doe Mark johen@hotmail.com.dombos"})
    void addStudentWithIncorrectEmailFormat(String credentials) {
        assertFalse(studentController.addStudent(credentials));
    }

    @Test
    @DisplayName("Students has been added to students list")
    void studentsAddedToStudentsList() {
        studentController.addStudent("John Doe johndoe@hotmail.com");
        studentController.addStudent("Mark Asm-ar Joe mark.asmar@hotmail.com");
        assertEquals(studentController.getStudents().size(), 2);
    }

    @Test
    @DisplayName("Correct student information being added")
    void studentListHaveCorrectInformation() {
    }

    @Test
    @DisplayName("Student has not been added to students list")
    void studentsNotAddedToStudentsList() {
        studentController.addStudent("John doe");
        studentController.addStudent("John");
        studentController.addStudent("");
        studentController.addStudent("J Doe doe@hotmail.com");
        studentController.addStudent("J234.sdf Doe doe@hotmail.com");
        studentController.addStudent("John D doe@hotmail.com");
        studentController.addStudent("John D23sdf2.,s doe@hotmail.com");
        studentController.addStudent("John Doe doerogan");
        studentController.addStudent("John doe mark@hotmal");
        studentController.addStudent("John Doe Mark @hotmail.com");
        studentController.addStudent("'John Doe john@hotmail.com");
        studentController.addStudent("John- Doe john@hotmail.com");
        studentController.addStudent("Joe -Doeh john@hotmail.com");
//        studentController.addStudent("Joe Doeh -john@hotmail.com");
        studentController.addStudent("Joe Doeh- john@hotmail.com");

        assertEquals(studentController.getStudents().size(), 0);
    }
}