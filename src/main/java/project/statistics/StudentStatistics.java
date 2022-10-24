package project.statistics;

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
        int compareValue = Integer.compare(this.points, other.points);
        return compareValue == 0 ? Integer.compare(other.id, this.id) : compareValue;
    }
}
