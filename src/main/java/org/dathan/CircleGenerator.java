package org.dathan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CircleGenerator {

    private static final int[][] NEIGHBORS = { {1,0}, {1,1}, {0,1}, {-1,0}, {0,1}, {1,-1} };

    private final Circle origin;
    private final double outerRadius;

    public CircleGenerator(Circle origin, double outerRadius) {
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

                for (int[] pair : NEIGHBORS) {
                    PackedCircle neighbor = new PackedCircle(origin, currentCircle.getX() + pair[0], currentCircle.getY() + pair[1]);
                    if (inOuterCircle(neighbor)) {
                        nextLevel.add(neighbor);
                    } else {
                        break outerloop;
                    }
                }
            }

        }
        return allCircles;
    }

    private boolean inOuterCircle(PackedCircle circle) {
        return false;
    }


}
