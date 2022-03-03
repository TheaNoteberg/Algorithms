package com.williamfiset.algorithms.geometry;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
public class PlaneTest {
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

    @Test
        public void testGetNormalVector(){
            //Vector expectedOutput = new Vector(1, 1, 1);
            double[] expectedOutput = {-1, 0, 0};
            Plane plane = new Plane(new double[]{1,2,3}, new  double[]{1,1,1}, new double[]{1,3,6});
            double[] result = plane.getNormalVector();
            for (int i = 0; i< expectedOutput.length; i++) {
                assertEquals(expectedOutput[i], result[i]);
            }
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

}
