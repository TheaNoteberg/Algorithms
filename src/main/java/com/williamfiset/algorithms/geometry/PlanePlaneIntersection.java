package com.williamfiset.algorithms.geometry;

import static java.lang.Math.*;

import java.util.Arrays;

import com.williamfiset.algorithms.linearalgebra.GaussianElimination;
import com.williamfiset.algorithms.linearalgebra.MatrixInverse;
import com.williamfiset.algorithms.linearalgebra.MatrixMultiplication;


public class PlanePlaneIntersection {
    
   

    private static int findCommonZero(Plane planeOne, Plane planeTwo){
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

    //Finds one point on the intersection between planeOne and planeTwo.
    private static Point findPoint(Plane planeOne, Plane planeTwo, int index){
        double[] abcdOne = planeOne.getABCD();
        double[] abcdTwo = planeTwo.getABCD();
        
        double[] resultArr = new double[2];
        double[] pointArr = {-1, -1, -1};
        
        double answerX = abcdTwo[0] + abcdTwo[1]*(abcdOne[0]); 
        double answerC = abcdTwo[1]*(abcdOne[2]) + abcdTwo[2]; 

        resultArr[0] = -answerC/answerX;
        resultArr[1] = (abcdTwo[0]*resultArr[0] + abcdTwo[2])/abcdTwo[1];
        pointArr[index] = 0;
        int j = 0;
        for(int i = 0; i < abcdTwo.length; i++){
            if (pointArr[i] != 0){
                pointArr[i] = resultArr[j];
                j++;
            }
        }
        return new Point(pointArr);
    }


    public static Line planePlaneIntersection(Plane planeOne, Plane planeTwo ){

        //double[] normalVector = Plane.crossProduct(planeOne, planeTwo);
        Vector normalOne = planeOne.getNormalVector();
        Vector normalTwo = planeTwo.getNormalVector();
        double[] abcdOne = planeOne.getABCD();
        double[] abcdTwo = planeTwo.getABCD();

        //Zero vector, Planes are parallel to each other.
        if (normalOne == normalTwo) return null;

        // The line consists of a direction vector parallell to both planes
        // and a point that lies on the line.

        // The direction of the line will be the cross product of the plane normals.
        Vector dirVect = Vector.crossProduct(normalOne, normalTwo);

        
        //If there is no intersection between the planes return null
        if (dirVect.getValue(0) == 0 && dirVect.getValue(1) == 0 && dirVect.getValue(2) == 0) return null;
        
        double pOne = abcdOne[3] / sqrt(pow(abcdOne[0], 2) + pow(abcdOne[1], 2) + pow(abcdOne[2], 2));
        double pTwo = abcdTwo[3] / sqrt(pow(abcdTwo[0], 2) + pow(abcdTwo[1], 2) + pow(abcdTwo[2], 2));

        int index = findCommonZero(planeOne, planeTwo);

        abcdOne[3] = -pOne;
        abcdTwo[3] = -pTwo;

        abcdOne[index] = 0;
        abcdTwo[index] = 0;

        double[][] augmented = {abcdOne, abcdTwo};
        GaussianElimination.solve(augmented);
        double[] pointArr = new double[3];
        switch (index) {
            case 0:
                pointArr[0] = 0;
                pointArr[1] = augmented[0][3]; 
                pointArr[2] = augmented[1][3];
                break;

            case 1:
                pointArr[1] = 0;
                pointArr[0] = augmented[0][3]; 
                pointArr[2] = augmented[1][3];
                break;

            case 2:
                pointArr[2] = 0;
                pointArr[0] = augmented[0][3]; 
                pointArr[1] = augmented[1][3];
                break;
            default:
                break;
        }
        Point point = new Point(pointArr);

        
        return new Line(dirVect, point);

        // Find x0, solve equation m*x0 = b => x0 = m^-1*b
        //Point point = new Point(MatrixMultiplication.multiply(mInv, b)[0]);

        //return new Line(dirVect, point);
    }
    
    /*
    //Calculates the normal vector between the two planes
    public static double[] crossProduct(double[] planeOne, double[] planeTwo){
        double[] normalVector = new double[3];
        normalVector[0] = planeOne[1]*planeTwo[2] - planeOne[2]*planeTwo[1];
        normalVector[1] = -(planeOne[0]*planeTwo[2] - planeOne[2]*planeTwo[0]); 
        normalVector[2] = planeOne[0]*planeTwo[1] - planeOne[1]*planeTwo[0];
        
        return normalVector;
    }
    */

    public static void main(String[] args) {
        Line answer = new Line (new Vector(0,0,-1), new Vector(-1.75, 1.0, -0.25));
        Plane planeOne = new Plane(1, 2, 1, -1);
        Plane planeTwo = new Plane(2, 3, -2, 2);
        Line result = PlanePlaneIntersection.planePlaneIntersection(planeOne, planeTwo);
        System.out.println(result == answer);
    }

    
    //Run tests in main because of repository or run in testfile.
}
