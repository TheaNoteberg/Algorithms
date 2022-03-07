package com.williamfiset.algorithms.geometry;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

public class PlanePlaneIntersectionTest {

    @Test
    public void PlanePlaneIntersectionTestOne(){
        //Expected input: Two planes x+2y+z = 1 and 2x+3y-2z=-2
        //Expected output: A line with vector: [-7,4,-1]t + [-7,4,0]
        Line answer = new Line (new Vector(-7,4,-1), new Point(-7, 4, 0));
        Plane planeOne = new Plane(1, 2, 1, -1);
        Plane planeTwo = new Plane(2, 3, -2, 2);
        Line result = PlanePlaneIntersection.planePlaneIntersection(planeOne, planeTwo);
        assertThat(answer).isEqualTo(result);
    }
    
    @Test
    public void PlanePlaneIntersectionTestTwo(){
        //Expected input: Two planes x+y+z = -1 and x+2y+3z=-4
        //Expected output: A line with vector: [1, -2, 1]t + [2,-3,0]
        Line answer = new Line(new Vector(1, -2, 1), new Point(2, -3, 0));
        Plane planeOne = new Plane (1, 1, 1, 1);
        Plane planeTwo = new Plane (1, 2, 3, 4);
        Line result = PlanePlaneIntersection.planePlaneIntersection(planeOne, planeTwo);
        assertThat(answer).isEqualTo(result);
    }
    
    @Test
    public void PlanePlaneIntersectionTestThree(){
        //Expected input: Two planes 5x+4y+4z = -6 and 5x+4y+4z=0
        //Expected output: Null
        Plane planeOne = new Plane (5, 4, 4, 6);
        Plane planeTwo = new Plane (5, 4, 4, 0);
        Line result = PlanePlaneIntersection.planePlaneIntersection(planeOne, planeTwo);
        assertThat(result).isEqualTo(null);
    }
    
}