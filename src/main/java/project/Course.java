package project;

import java.util.Objects;

public class Course {
    private final String name;
    private int score;
    private final int completionScore;
    private int completedTasks;

    public Course(String name, int completionScore) {
        this.name = name;
        this.completionScore = completionScore;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getCompletionScore() {
        return completionScore;
    }

    public int getCompletedTasks() {
        return this.completedTasks;
    }

    public void addScore(int score) {
        if (score <= 0) {
            return;
        }
        this.score += score;
        this.completedTasks++;
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
