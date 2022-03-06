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
    /*
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
     */

    public static Point findPoint(double[] planeOne, double[] planeTwo, int index){

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
        /*
        double result [] = new double[2];
        double[] point = {-1, -1, -1};
        // x2 + y2*x1
        // y2*z1 + z2
        double answerX = planeTwo[0] + planeTwo[1]*planeOne[0];
        double answerC = planeTwo[1]*planeOne[2] + planeTwo[2];

        result[0] = -answerC/answerX;
        result[1] = (planeTwo[0]*result[0] + planeTwo[2])/planeTwo[1];
        point[index] = 0;
        int j = 0;
        for(int i = 0; i<planeTwo.length; i++){
            if (point[i] != 0){
                point[i] = result[j];
                j++;
            }
        }

        return point;
        */

    }

    public static Line planePlaneIntersection(Plane planeOne, Plane planeTwo ){
        //Egen Kod
        double [] normaleq1 = planeOne.getABCD();
        double [] normaleq2 = planeTwo.getABCD();
        Vector normalOne = new Vector(normaleq1[0], normaleq1[1], normaleq1[2]);
        Vector normalTwo = new Vector(normaleq2[0], normaleq2[1], normaleq2[2]);
        //egen kod
        Vector normalVector = Vector.crossProduct(normalOne, normalTwo);
        if (normalVector.getValue(0) == 0 && normalVector.getValue(1) == 0 && normalVector.getValue(2) == 0) return null;
        

        int index = findCommonZero(planeOne, planeTwo);
        //if there is no common zero => y=0
        if (index == -1) index = 2;
        
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
        /*
        for (int i=0; i<newPlanOne.length; i++){
            System.out.println(newPlanOne[i] + " Plan1 nya eq");
            System.out.println(newPlanTwo[i] + " Plan2 nya eq");
            System.out.println(point[i] + "nya Pointen");
        }

         */

        return new Line(normalVector, point); //Default return
        /*
        //double[] normalVector = Plane.crossProduct(planeOne, planeTwo);
        //Vector normalOne = planeOne.getNormalVector();
        //Vector normalTwo = planeTwo.getNormalVector();
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

        //double pOne = abcdOne[3] / sqrt(pow(abcdOne[0], 2) + pow(abcdOne[1], 2) + pow(abcdOne[2], 2));
        //double pTwo = abcdTwo[3] / sqrt(pow(abcdTwo[0], 2) + pow(abcdTwo[1], 2) + pow(abcdTwo[2], 2));


        //Tog bort normaliseringen.
        double pOne = abcdOne[3];
        double pTwo = abcdTwo[3];
        //
        int index = findCommonZero(planeOne, planeTwo);

        abcdOne[3] = -pOne;
        abcdTwo[3] = -pTwo;

        abcdOne[index] = 0;
        abcdTwo[index] = 0;

        double[][] augmented = {abcdOne, abcdTwo};
        GaussianElimination.solve(augmented);
        for (int i = 0; i< augmented.length; i++){
            for (int j = 0; j < augmented[0].length; j++){
                System.out.println(augmented[i][j] + " Matrixpos:" + i + " " + j);
            }
        }

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

    */
    }


    public static void main(String[] args) {
        Line answer = new Line (new Vector(-7,4,-1), new Point(-7, 4, 0));
        Plane planeOne = new Plane(1, 2, 1, -1);
        Plane planeTwo = new Plane(2, 3, -2, 2);
        Line result = PlanePlaneIntersection.planePlaneIntersection(planeOne, planeTwo);
        Vector vector = result.getVector();
        Point point = result.getPoint();
        double[] expectedResult = vector.getCoordinates();
        double[] expectedPoint = point.getCoordinates();
        System.out.println(result == answer);

        for (int i=0; i<expectedResult.length; i++){
            System.out.println(expectedResult[i] + " Line");
            System.out.println(expectedPoint[i] + " Point");
        }



    }

    
    //Run tests in main because of repository or run in testfile.
}
