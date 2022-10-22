package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedHashSet;
import java.util.Set;

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
    @ValueSource(strings = {"John Doe markburg", "John doe mark@hotmal", "John Doe Mark @hotmail.com", "John Doe Mark johen@e@hotmail.com",
            "John Doe Mark johen@hotmail.com.dombos"})
    void addStudentWithIncorrectEmailFormat(String credentials) {
        assertFalse(studentController.addStudent(credentials));
    }

    @Test
    @DisplayName("Students with different emails can be added")
    void studentsAddedToStudentsList() {
        studentController.addStudent("John Doe johndoe@hotmail.com");
        studentController.addStudent("Mark Asm-ar Joe mark.asmar@hotmail.com");
        studentController.addStudent("John Doe jonathan@hotmail.com");
        assertEquals(studentController.getStudentsNumber(), 3);
    }

    @Test
    @DisplayName("Only students with unique email can be added")
    void studentsWithSameEmailAreNotAdded() {
        studentController.addStudent("John Doe johndoe@hotmail.com");
        studentController.addStudent("Mark zerg-berg johndoe@hotmail.com");
        studentController.addStudent("Omar khalil asmar johndoe@hotmail.com");
        assertEquals(studentController.getStudentsNumber(), 1);
    }

    @Test
    @DisplayName("Student with same email does not overwrite previous one")
    void sameEmailDoesNotOverWrite() {
        Student student = new Student("John", "doe", "john@hotmail.com");
        studentController.addStudent("John doe john@hotmail.com");
        studentController.addStudent("Mark zergberg john@hotmail.com");
        assertEquals(studentController.getStudentsNumber(), 1);

        String studentFirstName = studentController.getStudent(student.hashCode()).getFirstName();
        assertEquals(studentFirstName, "John");
        assertNotEquals(studentFirstName, "Mark");
    }

    @Test
    @DisplayName("Correct student information being added")
    void studentListHaveCorrectInformation() {
        studentController.addStudent("John doe john@hotmail.com");
        studentController.addStudent("Mark Zoigb-erg mark@gmail.com");
        Student john = new Student("John", "doe", "john@hotmail.com");
        Student mark = new Student("Mark", "Zoigb-erg", "mark@gmail.com");
        assertEquals(studentController.getStudent(john.hashCode()).getEmail(), john.getEmail());
        assertEquals(studentController.getStudent(mark.hashCode()).getEmail(), mark.getEmail());
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

        assertEquals(studentController.getStudentsNumber(), 0);
    }

    @Test
    @DisplayName("Return correct students id set")
    void getStudentsIDSet() {
        Student first = new Student("John", "Doe", "John@hotmail.com");
        Student second = new Student("Mark", "Zerg-berg asmar", "mark@gmail.com");
        studentController.addStudent("John Doe John@hotmail.com");
        studentController.addStudent("Mark Zerg-berg asmar mark@gmail.com");
        Set<Integer> set = new LinkedHashSet<>();
        set.add(first.hashCode());
        set.add(second.hashCode());
        assertEquals(set, studentController.getStudentsIDSet());
    }
}