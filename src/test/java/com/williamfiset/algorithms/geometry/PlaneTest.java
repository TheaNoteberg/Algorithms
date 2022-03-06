package com.williamfiset.algorithms.geometry;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
public class PlaneTest {

    @Test
    public void testPlane(){
        double[] expectedResult = {2,3,4,5};
        Plane plane = new Plane(expectedResult[0],expectedResult[1],expectedResult[2],expectedResult[3]);
        double[] hessianform = plane.getABCD();
        for (int i=0; i<expectedResult.length; i++){
            assertThat(expectedResult[i]).isEqualTo(hessianform[i]);
        }
    }

    @Test(expected = ArithmeticException.class)
    public void testPlaneInput(){
        double[] expectedResult = {0,0,0,5};
        Plane plane = new Plane(expectedResult[0],expectedResult[1],expectedResult[2],expectedResult[3]);
    }

    @Test
    public void testThreePointPlane(){
        Point point1 = new Point(1,1,1);
        Point point2 = new Point(-1,1,0);
        Point point3 = new Point(2,0,3);
        Plane plane = new Plane(point1,point2,point3);
        double[] Hessianform = plane.getABCD();
        double[] expectedResult = {-1,3,2,-4};
        for (int i=0; i<expectedResult.length; i++){
            assertThat(expectedResult[i]).isEqualTo(Hessianform[i]);
        }
    }
    @Test
    public void testThreeArraysPlane() {
        double[] point1 = {1,1,1};
        double[] point2 = {-1,1,0};
        double[] point3 = {2,0,3};
        Plane plane = new Plane(point1,point2,point3);
        double[] hessianform = plane.getABCD();
        double[] expectedResult = {-1,3,2,-4};
        for (int i=0; i<expectedResult.length; i++){
            assertThat(expectedResult[i]).isEqualTo(hessianform[i]);
        }
    }
    @Test
    public void testTwoLinesPlane() {
        Vector vector1 = new Vector(2,0,1);
        Vector vector2 = new Vector(-1,1,-2);

        Point point1 = new Point(1,1,1);
        Point point2 = new Point(2,0,3);

        Line line1 = new Line(vector1, point1);
        Line line2 = new Line(vector2, point2);

        Plane plane = new Plane(line1, line2);
        double[] hessianform = plane.getABCD();
        double[] expectedResult = {-1,3,2,-4};
        for (int i=0; i<expectedResult.length; i++){
            assertThat(expectedResult[i]).isEqualTo(hessianform[i]);
        }
    }
    /*
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
*/
    /*
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
    */
    /*
    //Input two 4D vectors
    //Expected output exception
    @Test(expected = ArithmeticException.class)
    public void planeException(){
        double[] vector1 = {1, 2, 3,4};
        double[] vector2 = {3, 3, 3, 3};
        Plane plane = new Plane(1.0, 1.0, 1.0, 1.0);
        double[] result = plane.crossProduct(vector1, vector2);

    }
*/
}
