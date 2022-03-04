package com.williamfiset.algorithms.geometry;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import java.awt.geom.Point2D;

class PlanePlaneIntersectionTest {

    @Test
    public void PlanePlaneIntersectionTestOne(){
        PlanePlaneIntersection obj = new PlanePlaneIntersection();
        Line answer = new Line (new Vector(0,0,-1), new Vector(-1.75, 1.0, -0.25));
        Plane planeOne = new Plane(1, 2, 1, -1);
        Plane planeTwo = new Plane(2, 3, -2, 2);
        Line result = obj.planePlaneIntersection(planeOne, planeTwo);
        assertThat(result).isEqualTo(answer);

        //expected output value is soemthing like (-7t, 4t, 1-t)
    }
    @Test
    public void PlanePlaneIntersectionTestTwo(){
        PlanePlaneIntersection obj = new PlanePlaneIntersection();
        Line answer = new Line(new Vector(0.5, 1, -0.833), new Point(0.5, 0, 1.167));
        Plane planeOne = new Plane (5, 0, 3, 6);
        Plane planeTwo = new Plane (1, 2, 3, 4);
        Line result = obj.planePlaneIntersection(planeOne, planeTwo);
        assertThat(result).isEqualTo(answer);
    }

    //Should not return a line
    @Test
    public void PlanePlaneIntersectionParallellPlanes(){
        PlanePlaneIntersection obj = new PlanePlaneIntersection();
        Line answer;
        Plane planeOne = new Plane(1, 2, 3, 5);
        Plane planeTwo = new Plane(1, 2, 3, 17);
        Linea result = obj.planePlaneIntersection(planeOne, planeTwo);
        assertThat(result).isEqualTo(answer);
        //expected output is an empty array

    }
}