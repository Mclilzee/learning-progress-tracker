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
        assertDoesNotThrow(() -> studentController.addStudent(credentials));
    }

    @ParameterizedTest(name = "Add student with less than 3 credentials should return false")
    @ValueSource(strings = {"Joen Doe", "John", ""})
    void addStudentWithLessCredentials(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> studentController.addStudent(credentials));
        assertEquals("Incorrect credentials", e.getMessage());
    }

    @ParameterizedTest(name = "Student with invalid first name should return false")
    @ValueSource(strings = {"J Doe doe@hotmail.com", "J23en.2as Doe john@gmail.de", "'John Doe john@hotmail.com", "John- Doe john@hotmail.com"})
    void addStudentWithInvalidFirstName(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> studentController.addStudent(credentials));
        assertEquals("Incorrect first name", e.getMessage());
    }

    @ParameterizedTest(name = "Student with invalid last name should return false")
    @ValueSource(strings = {"Joe D john@hotmail.com", "Joe D0domb21 das john@hotmail.com", "Joe -Doeh john@hotmail.com", "Joe Doeh de- john@hotmail.com"})
    void AddStudentWithInvalidLastName(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> studentController.addStudent(credentials));
        assertEquals("Incorrect last name", e.getMessage());
    }

    @ParameterizedTest(name = "Student with wrong email format should return false")
    @ValueSource(strings = {"John Doe markburg", "John doe mark@hotmal", "John Doe Mark @hotmail.com", "John Doe Mark johen@e@hotmail.com",
            "John Doe Mark johen@hotmail.com.dombos"})
    void addStudentWithIncorrectEmailFormat(String credentials) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> studentController.addStudent(credentials));
        assertEquals("Incorrect email", e.getMessage());
    }

    @Test
    @DisplayName("Students with different emails can be added")
    void studentsAddedToStudentsList() {
        studentController.addStudent("John Doe johndoe@hotmail.com");
        studentController.addStudent("Mark Asm-ar Joe mark.asmar@hotmail.com");
        studentController.addStudent("John Doe jonathan@hotmail.com");
        assertEquals(3, studentController.getStudentsNumber());
    }

    @Test
    @DisplayName("Only students with unique email can be added")
    void sameEmailDoesNotOverWrite() {
        studentController.addStudent("John doe john@hotmail.com");
        Exception e = assertThrows(IllegalArgumentException.class, () -> studentController.addStudent("Mark zergberg john@hotmail.com"));
        assertEquals("This email is already taken", e.getMessage());
        assertEquals(1, studentController.getStudentsNumber());
        assertEquals("John", studentController.getStudent(getEmailHash("john@hotmail.com")).getFirstName());
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
    @DisplayName("Return correct students id set")
    void getStudentsIDSet() {
        studentController.addStudent("John Doe John@hotmail.com");
        studentController.addStudent("Mark Zerg-berg asmar mark@gmail.com");
        Set<Integer> set = new LinkedHashSet<>();
        set.add(getEmailHash("John@hotmail.com"));
        set.add(getEmailHash("mark@gmail.com"));
        assertEquals(set, studentController.getStudentsIDSet());
    }

    @Test
    @DisplayName("Show correct most popular courses")
    void mostPopularCourse() {
        Set<Course> mostPopular = Set.of(Courses.JAVA.getCourse(), Courses.SPRING.getCourse());
        fillStudents();

        assertEquals(mostPopular, studentController.getMostPopularCourses());
    }

    @Test
    @DisplayName("Show correct least popular courses")
    void leastPopularCourses() {
        Set<Course> leastPopular = Set.of(Courses.DATABASES.getCourse());
        fillStudents();

        assertEquals(leastPopular, studentController.getLeastPopularCourses());
    }

    @Test
    @DisplayName("Show correct highest activity set")
    void highestActivityCourses() {
        Set<Course> highestActivity = Set.of(Courses.JAVA.getCourse(), Courses.DSA.getCourse());
        fillStudents();

        assertEquals(highestActivity, studentController.getHighestActivityCourses());
    }

    @Test
    @DisplayName("Show correct lowest activity set")
    void lowestActivityCourses() {
        Set<Course> lowestActivity = Set.of(Courses.DATABASES.getCourse());
        fillStudents();

        assertEquals(lowestActivity, studentController.getLowestActivityCourses());
    }

    private void fillStudents() {
        studentController.addStudent("john doe john@hotmail.com");
        studentController.addStudent("Khalil Markman khalil@gmail.com");
        studentController.addStudent("Mark zergberg mark@hotmail.com");
        studentController.addStudent("Glycen Glylo gly@gmail.com");

        Student john = studentController.getStudent(getEmailHash("john@hotmail.com"));
        john.addScores(new int[]{0, 5, 0, 20});
        john.addScores(new int[]{0, 50, 0, 0});
        john.addScores(new int[]{10, 5, 0, 0});

        Student khalil = studentController.getStudent(getEmailHash("khalil@gmail.com"));
        khalil.addScores(new int[]{5, 0, 6, 0});
        khalil.addScores(new int[]{8, 0, 4, 0});
        khalil.addScores(new int[]{7, 0, 5, 8});

        Student mark = studentController.getStudent(getEmailHash("mark@hotmail.com"));
        mark.addScores(new int[]{5, 5, 0, 10});
        mark.addScores(new int[]{0, 5, 0, 0});
        mark.addScores(new int[]{0, 5, 0, 0});

        Student gly = studentController.getStudent(getEmailHash("gly@gmail.com"));
        gly.addScores(new int[]{0, 0, 0, 7});
        gly.addScores(new int[]{20, 0, 0, 6});
        gly.addScores(new int[]{0, 0, 0, 0});
    }

    private int getEmailHash(String email) {
        return new Student("John", "Doe", email).hashCode();
    }
}