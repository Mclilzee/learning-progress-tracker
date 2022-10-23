package project;

public class Course {
    private String name;
    private int score;
    private int completionScore;

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

    public void addScore(int score) {
        if (score < 0) {
            score = 0;
        }
        this.score += score;
    }
}
