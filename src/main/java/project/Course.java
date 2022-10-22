package project;

public class Course {
    private String name;
    private int score;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        if (score < 0) {
            score = 0;
        }
        this.score += score;
    }
}
