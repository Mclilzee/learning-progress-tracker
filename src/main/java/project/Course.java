package project;

import java.util.*;

public class Course {
    private final String name;
    private int completedTasks;
    private final int completionScore;

    //Map students -> scores
    private Map<Student, Integer> students;

    public Course(String name, int completionScore) {
        this.name = name;
        this.completionScore = completionScore;
        this.students = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getCompletionScore() {
        return completionScore;
    }

    public Set<Student> getStudents() {
        return this.students.keySet();
    }

    public int getStudentScore(Student student) {
        return students.getOrDefault(student, 0);
    }

    public void addScore(Student student, int score) {
        students.put(student, students.getOrDefault(student, 0) + score);
        completedTasks++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
