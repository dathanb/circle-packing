package org.dathan;

/**
 * In a hexagonally-packed we can number circles based on how many circles they are from a reference circle in the X
 * direction, and how many circles they are along a 120 degree arc from that X coordinate. Then we can generate
 * an actual (x,y) position for this circle on demand -- but being able to use integers to track the coordinates on
 * two axes makes deduping SO much easier, because no rounding error.
 */
public class PackedCircle {

    private final Circle origin;
    private final int x;
    private final int y;

    public PackedCircle(Circle origin, int x, int y) {
        this.origin = origin;
        this.x = x;
        this.y = y;
    }

    public Circle getOrigin() {
        return origin;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getProjectedX() {
        return origin.getCenter().getX() +
                2 * origin.getRadius() * x +
                2 * origin.getRadius() * Math.abs(y) * Math.cos(2 * Math.PI / 3);
    }

    public double getProjectedY() {
        return origin.getCenter().getY() +
                2 * origin.getRadius() * y * Math.sin(Math.toRadians(120));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackedCircle that = (PackedCircle) o;

        if (x != that.x) {
            return false;
        }
        if (y != that.y) {
            return false;
        }
        return origin.equals(that.origin);
    }

    @Override
    public int hashCode() {
        int result = origin.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
