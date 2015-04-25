package org.dathan;

import java.awt.geom.Point2D;

import org.fest.assertions.Delta;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class PackedCircleTests {

    private static final Circle ORIGIN = new Circle(new Point2D.Double(0.0, 0.0), 1.0);
    private static final Delta DELTA = Delta.delta(0.00001);

    @Test
    public void testRightCircleProjectedPosition() {
        PackedCircle right = new PackedCircle(ORIGIN, 1, 0);
        assertThat(right.getProjectedX()).isEqualTo(2.0);
        assertThat(right.getProjectedY()).isEqualTo(0.0);
    }

    @Test
    public void testUpperRightCircleProjectedPosition() {
        PackedCircle upperRight = new PackedCircle(ORIGIN, 1, 1);
        assertThat(upperRight.getProjectedX()).isEqualTo(1.0, DELTA);
        assertThat(upperRight.getProjectedY()).isEqualTo(1.73205, DELTA);
    }

    @Test
    public void testUpperLeftCircleProjectedPosition() {
        PackedCircle upperLeft = new PackedCircle(ORIGIN, 0, 1);
        assertThat(upperLeft.getProjectedX()).isEqualTo(-1.0, DELTA);
        assertThat(upperLeft.getProjectedY()).isEqualTo(1.73205, DELTA);
    }

    @Test
    public void testLeftCircleProjectedPosition() {
        PackedCircle left = new PackedCircle(ORIGIN, -1, 0);
        assertThat(left.getProjectedX()).isEqualTo(-2.0);
        assertThat(left.getProjectedY()).isEqualTo(0.0);
    }

    @Test
    public void testLowerLeftCircleProjectedPosition() {
        PackedCircle lowerLeft = new PackedCircle(ORIGIN, 0, -1);
        assertThat(lowerLeft.getProjectedX()).isEqualTo(-1.0, DELTA);
        assertThat(lowerLeft.getProjectedY()).isEqualTo(-1.73205, DELTA);
    }

    @Test
    public void testLowerRightCircleProjectedPosition() {
        PackedCircle lowerRight = new PackedCircle(ORIGIN, 1, -1);
        assertThat(lowerRight.getProjectedX()).isEqualTo(1.0, DELTA);
        assertThat(lowerRight.getProjectedY()).isEqualTo(-1.73205, DELTA);
    }

}
