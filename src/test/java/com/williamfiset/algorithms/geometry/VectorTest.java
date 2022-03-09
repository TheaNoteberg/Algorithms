package com.williamfiset.algorithms.geometry;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTest {
    @Test
    public void testGetNormalized(){
        //Expected input:two doubles (up to n-dimensions)
        //Expected output: Normalized values of the vector.
        Vector vector = new Vector(4, 3);
        Vector expectedOutput = new Vector(0.8, 0.6);
        Vector result = vector.getNormalized();
        for (int i = 0; i< expectedOutput.getDim(); i++){
            assertEquals(expectedOutput.getValue(i), result.getValue(i));
        }

    }

    @Test
    public void testCrossProduct(){
        //Expected input:Two Vectors
        //Expected output: The orthogonal Vector to the two inputed vectors
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(3, 3, 3);
        Vector expectedOutput = new Vector(-3, 6, -3);
        Vector result = vector1.crossProduct(vector1, vector2);
        for (int i = 0; i< expectedOutput.getDim(); i++) {
            assertEquals(expectedOutput.getValue(i), result.getValue(i));
        }

    }

    @Test(expected = ArithmeticException.class)
    public void testWrongInput(){
        //Expected input: Two points, but different dimensions
        //Expected output: ArithmeticException
        Vector vector = new Vector(1, 2);
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2, 3);
        vector.makeTwoPointsVector(point1, point2);
    }

    @Test
    public void testMakeTwoPointsVector(){
        //Expected input:Two points
        //Expected output: Vector from the two points.
        Vector expectedOutput = new Vector(0, -1, -2);
        Point point1 = new Point(1, 1, 1);
        Point point2 = new Point(1, 2, 3);
        Vector result = expectedOutput.makeTwoPointsVector(point1, point2);
        for (int i = 0; i< expectedOutput.getDim(); i++){
            assertEquals(expectedOutput.getValue(i), result.getValue(i));
        }
    }

}
