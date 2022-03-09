package com.williamfiset.algorithms.geometry;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
public class PlaneTest {

    @Test
    public void testPlane(){
        //Expected input: Array of doubles with size 4:
        //Expected output: Plane object
        double[] expectedResult = {2,3,4,5};
        Plane plane = new Plane(expectedResult[0],expectedResult[1],expectedResult[2],expectedResult[3]);
        double[] hessianform = plane.getABCD();
        for (int i=0; i<expectedResult.length; i++){
            assertThat(expectedResult[i]).isEqualTo(hessianform[i]);
        }
    }

    @Test(expected = ArithmeticException.class)
    public void testPlaneInput(){
        //Expected input: Array of doubles with size 4, with a,b,c equal to 0:
        //Expected output: ArithmeticException
        double[] expectedResult = {0,0,0,5};
        Plane plane = new Plane(expectedResult[0],expectedResult[1],expectedResult[2],expectedResult[3]);
    }

    @Test
    public void testThreePointPlane(){
        //Expected input: Three points
        //Expected output: Plane object
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
        //Expected input: Three points in the form of three doubles
        //Expected output: Plane object
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
        //Expected input: Two Lines
        //Expected output: Plane object
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
}
