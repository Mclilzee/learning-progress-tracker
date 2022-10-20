package project;

import java.util.ArrayList;
import java.util.List;

public class StudentsController {

    private List<Student> students;

    public StudentsController() {
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean addStudent(String input) {
        String[] credentials = input.split(" ");
        if (validateStudentCredentials(credentials)) {
            this.students.add(new Student(credentials[0], credentials[1], credentials[2]));
            return true;
        } else {
            return false;
        }
    }

    private boolean validateStudentCredentials(String[] input) {
        if (input.length != 3) {
            return false;
        }

        return true;
    }
}
