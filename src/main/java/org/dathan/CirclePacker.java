package org.dathan;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CirclePacker {

    private static final int[][] NEIGHBORS = { {1,0}, {1,1}, {0,1}, {-1,0}, {0,-1}, {1,-1} };

    private final Circle origin;
    private final double outerRadius;

    public CirclePacker(Circle origin, double outerRadius) {
        this.origin = origin;
        this.outerRadius = outerRadius;
    }

    public List<Circle> getPackedCircles() {
        List<Circle> allCircles = new ArrayList<>();
        List<PackedCircle> currentLevel = new ArrayList<>();
        List<PackedCircle> nextLevel = new ArrayList<>();
        Set<PackedCircle> visited = new HashSet<>();

        currentLevel.add(new PackedCircle(origin, 0, 0));

        outerloop:
        for (;;) {
            for (PackedCircle currentCircle: currentLevel) {
                if (visited.contains(currentCircle)) {
                    continue;
                }
                visited.add(currentCircle);

                if (inOuterCircle(currentCircle)) {
                    for (int[] pair : NEIGHBORS) {
                        PackedCircle neighbor = new PackedCircle(origin, currentCircle.getX() + pair[0], currentCircle.getY() + pair[1]);
                        nextLevel.add(neighbor);
                    }
                } else {
                    break outerloop;
                }
            }

            for (PackedCircle circle: currentLevel) {
                allCircles.add(new Circle(new Point2D.Double(circle.getProjectedX(), circle.getProjectedY()), origin.getRadius()));
            }
            currentLevel.clear();
            currentLevel.addAll(nextLevel);
            nextLevel.clear();
        }

        return allCircles;
    }

    /**
     * A packed circle is entirely in the outer circle if it lies *entirely* within the outer circle.
     * We can figure that out by calculating the distance from this center point to the origin's center and adding
     * the radius of this circle. If that's smaller than the outer circle radius, this lies entirely within the outer
     * circle.
     * @param circle
     * @return
     */
    private boolean inOuterCircle(PackedCircle circle) {
        return Point2D.distance(circle.getProjectedX(), circle.getProjectedY(), origin.getCenter().getX(), origin.getCenter().getY()) + origin.getRadius() < outerRadius;
    }


}
