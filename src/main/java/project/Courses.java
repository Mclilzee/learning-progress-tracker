package project;

public enum Courses {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 550),
    SPRING("Spring", 550);

    private int completionScore;
    private String name;

    Courses(String name, int completionScore) {
        this.name = name;
        this.completionScore = completionScore;
    }

    public Course getCourse() {
        return new Course(this.name, this.completionScore);
    }
}
