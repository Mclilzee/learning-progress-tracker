package project.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

class Course {
    private final String name;
    private int completedTasks;
    private final int completionScore;

    //Map students -> scores
    private final Map<Student, Integer> students;

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

    public int getCompletedTasks() {
        return this.completedTasks;
    }

    public Set<Student> getStudents() {
        return this.students.keySet();
    }

    public int getStudentScore(Student student) {
        return students.getOrDefault(student, 0);
    }

    public void addScore(Student student, int score) {
        if (score <= 0 || student == null) {
            return;
        }

        students.put(student, students.getOrDefault(student, 0) + score);
        completedTasks++;
    }

    public double getAverageScores() {
        if (students.isEmpty()) {
            return 0;
        }
        BigDecimal totalScore = new BigDecimal(students.values().stream().reduce((sum, next) -> sum += next).orElse(0));
        BigDecimal studentCount = BigDecimal.valueOf(students.size());
        return Double.parseDouble(totalScore.divide(studentCount, 2, RoundingMode.HALF_UP).toPlainString());
    }

    public String[] getCourseStatistics() {
        String[] statistics = new String[students.size()];
        int i = 0;
        for (Student student : students.keySet()) {
            double completion = getStudentCompletionScore(student);
            statistics[i] = String.format("%d\t\t%d\t\t%.1f%%\n", student.hashCode(), students.get(student), completion);
            i++;
        }

        return statistics;
    }

    private double getStudentCompletionScore(Student student) {
        double completion = (double) students.get(student) / completionScore * 100;
        return Double.parseDouble(BigDecimal.valueOf(completion).setScale(1, RoundingMode.HALF_UP).toPlainString());
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
