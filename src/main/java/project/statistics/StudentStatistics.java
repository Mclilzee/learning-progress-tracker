package project.statistics;

import java.util.Objects;

class StudentStatistics implements Comparable<StudentStatistics> {
    private final int id;
    private final int points;
    private final double completion;

    StudentStatistics(int id, int points, double completion) {
        this.id = id;
        this.points = points;
        this.completion = completion;
    }

    @Override
    public String toString() {
        return String.format("%d\t\t%d\t\t%.1f%%", this.id, this.points, this.completion);
    }

    @Override
    public int compareTo(StudentStatistics other) {
        int compareValue = Integer.compare(other.points, this.points);
        return compareValue == 0 ? Integer.compare(this.id, other.id) : compareValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentStatistics that = (StudentStatistics) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
