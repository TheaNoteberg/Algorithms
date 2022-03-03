package com.williamfiset.algorithms.geometry;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTest {
    @Test
    public void testGetNorm(){



        Vector vector = new Vector()
    }

    //Input two 4D vectors
    //Expected output exception
    @Test(expected = ArithmeticException.class)
    public void planeException(){
        double[] vector1 = {1, 2, 3,4};
        double[] vector2 = {3, 3, 3, 3};
        Plane plane = new Plane(1.0, 1.0, 1.0, 1.0);
        double[] result = plane.crossProduct(vector1, vector2);

    }

    @Test
    public void testCrossProduct(){
        //Vector vector1 = new Vector(1, 2,3);
        //Vector vector2 = new Vector(1,2,3);
        //Vector expectedOutput = new Vector(1, 1, 1);
        double[] vector1 = {1, 2, 3};
        double[] vector2 = {3, 3, 3};
        double[] expectedOutput = {-3, 6, -3};
        Plane plane = new Plane(1.0, 1.0, 1.0, 1.0);
        double[] result = plane.crossProduct(vector1, vector2);
        for (int i = 0; i< expectedOutput.length; i++) {
            assertEquals(expectedOutput[i], result[i]);
        }

    }

}
