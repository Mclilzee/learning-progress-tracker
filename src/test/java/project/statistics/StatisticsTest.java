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

import project.statistics.CoursesController.Courses;

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
        Exception e = assertThrows(IncorrectInput.class, () -> statistics.addStudent(credentials));
        assertEquals(IncorrectInput.incorrectCredentials().getMessage(), e.getMessage());
    }

    @ParameterizedTest(name = "Student with invalid first name should return false")
    @ValueSource(strings = {"J Doe doe@hotmail.com", "J23en.2as Doe john@gmail.de", "'John Doe john@hotmail.com", "John- Doe john@hotmail.com"})
    void addStudentWithInvalidFirstName(String credentials) {
        Exception e = assertThrows(IncorrectInput.class, () -> statistics.addStudent(credentials));
        assertEquals(IncorrectInput.incorrectFirstName().getMessage(), e.getMessage());
    }

    @ParameterizedTest(name = "Student with invalid last name should return false")
    @ValueSource(strings = {"Joe D john@hotmail.com", "Joe D0domb21 das john@hotmail.com", "Joe -Doeh john@hotmail.com", "Joe Doeh de- john@hotmail.com"})
    void AddStudentWithInvalidLastName(String credentials) {
        Exception e = assertThrows(IncorrectInput.class, () -> statistics.addStudent(credentials));
        assertEquals(IncorrectInput.incorrectLastName().getMessage(), e.getMessage());
    }

    @ParameterizedTest(name = "Student with wrong email format should return false")
    @ValueSource(strings = {"John Doe markburg", "John doe mark@hotmal", "John Doe Mark @hotmail.com", "John Doe Mark johen@e@hotmail.com",
            "John Doe Mark johen@hotmail.com.dombos"})
    void addStudentWithIncorrectEmailFormat(String credentials) {
        Exception e = assertThrows(IncorrectInput.class, () -> statistics.addStudent(credentials));
        assertEquals(IncorrectInput.incorrectEmail().getMessage(), e.getMessage());
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
        Exception e = assertThrows(IncorrectInput.class, () -> statistics.addStudent("Mark zergberg john@hotmail.com"));
        assertEquals(IncorrectInput.emailTaken().getMessage(), e.getMessage());
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

    @Test
    @DisplayName("Show correct most popular courses")
    void mostPopularCourse() throws IncorrectInput {
        String expected = Courses.JAVA.getName() + ", " + Courses.SPRING.getName();
        fillStudents();

        assertEquals(expected, statistics.getMostPopularCourses());
    }

    @Test
    @DisplayName("Show most popular courses if all courses has same enrollment")
    void mostPopularCoursesSameEnrollment() {
        String expected = String.format("%s, %s, %s, %s", Courses.JAVA.getName(),
                Courses.DSA.getName(), Courses.DATABASES.getName(), Courses.SPRING.getName());
    }

    @Test
    @DisplayName("Show correct least popular courses")
    void leastPopularCourses() throws IncorrectInput {
        String expected = Courses.DATABASES.getName();
        fillStudents();

        assertEquals(expected, statistics.getLeastPopularCourses());
    }

    @Test
    @DisplayName("Show correct highest activity set")
    void highestActivityCourses() throws IncorrectInput {
        String expected = Courses.JAVA.getName() + ", " + Courses.DSA.getName();
        fillStudents();

        assertEquals(expected, statistics.getHighestActivityCourses());
    }

    @Test
    @DisplayName("Show correct lowest activity set")
    void lowestActivityCourses() throws IncorrectInput {
        String expected = Courses.DATABASES.getName();
        fillStudents();

        assertEquals(expected, statistics.getLowestActivityCourses());
    }

    @Test
    @DisplayName("Show correct easiest courses")
    void easiestCourses() throws IncorrectInput {
        String expected = Courses.DSA.getName();
        fillStudents();

        assertEquals(expected, statistics.getEasiestCourses());
    }

    @Test
    @DisplayName("Show correct hardest courses")
    void hardestCourses() throws IncorrectInput {
        String expected = Courses.SPRING.getName();
        fillStudents();

        assertEquals(expected, statistics.getHardestCourses());
    }

    @Test
    @DisplayName("Return empty set if no students enrolled")
    void noEnrollment() {
        assertEquals("n/a", statistics.getMostPopularCourses());
        assertEquals("n/a", statistics.getLeastPopularCourses());
        assertEquals("n/a", statistics.getHighestActivityCourses());
        assertEquals("n/a", statistics.getLowestActivityCourses());
        assertEquals("n/a", statistics.getEasiestCourses());
        assertEquals("n/a", statistics.getHardestCourses());
    }

    @Test
    @DisplayName("Throw error if no course found for the name")
    void noCourseFound() {
        Exception e = assertThrows(IncorrectInput.class, () -> statistics.getCourseStatistics("doblos"));
        assertEquals(IncorrectInput.incorrectCourseName().getMessage(), e.getMessage());
    }

    @Test
    @DisplayName("Does not throw error if students were found and course name is correct")
    void correctClassNameAndStudentsEnrolled() throws IncorrectInput {
        fillStudents();
        assertDoesNotThrow(() -> statistics.getCourseStatistics("java"));
    }

    private void fillStudents() throws IncorrectInput {
        statistics.addStudent("john doe john@hotmail.com");
        statistics.addStudent("Khalil Markman khalil@gmail.com");
        statistics.addStudent("Mark zergberg mark@hotmail.com");
        statistics.addStudent("Glycen Glylo gly@gmail.com");

        statistics.addPointsToStudent(getEmailHashString("john@hotmail.com") + " 0 5 0 20");
        statistics.addPointsToStudent(getEmailHashString("john@hotmail.com") + " 0 50 0 0");
        statistics.addPointsToStudent(getEmailHashString("john@hotmail.com") + " 10 5 0 0");

        statistics.addPointsToStudent(getEmailHashString("khalil@gmail.com") + " 5 0 6 0");
        statistics.addPointsToStudent(getEmailHashString("khalil@gmail.com") + " 8 0 4 0");
        statistics.addPointsToStudent(getEmailHashString("khalil@gmail.com") + " 7 0 5 8");

        statistics.addPointsToStudent(getEmailHashString("mark@hotmail.com") + " 5 5 0 10");
        statistics.addPointsToStudent(getEmailHashString("mark@hotmail.com") + " 0 5 0 0");
        statistics.addPointsToStudent(getEmailHashString("mark@hotmail.com") + " 0 5 0 0");

        statistics.addPointsToStudent(getEmailHashString("gly@gmail.com") + " 0 0 0 7");
        statistics.addPointsToStudent(getEmailHashString("gly@gmail.com") + " 20 0 0 6");
        statistics.addPointsToStudent(getEmailHashString("gly@gmail.com") + " 0 0 0 0");
    }

    private void fillSamePointsStudents() throws IncorrectInput {
        statistics.addStudent("john doe john@hotmail.com");
        statistics.addStudent("Khalil Marksman khalil@gmail.com");

        statistics.addPointsToStudent(getEmailHashString("john@hotmail.com" + " 1 1 1 1"));
        statistics.addPointsToStudent(getEmailHashString("khalil@gmail.com" + " 1 1 1 1"));
    }

    private int getEmailHash(String email) {
        return Math.abs(Objects.hash(email));
    }

    private String getEmailHashString(String email) {
        return String.valueOf(getEmailHash(email));
    }
}