package project.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.IncorrectInput;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {
    private static Statistics statistics;

    @BeforeEach
    void setup() {
        statistics = new Statistics();
    }

    @ParameterizedTest(name = "Student with right amount of credentials and format should return true")
    @ValueSource(strings = {"Jo-hn doe doe@hotmail.com", "Mark suzan james mark.suzan@hotmail.com", "Omar khalil ahm'ed khalil khalil@gmail.de"})
    void addStudentWithCorrectAmountOfCredentials(String credentials) {
        assertDoesNotThrow(() -> statistics.addStudent(credentials));
    }

    @ParameterizedTest(name = "Add student with less than 3 credentials should return false")
    @ValueSource(strings = {"Joen Doe", "John", ""})
    void addStudentWithLessCredentials(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> statistics.addStudent(credentials));
        assertEquals("Incorrect credentials", e.getMessage());
    }

    @ParameterizedTest(name = "Student with invalid first name should return false")
    @ValueSource(strings = {"J Doe doe@hotmail.com", "J23en.2as Doe john@gmail.de", "'John Doe john@hotmail.com", "John- Doe john@hotmail.com"})
    void addStudentWithInvalidFirstName(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> statistics.addStudent(credentials));
        assertEquals("Incorrect first name", e.getMessage());
    }

    @ParameterizedTest(name = "Student with invalid last name should return false")
    @ValueSource(strings = {"Joe D john@hotmail.com", "Joe D0domb21 das john@hotmail.com", "Joe -Doeh john@hotmail.com", "Joe Doeh de- john@hotmail.com"})
    void AddStudentWithInvalidLastName(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> statistics.addStudent(credentials));
        assertEquals("Incorrect last name", e.getMessage());
    }

    @ParameterizedTest(name = "Student with wrong email format should return false")
    @ValueSource(strings = {"John Doe markburg", "John doe mark@hotmal", "John Doe Mark @hotmail.com", "John Doe Mark johen@e@hotmail.com",
            "John Doe Mark johen@hotmail.com.dombos"})
    void addStudentWithIncorrectEmailFormat(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> statistics.addStudent(credentials));
        assertEquals("Incorrect email", e.getMessage());
    }

    @Test
    @DisplayName("Students with different emails can be added")
    void studentsAddedToStudentsList() throws IncorrectInput {
        statistics.addStudent("John Doe johndoe@hotmail.com");
        statistics.addStudent("Mark Asm-ar Joe mark.asmar@hotmail.com");
        statistics.addStudent("John Doe jonathan@hotmail.com");
        assertEquals(3, statistics.getTotalStudents());
    }

    @Test
    @DisplayName("Only students with unique email can be added")
    void sameEmailDoesNotOverWrite() throws IncorrectInput {
        statistics.addStudent("John doe john@hotmail.com");
        Exception e = assertThrows(IllegalArgumentException.class, () -> statistics.addStudent("Mark zergberg john@hotmail.com"));
        assertEquals("This email is already taken", e.getMessage());
        assertEquals(1, statistics.getTotalStudents());
        assertEquals("John", statistics.getStudent(getEmailHashString("john@hotmail.com")).getFirstName());
    }

    @Test
    @DisplayName("Correct student information being added")
    void studentListHaveCorrectInformation() throws IncorrectInput {
        statistics.addStudent("John doe john@hotmail.com");
        statistics.addStudent("Mark Zoigb-erg mark@gmail.com");
        Student john = new Student("John", "doe", "john@hotmail.com");
        Student mark = new Student("Mark", "Zoigb-erg", "mark@gmail.com");
        assertEquals(statistics.getStudent(String.valueOf(john.hashCode())).getEmail(), john.getEmail());
        assertEquals(statistics.getStudent(String.valueOf(mark.hashCode())).getEmail(), mark.getEmail());
    }

    @Test
    @DisplayName("Return correct students id set")
    void getStudentsIDSet() throws IncorrectInput {
        statistics.addStudent("John Doe John@hotmail.com");
        statistics.addStudent("Mark Zerg-berg asmar mark@gmail.com");
        Set<Integer> set = new LinkedHashSet<>();
        set.add(getEmailHash("John@hotmail.com"));
        set.add(getEmailHash("mark@gmail.com"));
        assertEquals(set, statistics.getStudentIDSet());
    }

    private int getEmailHash(String email) {
        return Math.abs(Objects.hash(email));
    }

    private String getEmailHashString(String email) {
        return String.valueOf(getEmailHash(email));
    }
}