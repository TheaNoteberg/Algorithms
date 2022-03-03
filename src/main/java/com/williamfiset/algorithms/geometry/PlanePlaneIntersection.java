package com.williamfiset.algorithms.geometry;

public class PlanePlaneIntersection {

    public static int findCommonZero(double[] planeOne, double[] planeTwo){
        int index = -1;
        for (int i = 0; i<planeOne.length-1; i++){
            //If x or y or z is 0 they shouldnt be set to zero to find one point on the line
            if (planeOne[i] == 0 && planeTwo[i] == 0 ) {
                index = i;
            }
        }
        return index;
    }

    //Finds one point on the intersection between planeOne and planeTwo.
    public static double[] findPoint(double[] planeOne, double[] planeTwo, int index){
        double result [] = new double[2];
        double[] point = {-1, -1, -1};
        double answerX = planeTwo[0] + planeTwo[1]*(planeOne[0]); 
        double answerC = planeTwo[1]*(planeOne[2]) + planeTwo[2]; 

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
    
    }


    public static Line planePlaneIntersection(Plane planeOne, Plane planeTwo ){

        //double[] normalVector = Plane.crossProduct(planeOne, planeTwo);
        double[] normalOne = planeOne.getNormalVector();
        double[] normalTwo = planeTwo.getNormalVector();

        //Zero vector, Planes are parallel to each other.
        if (normalOne == normalTwo) return null;
        
        int index = findCommonZero(planeOne, planeTwo);

        //if there is no common zero => y=0
        if (index == -1) index = 1;
        
        double[] newPlanOne = new double[3];
        double[] newPlanTwo = new double[3];

        //Remove one dimenson that is set to zero
        for (int i=0; i<planeOne.length; i++){
            if (i == index){
                break;
            }
            if (i>index){
                newPlanOne[i-1] = planeOne[i];
                newPlanTwo[i-1] = planeTwo[i];
            } else {
                newPlanOne[i] = planeOne[i];
                newPlanTwo[i] = planeTwo[i];
            }
        }
        double[] point = findPoint(newPlanOne, newPlanTwo, index);    


        return new Point2D[] {new Point2D.Double(normalOne,normalVector[1],normalVector[2]), new Point2D.Double(point[0],2) };
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
    public void main(){
        double[] planeOne = {1, 2, 1, -1};
        double[] planeTwo = {2, 3, -2, 2};
        System.out.println(planePlaneIntersection(planeOne, planeTwo));
    }

    
    //Run tests in main because of repository or run in testfile.
}
