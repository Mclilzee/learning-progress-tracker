package project.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.IncorrectInput;
import project.statistics.CoursesController.Courses;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static project.statistics.CoursesController.Courses;

class CoursesControllerTest {

    CoursesController controller;
    Student john = new Student("John", "Doe", "John@hotmail.com");
    Student gly = new Student("Gly", "Marksman", "gly@email.com");
    Student mark = new Student("Mark", "Zergberg", "mark@gmail.net");
    int javaCompletionScore = Courses.JAVA.getCompletionScore();
    int DSACompletionScore = Courses.DSA.getCompletionScore();
    int databasesCompletionScore = Courses.DATABASES.getCompletionScore();
    int springCompletionScore = Courses.SPRING.getCompletionScore();

    @BeforeEach
    void setUp() throws IncorrectInput {
        controller = new CoursesController();
        int[] johnPoints = new int[]{javaCompletionScore, DSACompletionScore - 1, databasesCompletionScore - 1, springCompletionScore};
        int[] glyPoints = new int[]{javaCompletionScore - 1, DSACompletionScore - 1, databasesCompletionScore - 1, springCompletionScore - 1};
        int[] markPoints = new int[]{javaCompletionScore, DSACompletionScore, databasesCompletionScore - 1, springCompletionScore};
        controller.addPointsToStudent(john, johnPoints);
        controller.addPointsToStudent(gly, glyPoints);
        controller.addPointsToStudent(mark, markPoints);
    }

    @Test
    void getStudentsCompletedCourses() {
        Map<Student, Set<Course>> expected = new LinkedHashMap<>();
        Set<Course> johnSet = new LinkedHashSet<>();
        johnSet.add(Courses.JAVA.getCourse());
        johnSet.add(Courses.SPRING.getCourse());

        Set<Course> markSet = new LinkedHashSet<>();
        markSet.add(Courses.JAVA.getCourse());
        markSet.add(Courses.DSA.getCourse());
        markSet.add(Courses.SPRING.getCourse());

        expected.put(john, johnSet);
        expected.put(mark, markSet);

        assertEquals(expected, controller.getStudentsCompletedCourses());
    }
}