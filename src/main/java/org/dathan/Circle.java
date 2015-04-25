package org.dathan;

import javafx.geometry.Point2D;

public class Circle {
    private final Point2D center;
    private final double radius;

    public Circle(Point2D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (Double.compare(circle.radius, radius) != 0) {
            return false;
        }
        return center.equals(circle.center);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = center.hashCode();
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
