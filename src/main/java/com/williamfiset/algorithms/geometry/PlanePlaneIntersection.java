/**
 * Given two two planes in 3D space, find the line at which
 * they intersect, given that they are not parallell.
 * 
 * <p>Time Complexity: O(1) (input size is not variable)
 *
 * @author Edvard Aldor, Jonathan Hedin, Oscar Ingels, Thea Nöteberg
 */

package com.williamfiset.algorithms.geometry;

public class PlanePlaneIntersection {

    // Finds whether a dimension is set to 0 in both planes.
    private static int findCommonZero(Plane planeOne, Plane planeTwo){
        //Checks if a dimension is already removed.
        int index = 2;
        double[] abcdOne = planeOne.getABCD();
        double[] abcdTwo = planeTwo.getABCD();

        for (int i = 0; i < 3; i++){
            //If x or y or z is 0 they shouldnt be set to zero to find one point on the line
            if (abcdOne[i] == 0 && abcdTwo[i] == 0) {
                index = i;
            }
        }
        return index;
    }

    // Findsa point that exists on the intersection line 
    // given that one dimension can be set to 0
    public static Point findPoint(double[] planeOne, double[] planeTwo, int index){
        //Finds the point to the line
        double[] point = {-1, -1, -1};

        double[] first_equation = {-planeOne[1], -planeOne[2]};
        double[] second_equation = {planeTwo[0]*first_equation[0]+planeTwo[1],planeTwo[0]*first_equation[1]+ planeTwo[2]};

        double second_variable_solution = -second_equation[1]/second_equation[0]; //y
        double first_variable_solution = first_equation[0]*second_variable_solution + first_equation[1]; //x
        double[] both_solutions = {first_variable_solution, second_variable_solution};
        point[index] = 0;
        int j = 0;
        
        for (int i =0; i<point.length; i++){
            if (point[i] != 0){
                point[i] = both_solutions[j];
                j++; 
            }
        }
       
        return new Point(point[0], point[1], point[2]);

    }

    public static Line planePlaneIntersection(Plane planeOne, Plane planeTwo ){

        // Gets the Hessian normal form representation of the planes (ax+by+cz+d=0)
        double [] normaleq1 = planeOne.getABCD();
        double [] normaleq2 = planeTwo.getABCD();
        
        // Calculates the normal vector between the two plane normals, 
        // which will be the direction vector of the intersection line.
        Vector normalOne = new Vector(normaleq1[0], normaleq1[1], normaleq1[2]);
        Vector normalTwo = new Vector(normaleq2[0], normaleq2[1], normaleq2[2]);

        Vector normalVector = Vector.crossProduct(normalOne, normalTwo);
        
        // return null if planes are parallell
        if (normalVector.getValue(0) == 0 && normalVector.getValue(1) == 0 && normalVector.getValue(2) == 0) return null;
        
        int index = findCommonZero(planeOne, planeTwo);
        //if there is no common zero => y=0
        if (index == -1) index = 2;
        
        // Use a lesser dimension to solve linear equation of
        // finding a point on the line.
        double[] newPlanOne = new double[3];
        double[] newPlanTwo = new double[3];
        
        //Remove one dimenson that is set to zero
        for (int i=0; i<normaleq1.length; i++){
            if (i>index){
                newPlanOne[i-1] = normaleq1[i];
                newPlanTwo[i-1] = normaleq2[i];
            } 
            if (i < index) {
                newPlanOne[i] = normaleq1[i];
                newPlanTwo[i] = normaleq2[i];
            }
        }

        Point point = findPoint(newPlanOne, newPlanTwo, index);

        return new Line(normalVector, point); //Default return
    }


    public static void main(String[] args) {
        //Test in main instead of testclasses.
        Line answer = new Line (new Vector(-7,4,-1), new Point(-7, 4, 0));
        Plane planeOne = new Plane(1, 2, 1, -1);
        Plane planeTwo = new Plane(2, 3, -2, 2);
        Line result = PlanePlaneIntersection.planePlaneIntersection(planeOne, planeTwo);
        Vector vector = result.getVector();
        Point point = result.getPoint();
        double[] expectedResult = vector.getCoordinates();
        double[] expectedPoint = point.getCoordinates();
        
    }
}
