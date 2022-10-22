package project;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Course[] courses;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = new Course[]{new Course("Java"), new Course("DSA"), new Course("Databases"), new Course("Spring")};
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addScores(String[] scores) {
        if (scores.length < courses.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < scores.length; i++) {
            int score = Integer.parseInt(scores[i]);
            courses[i].addScore(score);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(hashCode()).append(" points: ");
        for (int i = 0; i < courses.length; i++) {
            builder.append(courses[i].getName()).append("=").append(courses[i].getScore());
            if (i != courses.length - 1) {
                builder.append("; ");
            }
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(email));
    }
}
