package com.williamfiset.algorithms.geometry;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {
    // The point is on the line, output is expected to be true.
    @Test
    public void testPointOnLineTrue(){
        Line line = new Line(1, 1, 1, 2, 2, 2);
        Point point = new Point(1.5, 1.5, 1.5);
        assertTrue(line.pointOnLine(point));
    }

    // The point is not on the line, output is expected to be false.
    @Test
    public void testPointOnLineFalse(){
        Line line = new Line(1, 1, 1, 2, 2, 2);
        Point point = new Point(1.5, 1.5, 3);
        assertFalse(line.pointOnLine(point));
    }

    // The lines are equal. Output should be true.
    @Test
    public void testEquals(){
        Point p1 = new Point(1, 1, 1);
        Vector v1 = new Vector(1, 1, 1);
        Line line1 = new Line(v1, p1);

        Point p2 = new Point(50, 50, 50);
        Vector v2 = new Vector(10,10,10);
        Line line2 = new Line(v2, p2);

        assertTrue(line1.equals(line2));
    }

    // The lines are not equal. Output should be false.
    @Test
    public void testNotEquals(){
        Point p1 = new Point(1, 1, 1);
        Vector v1 = new Vector(1, 1, 1);
        Line line1 = new Line(v1, p1);

        Point p2 = new Point(1, 1, 1);
        Vector v2 = new Vector(1,2,3);
        Line line2 = new Line(v2, p2);

        assertFalse(line1.equals(line2));
    }
}
