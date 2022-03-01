package com.williamfiset.algorithms.geometry;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import java.awt.geom.Point2D;

class PlanePlaneIntersectionTest {

    @Test
    public void PlanePlaneIntersectionTestOne(){
        PlanePlaneIntersection obj = new PlanePlaneIntersection();
        Point2D[] answer = {};
        double[] planeOne = {1, 2, 1, -1};
        double[] planeTwo = {2, 3, -2, 2};
        Point2D[] result = obj.planePlaneIntersection(planeOne, planeTwo);
        assertThat(result).isEqualTo(answer);

        //expected output value is soemthing like (-7t, 4t, 1-t)
    }
    @Test
    public void PlanePlaneIntersectionTestTwo(){
        PlanePlaneIntersection obj = new PlanePlaneIntersection();
        Point2D[] answer = {};
        double[] planeOne = {5, 0, 3, 6};
        double[] planeTwo = {1, 2, 3, 4};
        Point2D[] result = obj.planePlaneIntersection(planeOne, planeTwo);
        assertThat(result).isEqualTo(answer);
        //expected output a throw of some sort


    }
    //Should not return a line
    @Test
    public void PlanePlaneIntersectionParallellPlanes(){
        PlanePlaneIntersection obj = new PlanePlaneIntersection();
        Point2D[] answer = {};
        double[] planeOne = {1, 2, 3, 5};
        double[] planeTwo = {1, 2, 3, 17};
        Point2D[] result = obj.planePlaneIntersection(planeOne, planeTwo);
        assertThat(result).isEqualTo(answer);
        //expected output is an empty array


    }
    @Test(expected = ArithmeticException.class)
    public void PlanePlaneIntersectionInvalidInput(){
        PlanePlaneIntersection obj = new PlanePlaneIntersection();
        Point2D[] result;
        double[] planeOne = {5, 0, 3, 6};
        double[] planeTwo = {1, 2, 3};
        result = obj.planePlaneIntersection(planeOne, planeTwo);
        //expected output a throw of some sort


    }
    
    

}