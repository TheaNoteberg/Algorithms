package com.williamfiset.algorithms.geometry;

import static java.lang.Math.*;


//import java.awt.geom.Point3D;

public class Plane {
    // The internal representation of a plane is
    // in Hessian normal form which is: ax + by + cz + d = 0
    private double a, b, c, d;
    // (1,2,3)t + (2,3,4) = (t+2, 2+3t, 3t+4)
    public Plane(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    //Constructs a plane from 3 points
    public Plane(double[] point1, double[] point2, double[] point3) {
        double[] a_b = new double [3];
        double[] a_c = new double [3];
        //create 3d lines from line class
        for(int i = 0; i <point1.length; i++){
            a_b[i] = point1[i] - point2[i];
            a_c[i] = point1[i] - point3[i];
        }
        double[] normalVector = crossProduct(a_b, a_c);
        
        this.a = normalVector[0];
        this.b = normalVector[1];
        this.c = normalVector[2];
        this.d = normalVector[0]*point1[0] + normalVector[1]*point1[1] + normalVector[2]*point1[2];//inte 100% på den här
    }
    
    //Constructs a plane from 2 lines
    public Plane(Line line1, Line line2){

        double [] vector1 = new double[3];
        double [] vector2 = new double[3];

        vector1[0] = line1.();
        vector1[1] = line1.getB();
        vector1[2] = line1.getC();

        vector2[0] = line2.getA();
        vector2[1] = line2.getB();
        vector2[2] = line2.getC();

        double[] normalVector = crossProduct(vector1, vector2);
        
        this.a = normalVector[0];
        this.b = normalVector[1];
        this.c = normalVector[2];
        this.d = normalVector[0]*line1.getX() + normalVector[1]*line1.getY() + normalVector[2]*line1.getZ();
    }

    //Coalculates the normalvector with cross product
    public static double[] crossProduct(double[] vector1, double[] vector2){
        double[] normalVector = new double[3];
        normalVector[0] = vector1[1]*vector2[2] - vector1[2]*vector2[1];
        normalVector[1] = -(vector1[0]*vector2[2] - vector1[2]*vector2[0]); 
        normalVector[2] = vector1[0]*vector2[1] - vector1[1]*vector2[0];
		return normalVector;
    }

    public double[] getNormalVector() {
        double pyth = sqrt(pow(a,2) + pow(b,2) + pow(c,2));
        double [] norm = {a/pyth,b/pyth,c/pyth};
        return norm;
    }
}   
