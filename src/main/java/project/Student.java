package project;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Course[] courses;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = new Course[]{Courses.JAVA.getCourse(), Courses.DSA.getCourse(), Courses.DATABASES.getCourse(), Courses.SPRING.getCourse()};
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

    public void addScores(int[] scores) {
        if (scores.length < courses.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < scores.length; i++) {
            int score = scores[i];
            courses[i].addScore(score);
        }
    }

    public Set<Course> getEnrolledCourseSet() {
        Set<Course> enrolled = new HashSet<>();
        for (Course course : courses) {
            if (course.getScore() > 0) {
                enrolled.add(course);
            }
        }
        return enrolled;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(String.valueOf(hashCode())).append(" points: ");
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
