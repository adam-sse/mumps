package de.uni_hildesheim.mump.help_classes;


public class CourseInfo {
    private String name;
    private int points; // Or String if points can be non-numeric

    public CourseInfo(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    // Optional: Override toString() for easy logging/debugging
    @Override
    public String toString() {
        return "CourseInfo{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}